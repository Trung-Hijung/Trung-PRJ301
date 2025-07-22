/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import utils.DbUtils;

@WebServlet("/order-details")
public class OrderDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String orderIdParam = request.getParameter("orderId");
        if (orderIdParam == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing orderId");
            return;
        }

        int orderId = Integer.parseInt(orderIdParam);

        try (Connection conn = DbUtils.getConnection()) {
            OrderDetailDAO dao = new OrderDetailDAO(conn);
            List<OrderDetailDTO> details = dao.getOrderDetailsByOrderId(orderId);

            request.setAttribute("details", details);
            request.setAttribute("orderId", orderId);
            RequestDispatcher rd = request.getRequestDispatcher("order_detail.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
