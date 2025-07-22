<%-- 
    Document   : users
    Created on : Jul 13, 2025, 11:24:54 AM
    Author     : Legion 7
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, model.UserDTO" %>
<html>
<head>
    <title>Manage Users</title>
    <style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f9fafb;
        margin: 0;
        padding: 20px;
    }

    h2 {
        text-align: center;
        color: #333;
        margin-bottom: 20px;
    }

    table {
        width: 90%;
        margin: 0 auto 20px;
        border-collapse: collapse;
        background: #fff;
        box-shadow: 0 0 5px rgba(0,0,0,0.1);
    }

    table th, table td {
        padding: 10px;
        border: 1px solid #ddd;
        text-align: center;
    }

    table th {
        background-color: #007bff;
        color: white;
    }

    table tr:nth-child(even) {
        background-color: #f2f2f2;
    }

    a {
        color: #007bff;
        text-decoration: none;
    }

    a:hover {
        text-decoration: underline;
    }

    .btn {
        display: inline-block;
        padding: 8px 12px;
        background-color: #28a745;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        text-decoration: none;
    }

    .btn:hover {
        background-color: #218838;
    }

    .back-btn {
        text-align: center;
        margin-top: 20px;
    }

    .back-btn button {
        background-color: #6c757d;
        color: white;
        padding: 8px 12px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    .back-btn button:hover {
        background-color: #5a6268;
    }

    .action-links a {
        margin: 0 4px;
        color: #007bff;
    }

    .action-links a:hover {
        color: #0056b3;
    }

    .add-link {
        display: block;
        text-align: center;
        margin-bottom: 15px;
    }
</style>

</head>
<body>

<h2>User Management</h2>

<table border="1" cellpadding="5" cellspacing="0">
<tr>
<th>ID</th><th>Username</th><th>Name</th><th>Role</th></tr>

<%
List<UserDTO> users = (List<UserDTO>) request.getAttribute("users");
if (users != null) {
    for (UserDTO u : users) {
%>
<tr>
<td><%=u.getId()%></td>
<td><%=u.getUsername()%></td>
<td><%=u.getName()%></td>
<td><%=u.getRole()%></td>
</tr>
<%
    }
}
%>
</table>

<!-- Nút quay về Dashboard -->
<div style="margin-top: 15px; text-align: center;">
    <form action="dashboard.jsp" method="get">
        <button type="submit">Back to Admin page</button>
    </form>
</div>

</body>
</html>
