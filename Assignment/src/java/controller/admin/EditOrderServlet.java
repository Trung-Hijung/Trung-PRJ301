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
import model.OrderDTO;
import utils.DbUtils;

@WebServlet("/admin/editOrder")
public class EditOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try (Connection conn = DbUtils.getConnection()) {
            OrderDAO dao = new OrderDAO(conn);
            OrderDTO order = dao.getOrderById(id);

            request.setAttribute("order", order);
            request.getRequestDispatcher("/admin/edit_order.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("manageOrders?error=" + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String status = request.getParameter("status");

        try (Connection conn = DbUtils.getConnection()) {
            OrderDAO dao = new OrderDAO(conn);
            dao.updateOrderStatus(id, status);
            response.sendRedirect("manageOrders?msg=updated");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("manageOrders?error=" + e.getMessage());
        }
    }
}
