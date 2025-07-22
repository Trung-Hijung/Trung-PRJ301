<%-- 
    Document   : category_list
    Created on : Jul 14, 2025, 10:07:02 AM
    Author     : Legion 7
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, model.CategoryDTO" %>
<html>
<head>
    <title>Categories</title>
    <link rel="stylesheet" href="css/style.css">

</head>
<body>
<h2>Product Categories</h2>

<ul>
<%
    List<CategoryDTO> categories = (List<CategoryDTO>) request.getAttribute("categories");
    if (categories != null && !categories.isEmpty()) {
        for (CategoryDTO c : categories) {
%>
    <li>
        <%= c.getName() %> 
        (ID: <%= c.getId() %>)
    </li>
<%
        }
    } else {
%>
    <li>No categories found.</li>
<%
    }
%>
</ul>

</body>
</html>
