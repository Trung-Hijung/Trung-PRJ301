<%-- 
    Document   : order_detail
    Created on : Jul 14, 2025, 10:08:09 AM
    Author     : Legion 7
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, model.OrderDetailDTO" %>
<html>
<head>
    <title>Order Details</title>
    <link rel="stylesheet" href="css/style.css">

</head>
<body>
<h2>Order Details (Order ID: <%= request.getAttribute("orderId") %>)</h2>

<table border="1" cellpadding="5">
<tr>
    <th>Product ID</th>
    <th>Quantity</th>
    <th>Unit Price</th>
</tr>

<%
    List<OrderDetailDTO> details = (List<OrderDetailDTO>) request.getAttribute("details");
    if (details != null && !details.isEmpty()) {
        for (OrderDetailDTO d : details) {
%>
<tr>
    <td><%= d.getProductId() %></td>
    <td><%= d.getQuantity() %></td>
    <td>$<%= d.getUnitPrice() %></td>
</tr>
<%
        }
    } else {
%>
<tr>
    <td colspan="3" style="text-align:center;">No details found for this order.</td>
</tr>
<%
    }
%>
</table>

</body>
</html>
