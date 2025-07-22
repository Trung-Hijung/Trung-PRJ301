<%-- 
    Document   : edit_order.jsp
    Created on : Jul 21, 2025, 11:25:25 PM
    Author     : Legion 7
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="model.OrderDTO" %>
<html>
<head>
    <title>Edit Order</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>

<h2>Edit Order</h2>

<%
    OrderDTO order = (OrderDTO) request.getAttribute("order");
    if (order == null) {
%>
    <p style="color:red;">Order not found.</p>
    <a href="manageOrders">Back to Orders</a> |
    <a href="../dashboard.jsp">Back to Admin Dashboard</a>
<%
    } else {
%>

<form action="editOrder" method="post">
    <input type="hidden" name="id" value="<%= order.getId() %>">

    <p><strong>Order ID:</strong> <%= order.getId() %></p>
    <p><strong>User ID:</strong> <%= order.getUserId() %></p>
    <p><strong>Username:</strong> <%= order.getUserName() %></p>
    <p><strong>Order Date:</strong> <%= order.getOrderDate() %></p>
    <p><strong>Total Amount:</strong> <%= order.getTotalAmount() %></p>

    <label for="status">Status:</label>
    <select name="status" id="status">
        <option value="Pending" <%= "Pending".equals(order.getStatus()) ? "selected" : "" %>>Pending</option>
        <option value="Processing" <%= "Processing".equals(order.getStatus()) ? "selected" : "" %>>Processing</option>
        <option value="Shipped" <%= "Shipped".equals(order.getStatus()) ? "selected" : "" %>>Shipped</option>
        <option value="Cancelled" <%= "Cancelled".equals(order.getStatus()) ? "selected" : "" %>>Cancelled</option>
        <option value="Completed" <%= "Completed".equals(order.getStatus()) ? "selected" : "" %>>Completed</option>
    </select>
    <br><br>

    <input type="submit" value="Update Order">
</form>

<br>
<a href="manageOrders">Back to Orders</a> |
<a href="../dashboard.jsp">Back to Admin Dashboard</a>

<%
    }
%>

</body>
</html>
