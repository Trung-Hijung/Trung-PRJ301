<%-- 
    Document   : erro
    Created on : Jul 13, 2025, 2:48:33 PM
    Author     : Legion 7
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h2 style="color:red;">Có lỗi xảy ra</h2>
<p><%= request.getAttribute("message") != null ? request.getAttribute("message") : "Unknown error." %></p>
<p><a href="products">Quay lại sản phẩm</a></p>
</body>
</html>
