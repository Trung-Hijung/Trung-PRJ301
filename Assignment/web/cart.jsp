<%-- 
    Document   : cart
    Created on : Jul 11, 2025, 11:44:02 PM
    Author     : Legion 7
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, model.ProductDTO, model.ProductDAO, utils.DbUtils" %>
<html>
<head>
    <title>Your Cart</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h2>Your Shopping Cart</h2>

<%
    Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");

    if (cart == null || cart.isEmpty()) {
%>
    <p>Your cart is empty.</p>
    <p><a href="products">Continue Shopping</a></p>
<%
    } else {
        double total = 0.0;
        try (java.sql.Connection conn = DbUtils.getConnection()) {
            ProductDAO dao = new ProductDAO(conn);
%>
    <table border="1" cellpadding="5" cellspacing="0">
        <tr>
            <th>Product Name</th>
            <th>Quantity</th>
            <th>Unit Price</th>
            <th>Subtotal</th>
            <th>Action</th>
        </tr>
<%
            for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
                int productId = entry.getKey();
                int quantity = entry.getValue();
                ProductDTO product = dao.getProductById(productId);
                if (product == null) continue;

                double subtotal = product.getPrice() * quantity;
                total += subtotal;
%>
        <tr>
            <td><%= product.getName() %></td>
            <td><%= quantity %></td>
            <td><%= product.getPrice() %></td>
            <td><%= subtotal %></td>
            <td>
                <a href="cart?action=inc&id=<%= productId %>">+</a>
                <a href="cart?action=dec&id=<%= productId %>">-</a>
                <a href="cart?action=remove&id=<%= productId %>">Remove</a>
            </td>
        </tr>
<%
            }
%>
    </table>
    <h3>Total: <%= total %></h3>
    <p><a href="checkout">pay</a></p>
    <p><a href="products">Continue Shopping</a></p>
<%
        } catch (Exception e) {
            out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
        }
    }
%>

</body>
</html>
