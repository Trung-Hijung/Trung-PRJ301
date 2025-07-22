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

@WebServlet("/admin/addProduct")
public class AddProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String priceStr = request.getParameter("price");
        String stockStr = request.getParameter("stock");
        String categoryIdStr = request.getParameter("categoryId");

        try {
            double price = Double.parseDouble(priceStr);
            int stock = Integer.parseInt(stockStr);
            int categoryId = Integer.parseInt(categoryIdStr);

            try (Connection conn = DbUtils.getConnection()) {
                ProductDAO dao = new ProductDAO(conn);
                ProductDTO product = new ProductDTO(name, description, price, stock, categoryId);
                boolean success = dao.addProduct(product);

                if (success) {
                    response.sendRedirect("manageProducts?msg=added");
                } else {
                    response.sendRedirect("manageProducts?error=insert_failed");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("manageProducts?error=exception");
        }
    }
}
