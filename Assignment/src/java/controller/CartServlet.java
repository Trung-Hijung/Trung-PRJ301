/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.*;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");

        if (cart == null) {
            cart = new HashMap<>();
        }

        String action = request.getParameter("action");
        String idStr = request.getParameter("id");
        int productId = -1;

        if (idStr != null) {
            try {
                productId = Integer.parseInt(idStr);
            } catch (NumberFormatException e) {
                productId = -1;
            }
        }

        if (action != null && productId > 0) {
            switch (action) {
                case "add":
                case "inc":
                    cart.put(productId, cart.getOrDefault(productId, 0) + 1);
                    break;

                case "dec":
                    if (cart.containsKey(productId)) {
                        int qty = cart.get(productId) - 1;
                        if (qty <= 0) {
                            cart.remove(productId);
                        } else {
                            cart.put(productId, qty);
                        }
                    }
                    break;

                case "remove":
                    cart.remove(productId);
                    break;

                default:
                    // Không làm gì
                    break;
            }
        }

        session.setAttribute("cart", cart);

        // chuyển sang JSP để render
        RequestDispatcher rd = request.getRequestDispatcher("cart.jsp");
        rd.forward(request, response);
    }
}
