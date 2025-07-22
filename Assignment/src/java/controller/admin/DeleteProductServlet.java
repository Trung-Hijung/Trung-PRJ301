/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.ProductDAO;
import utils.DbUtils;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/admin/deleteProduct")
public class DeleteProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");

        try {
            int id = Integer.parseInt(idStr);

            try (Connection conn = DbUtils.getConnection()) {
                ProductDAO dao = new ProductDAO(conn);
                boolean success = dao.deleteProduct(id);

                if (success) {
                    response.sendRedirect("manageProducts?msg=deleted");
                } else {
                    response.sendRedirect("manageProducts?error=delete_failed");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("manageProducts?error=exception");
        }
    }
}
