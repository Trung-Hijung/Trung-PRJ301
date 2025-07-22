<%-- 
    Document   : index
    Created on : Jul 10, 2025, 11:57:42 PM
    Author     : Legion 7
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="model.UserDTO" %>
<%@ page session="true" %>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<%
    UserDTO user = (UserDTO) session.getAttribute("user");
    if (user != null) {
%>
    <h2>Welcome, <%= user.getName() %>!</h2>
    <p>You are logged in as <%= user.getRole() %>.</p>
<%
    } else {
%>
    <h2>Welcome, Guest!</h2>
    <p>Please <a href="login.jsp">login</a> or <a href="register.jsp">register</a>.</p>
<%
    }
%>
</body>
</html>
