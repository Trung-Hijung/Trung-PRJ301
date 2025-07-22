/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    private final Connection conn;

    public OrderDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Tạo order mới → trả về ID
     */
    public int createOrder(int userId, double totalAmount) throws SQLException {
        String sql =
            "INSERT INTO orders (user_id, order_date, total_amount, status) " +
            "VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, userId);
            ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            ps.setDouble(3, totalAmount);
            ps.setString(4, "Pending");
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        throw new SQLException("Failed to create order");
    }

    /**
     * Thêm chi tiết order
     */
    public void addOrderDetail(int orderId, int productId, int quantity, double unitPrice) throws SQLException {
        String sql =
            "INSERT INTO order_details (order_id, product_id, quantity, unit_price) " +
            "VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ps.setInt(2, productId);
            ps.setInt(3, quantity);
            ps.setDouble(4, unitPrice);
            ps.executeUpdate();
        }
    }

    /**
     * Lấy tất cả orders
     */
    public List<OrderDTO> getAllOrders() throws SQLException {
        List<OrderDTO> orders = new ArrayList<>();
        String sql =
            "SELECT o.id, o.user_id, u.username, o.order_date, o.total_amount, o.status " +
            "FROM orders o " +
            "JOIN users u ON o.user_id = u.id";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                orders.add(new OrderDTO(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getTimestamp("order_date"),
                        rs.getDouble("total_amount"),
                        rs.getString("status")
                ));
            }
        }
        return orders;
    }

    /**
     * Lấy 1 order theo ID
     */
    public OrderDTO getOrderById(int id) throws SQLException {
        String sql =
            "SELECT o.id, o.user_id, u.username, o.order_date, o.total_amount, o.status " +
            "FROM orders o " +
            "JOIN users u ON o.user_id = u.id " +
            "WHERE o.id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new OrderDTO(
                            rs.getInt("id"),
                            rs.getInt("user_id"),
                            rs.getString("username"),
                            rs.getTimestamp("order_date"),
                            rs.getDouble("total_amount"),
                            rs.getString("status")
                    );
                }
            }
        }
        return null;
    }

    /**
     * Cập nhật trạng thái order
     */
    public void updateOrderStatus(int id, String newStatus) throws SQLException {
        String sql = "UPDATE orders SET status = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newStatus);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    /**
     * Xóa order (và cả chi tiết order)
     */
    public void deleteOrder(int id) throws SQLException {
        // Xóa order_details trước để tránh lỗi khoá ngoại
        String deleteDetails = "DELETE FROM order_details WHERE order_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(deleteDetails)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }

        // Xóa order
        String deleteOrder = "DELETE FROM orders WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(deleteOrder)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
