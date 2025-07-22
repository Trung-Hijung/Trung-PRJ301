<%-- 
    Document   : product_detail
    Created on : Jul 11, 2025, 8:23:54 PM
    Author     : Legion 7
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="model.ProductDTO" %>
<html>
<head>
    <title>Product Detail</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<%
    ProductDTO product = (ProductDTO) request.getAttribute("product");
%>

<h2 style="text-align:center;">Product Detail</h2>

<% if (product != null) { 
    String imagePath = (product.getImage() != null && !product.getImage().isEmpty()) ?
        (request.getContextPath() + "/uploads/" + product.getImage()) :
        (request.getContextPath() + "/images/default-shoe.jpg");
%>

<div style="text-align:center;">
    <img src="<%= imagePath %>" alt="<%= product.getName() %>"
         style="width:250px; height:250px; object-fit:cover; margin-bottom:20px;">
</div>

<p><strong>ID:</strong> <%= product.getId() %></p>
<p><strong>Name:</strong> <%= product.getName() %></p>
<p><strong>Description:</strong> <%= product.getDescription() %></p>
<p><strong>Price:</strong> <%= product.getPrice() %></p>
<p><strong>Stock:</strong> <%= product.getStock() %></p>
<p><strong>Category ID:</strong> <%= product.getCategoryId() %></p>

<p><a href="products">Back to products</a></p>

<% } else { %>
    <p style="color:red; text-align:center;">Product not found.</p>
<% } %>

</body>
</html>
