/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import model.OrderDAO;
import utils.DbUtils;

@WebServlet("/admin/deleteOrder")
public class DeleteOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        try (Connection conn = DbUtils.getConnection()) {
            OrderDAO dao = new OrderDAO(conn);
            dao.deleteOrder(id);
            response.sendRedirect("manageOrders?msg=deleted");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("manageOrders?error=" + e.getMessage());
        }
    }
}
