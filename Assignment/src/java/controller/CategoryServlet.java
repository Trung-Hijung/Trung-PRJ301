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

@WebServlet("/categories")
public class CategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;

        try {
            conn = DbUtils.getConnection();
            CategoryDAO dao = new CategoryDAO(conn);
            List<CategoryDTO> categories = dao.getAllCategories();

            request.setAttribute("categories", categories);
            RequestDispatcher rd = request.getRequestDispatcher("category_list.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        } finally {
            if (conn != null) try { conn.close(); } catch (Exception ignore) {}
        }
    }
}
