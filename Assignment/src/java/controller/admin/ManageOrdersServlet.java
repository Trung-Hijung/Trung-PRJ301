/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.OrderDAO;
import model.OrderDTO;
import utils.DbUtils;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/admin/manageOrders")
public class ManageOrdersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection conn = DbUtils.getConnection()) {
            OrderDAO dao = new OrderDAO(conn);
            List<OrderDTO> orders = dao.getAllOrders();

            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/admin/orders.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("admin.jsp?error=" + e.getMessage());
        }
    }
}
