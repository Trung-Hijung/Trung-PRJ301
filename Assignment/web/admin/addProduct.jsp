<%-- 
    Document   : addProduct
    Created on : Jul 13, 2025, 11:23:42 AM
    Author     : Legion 7
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Add Product</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h2>Add New Product</h2>

<form action="addProduct" method="post">
    Name: <input type="text" name="name"><br>
    Description: <textarea name="description"></textarea><br>
    Price: <input type="text" name="price"><br>
    Stock: <input type="text" name="stock"><br>
    Category ID: <input type="text" name="categoryId"><br>
    <input type="submit" value="Add Product">
</form>

<a href="manageProducts">Back to Product List</a>
</body>
</html>
