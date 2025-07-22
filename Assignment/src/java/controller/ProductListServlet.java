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
import java.util.List;

@WebServlet("/products")
public class ProductListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String categoryIdParam = request.getParameter("categoryId");
        String keyword = request.getParameter("keyword");

        Integer categoryId = null;
        if (categoryIdParam != null && !categoryIdParam.trim().isEmpty()) {
            try {
                categoryId = Integer.parseInt(categoryIdParam);
            } catch (NumberFormatException ignored) {}
        }

        try (Connection conn = DbUtils.getConnection()) {
            ProductDAO productDAO = new ProductDAO(conn);
            CategoryDAO categoryDAO = new CategoryDAO(conn);

            List<CategoryDTO> categories = categoryDAO.getAllCategories();
            List<ProductDTO> products;

            if (keyword != null && !keyword.trim().isEmpty()) {
                products = productDAO.searchProducts(keyword, categoryId);
            } else if (categoryId != null) {
                products = productDAO.getProductsByCategory(categoryId);
            } else {
                products = productDAO.getAllProducts();
            }

            request.setAttribute("products", products);
            request.setAttribute("categories", categories);
            request.setAttribute("currentCategory", categoryId);
            request.setAttribute("keyword", keyword);

            request.getRequestDispatcher("products.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
