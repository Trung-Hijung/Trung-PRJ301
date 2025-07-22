/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAO {
    private final Connection conn;

    public OrderDetailDAO(Connection conn) {
        this.conn = conn;
    }

    public void addOrderDetail(int orderId, int productId, int quantity, double unitPrice) throws SQLException {
        String sql = "INSERT INTO order_details(order_id, product_id, quantity, unit_price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ps.setInt(2, productId);
            ps.setInt(3, quantity);
            ps.setDouble(4, unitPrice);
            ps.executeUpdate();
        }
    }

    public List<OrderDetailDTO> getOrderDetailsByOrderId(int orderId) throws SQLException {
        List<OrderDetailDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM order_details WHERE order_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new OrderDetailDTO(
                        rs.getInt("id"),
                        rs.getInt("order_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getDouble("unit_price")
                    ));
                }
            }
        }
        return list;
    }
}
