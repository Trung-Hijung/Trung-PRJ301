/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Map;

@WebServlet("/updateCart")
public class UpdateCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int productId = Integer.parseInt(request.getParameter("id"));
        String action = request.getParameter("action");

        HttpSession session = request.getSession();
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");

        if (cart != null && cart.containsKey(productId)) {
            int quantity = cart.get(productId);

            if ("inc".equals(action)) {
                cart.put(productId, quantity + 1);
            } else if ("dec".equals(action)) {
                if (quantity > 1) {
                    cart.put(productId, quantity - 1);
                } else {
                    cart.remove(productId); // nếu giảm về 0 thì xoá luôn
                }
            }
        }

        session.setAttribute("cart", cart);
        response.sendRedirect("cart.jsp");
    }
}
