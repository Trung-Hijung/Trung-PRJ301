/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import model.*;
import utils.DbUtils;

import java.io.*;
import java.nio.file.*;
import java.sql.Connection;

@WebServlet("/admin/editProduct")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024
)
public class EditProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));

        String fileName = null;

        try {
            // lấy file part
            Part filePart = request.getPart("image");

            if (filePart != null && filePart.getSize() > 0) {
                fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

                // đường dẫn thư mục uploads
                String uploadPath = getServletContext().getRealPath("/uploads");
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                // lưu file
                String fullPath = uploadPath + File.separator + fileName;
                filePart.write(fullPath);
                System.out.println("File uploaded to: " + fullPath);
            } else {
                System.out.println("No new image uploaded. Keeping current image.");
            }

            try (Connection conn = DbUtils.getConnection()) {
                ProductDAO dao = new ProductDAO(conn);
                ProductDTO product = dao.getProductById(id);

                if (product != null) {
                    product.setName(name);
                    product.setDescription(description);
                    product.setPrice(price);
                    product.setStock(stock);
                    product.setCategoryId(categoryId);

                    if (fileName != null) {
                        product.setImage(fileName); // cập nhật nếu có ảnh mới
                    }

                    dao.updateProduct(product);
                } else {
                    System.err.println("Product with ID " + id + " not found.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/admin/manageProducts");
    }
}
