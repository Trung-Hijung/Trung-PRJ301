/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.UserDAO;
import model.UserDTO;
import utils.DbUtils;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/admin/manageUsers")
public class ManageUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection conn = DbUtils.getConnection()) {
            UserDAO dao = new UserDAO(conn);
            List<UserDTO> users = dao.getAllUsers();

            request.setAttribute("users", users);
            request.getRequestDispatcher("/admin/users.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("admin.jsp?error=exception");
        }
    }
}
