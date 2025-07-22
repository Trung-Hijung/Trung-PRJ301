/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;
import utils.DbUtils;

import java.io.IOException;
import java.sql.Connection;
import java.util.Map;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("login.jsp?error=login_required");
            return;
        }

        UserDTO user = (UserDTO) session.getAttribute("user");
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");

        if (user == null) {
            response.sendRedirect("login.jsp?error=login_required");
            return;
        }

        if (cart == null || cart.isEmpty()) {
            response.sendRedirect("cart.jsp?error=cart_empty");
            return;
        }

        try (Connection conn = DbUtils.getConnection()) {
            OrderDAO orderDAO = new OrderDAO(conn);
            ProductDAO productDAO = new ProductDAO(conn);

            double totalAmount = 0.0;

            // Tính tổng tiền
            for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
                int productId = entry.getKey();
                int quantity = entry.getValue();
                ProductDTO product = productDAO.getProductById(productId);
                if (product == null) {
                    throw new Exception("Product with ID " + productId + " not found");
                }
                totalAmount += product.getPrice() * quantity;
            }

            // Tạo order
            int orderId = orderDAO.createOrder(user.getId(), totalAmount);

            // Thêm chi tiết từng sản phẩm
            for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
                int productId = entry.getKey();
                int quantity = entry.getValue();
                ProductDTO product = productDAO.getProductById(productId);

                orderDAO.addOrderDetail(orderId, productId, quantity, product.getPrice());
            }

            // Xoá cart
            session.removeAttribute("cart");

            // Chuyển đến trang cảm ơn
            response.sendRedirect("checkout.jsp");

        } catch (Exception e) {
            e.printStackTrace();

            // Nếu error.jsp tồn tại thì forward, không thì in ra luôn
            try {
                request.setAttribute("message", "Error during checkout: " + e.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            } catch (Exception ex) {
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write("<h3 style='color:red;'>Error during checkout: " + e.getMessage() + "</h3>");
            }
        }
    }
}
