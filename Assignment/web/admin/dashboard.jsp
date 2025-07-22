<%-- 
    Document   : dashboard
    Created on : Jul 13, 2025, 11:29:44 AM
    Author     : Legion 7
--%>

<html>
<head>
    <title>Admin Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f8;
            margin: 0;
            padding: 0;
        }

        h2 {
            text-align: center;
            color: #343a40;
            margin-top: 30px;
            font-size: 28px;
        }

        .dashboard {
            max-width: 400px;
            margin: 40px auto;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }

        ul {
            list-style: none;
            padding: 0;
            margin: 0;
        }

        ul li {
            margin: 15px 0;
            text-align: center;
        }

        ul li a {
            display: inline-block;
            width: 80%;
            padding: 10px;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            border-radius: 4px;
            transition: background 0.3s;
        }

        ul li a:hover {
            background-color: #0056b3;
        }

        .logout {
            background-color: #dc3545;
        }

        .logout:hover {
            background-color: #b02a37;
        }
    </style>
</head>
<body>

<h2>Welcome, Admin</h2>

<div class="dashboard">
    <ul>
        <li><a href="manageProducts">Manage Products</a></li>
        <li><a href="manageOrders">Manage Orders</a></li>
        <li><a href="manageUsers">Manage Users</a></li>
        <li><a href="../logout" class="logout">Logout</a></li>
    </ul>
</div>

</body>
</html>
