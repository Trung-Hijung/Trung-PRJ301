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

@WebServlet("/productDetail")
public class ProductDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        if (idParam == null) {
            response.sendRedirect("products");
            return;
        }

        try (Connection conn = DbUtils.getConnection()) {
            int id = Integer.parseInt(idParam);

            ProductDAO dao = new ProductDAO(conn);
            ProductDTO product = dao.getProductById(id);

            if (product == null) {
                response.sendRedirect("products?error=notfound");
                return;
            }

            request.setAttribute("product", product);
            request.getRequestDispatcher("product_detail.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect("products?error=invalidid");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
