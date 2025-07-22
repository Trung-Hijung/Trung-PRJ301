<%-- 
    Document   : register
    Created on : Jul 10, 2025, 11:56:34 PM
    Author     : Legion 7
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<title>Register</title>
<style>
body {
    font-family: Arial, sans-serif;
    background: linear-gradient(135deg, #74ABE2, #5563DE);
    margin: 0;
    padding: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}
.container {
    background: #fff;
    padding: 30px 40px;
    border-radius: 8px;
    box-shadow: 0 4px 15px rgba(0,0,0,0.2);
    max-width: 400px;
    width: 100%;
    text-align: center;
}
h2 {
    margin-bottom: 20px;
    color: #333;
}
input[type="text"], input[type="password"] {
    width: 90%;
    padding: 10px;
    margin: 10px 0;
    border: 1px solid #ccc;
    border-radius: 4px;
}
button {
    padding: 10px 20px;
    font-size: 14px;
    margin-top: 10px;
    cursor: pointer;
    background: #5563DE;
    color: #fff;
    border: none;
    border-radius: 4px;
    transition: 0.3s;
}
button:hover {
    background: #3742af;
}
.error {
    margin-top: 15px;
    font-weight: bold;
    color: red;
}
</style>
</head>
<body>
<div class="container">
<h2>Register</h2>

<form method="post" action="register">
    <input type="text" name="username" placeholder="Username" required><br>
    <input type="text" name="name" placeholder="Full Name" required><br>
    <input type="password" name="password" placeholder="Password" required><br>
    <button type="submit">Register</button>
</form>

<%
String error = request.getParameter("error");
if ("failed".equals(error)) {
%>
<p class="error">Registration failed. Try again!</p>
<%
} else if ("exception".equals(error)) {
%>
<p class="error">An error occurred. Contact admin.</p>
<%
}
%>
</div>
</body>
</html>
