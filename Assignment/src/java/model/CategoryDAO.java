/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private final Connection conn;

    public CategoryDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Lấy tất cả danh mục
     */
    public List<CategoryDTO> getAllCategories() throws SQLException {
        List<CategoryDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM categories";

        Statement st = null;
        ResultSet rs = null;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                list.add(new CategoryDTO(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
            }
        } finally {
            if (rs != null) try { rs.close(); } catch (Exception ignore) {}
            if (st != null) try { st.close(); } catch (Exception ignore) {}
        }

        return list;
    }

    /**
     * Lấy danh mục theo ID
     */
    public CategoryDTO getCategoryById(int id) throws SQLException {
        String sql = "SELECT * FROM categories WHERE id=?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new CategoryDTO(rs.getInt("id"), rs.getString("name"));
            }
        } finally {
            if (rs != null) try { rs.close(); } catch (Exception ignore) {}
            if (ps != null) try { ps.close(); } catch (Exception ignore) {}
        }

        return null;
    }

    /**
     * Thêm danh mục
     */
    public void addCategory(CategoryDTO c) throws SQLException {
        String sql = "INSERT INTO categories(name) VALUES(?)";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, c.getName());
            ps.executeUpdate();
        } finally {
            if (ps != null) try { ps.close(); } catch (Exception ignore) {}
        }
    }

    /**
     * Cập nhật danh mục
     */
    public void updateCategory(CategoryDTO c) throws SQLException {
        String sql = "UPDATE categories SET name=? WHERE id=?";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, c.getName());
            ps.setInt(2, c.getId());
            ps.executeUpdate();
        } finally {
            if (ps != null) try { ps.close(); } catch (Exception ignore) {}
        }
    }

    /**
     * Xóa danh mục
     */
    public void deleteCategory(int id) throws SQLException {
        String sql = "DELETE FROM categories WHERE id=?";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } finally {
            if (ps != null) try { ps.close(); } catch (Exception ignore) {}
        }
    }
}
