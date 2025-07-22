<%-- 
    Document   : editProduct
    Created on : Jul 13, 2025, 11:24:04 AM
    Author     : Legion 7
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="model.ProductDTO, model.ProductDAO, utils.DbUtils" %>
<%
int id = Integer.parseInt(request.getParameter("id"));
ProductDTO product = null;
try (java.sql.Connection conn = utils.DbUtils.getConnection()) {
    product = new ProductDAO(conn).getProductById(id);
} catch (Exception e) {
    e.printStackTrace();
}
%>

<html>
<head>
    <title>Edit Product</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<h2>Edit Product</h2>

<form action="editProduct" method="post" enctype="multipart/form-data">
    <input type="hidden" name="id" value="<%= product.getId() %>">

    Name: <input type="text" name="name" value="<%= product.getName() %>"><br>

    Description: <textarea name="description"><%= product.getDescription() %></textarea><br>

    Price: <input type="text" name="price" value="<%= product.getPrice() %>"><br>

    Stock: <input type="text" name="stock" value="<%= product.getStock() %>"><br>

    Category ID: <input type="text" name="categoryId" value="<%= product.getCategoryId() %>"><br>

    <p>Current Image:</p>
    <% if (product.getImage() != null && !product.getImage().isEmpty()) { %>
        <img src="<%= request.getContextPath() %>/uploads/<%= product.getImage() %>" width="150"><br>
    <% } else { %>
        No Image<br>
    <% } %>

    <br>
    Upload New Image: <input type="file" name="image"><br><br>

    <input type="submit" value="Update Product">
</form>

<a href="manageProducts">Back to Product List</a>

</body>
</html>
