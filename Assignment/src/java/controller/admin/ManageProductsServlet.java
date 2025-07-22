/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.ProductDAO;
import model.ProductDTO;
import utils.DbUtils;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/admin/manageProducts")
public class ManageProductsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection conn = DbUtils.getConnection()) {
            ProductDAO dao = new ProductDAO(conn);
            List<ProductDTO> products = dao.getAllProducts();

            request.setAttribute("products", products);
            request.getRequestDispatcher("/admin/products.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("admin.jsp?error=" + e.getMessage());
        }
    }
}
