/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

public class OrderDTO {
    private int id;
    private int userId;
    private String userName;   // 👈 sửa thành String
    private Date orderDate;
    private double totalAmount;
    private String status;

    public OrderDTO() {}

    // constructor đầy đủ
    public OrderDTO(int id, int userId, String userName, Date orderDate, double totalAmount, String status) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public OrderDTO(int id, int userId, Date orderDate, double totalAmount, String status) {
        this.id = id;
        this.userId = userId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public OrderDTO(int userId, Date orderDate, double totalAmount, String status) {
        this(0, userId, orderDate, totalAmount, status);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getUserName() { return userName; }  // 👈
    public void setUserName(String userName) { this.userName = userName; }  // 👈

    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
