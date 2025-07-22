/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private final Connection conn;

    public ProductDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Lấy tất cả sản phẩm
     */
    public List<ProductDTO> getAllProducts() throws SQLException {
        String sql = "SELECT * FROM products";
        List<ProductDTO> products = new ArrayList<>();

        try ( Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                products.add(mapRow(rs));
            }
        }
        return products;
    }

    /**
     * Lấy sản phẩm theo ID
     */
    public ProductDTO getProductById(int id) throws SQLException {
        String sql = "SELECT * FROM products WHERE id=?";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    /**
     * Lấy sản phẩm theo Category ID
     */
    public List<ProductDTO> getProductsByCategory(int categoryId) throws SQLException {
        String sql = "SELECT * FROM products WHERE category_id=?";
        List<ProductDTO> products = new ArrayList<>();

        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    products.add(mapRow(rs));
                }
            }
        }
        return products;
    }

    /**
     * Thêm sản phẩm
     */
    public boolean addProduct(ProductDTO product) throws SQLException {
        String sql
                = "INSERT INTO products (name, description, price, stock, image, category_id) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getStock());
            ps.setString(5, product.getImage());
            ps.setInt(6, product.getCategoryId());
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Cập nhật sản phẩm
     */
    public void updateProduct(ProductDTO product) throws SQLException {
        String sql;
        if (product.getImage() != null) {
            sql = "UPDATE products SET name=?, description=?, price=?, stock=?, category_id=?, image=? WHERE id=?";
        } else {
            sql = "UPDATE products SET name=?, description=?, price=?, stock=?, category_id=? WHERE id=?";
        }

        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getStock());
            ps.setInt(5, product.getCategoryId());
            if (product.getImage() != null) {
                ps.setString(6, product.getImage());
                ps.setInt(7, product.getId());
            } else {
                ps.setInt(6, product.getId());
            }
            ps.executeUpdate();
        }
    }

    /**
     * Xóa sản phẩm
     */
    public boolean deleteProduct(int id) throws SQLException {
        String sql = "DELETE FROM products WHERE id=?";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Tiện ích: chuyển ResultSet thành ProductDTO
     */
    private ProductDTO mapRow(ResultSet rs) throws SQLException {
        return new ProductDTO(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getDouble("price"),
                rs.getInt("stock"),
                rs.getString("image"),
                rs.getInt("category_id")
        );
    }
    
    /**
 * Tìm kiếm sản phẩm theo tên (keyword) và category (có thể null).
 */
public List<ProductDTO> searchProducts(String keyword, Integer categoryId) throws SQLException {
    List<ProductDTO> list = new ArrayList<>();

    StringBuilder sql = new StringBuilder("SELECT * FROM products WHERE 1=1");
    if (keyword != null && !keyword.trim().isEmpty()) {
        sql.append(" AND name LIKE ?");
    }
    if (categoryId != null) {
        sql.append(" AND category_id = ?");
    }

    try (PreparedStatement ps = conn.prepareStatement(sql.toString())) {
        int paramIndex = 1;

        if (keyword != null && !keyword.trim().isEmpty()) {
            ps.setString(paramIndex++, "%" + keyword.trim() + "%");
        }

        if (categoryId != null) {
            ps.setInt(paramIndex, categoryId);
        }

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new ProductDTO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock"),
                        rs.getString("image"),
                        rs.getInt("category_id")
                ));
            }
        }
    }

    return list;
}

}
