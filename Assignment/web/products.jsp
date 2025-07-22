<%-- 
    Document   : products
    Created on : Jul 11, 2025, 8:05:48 PM
    Author     : Legion 7
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, java.text.NumberFormat, model.ProductDTO, model.CategoryDTO, model.UserDTO" %>
<html>
<head>
<title>Our Shoes Collection</title>
<style>
body {
    font-family: Arial, sans-serif;
    background-color: #f8f9fa;
    margin: 0;
    padding: 0;
}

h2 {
    text-align: center;
    color: #343a40;
    margin: 20px 0;
}

.top-bar {
    text-align: right;
    margin: 10px 20px;
}

.btn {
    display: inline-block;
    padding: 6px 12px;
    text-decoration: none;
    background-color: #007bff;
    color: #fff;
    border-radius: 4px;
    transition: 0.3s;
    font-size: 14px;
    border: none;
    cursor: pointer;
}
.btn:hover {
    background-color: #0056b3;
}

.filter-form {
    text-align: center;
    margin: 20px 0;
}
.filter-form select,
.filter-form input[type=text] {
    padding: 6px;
    margin: 0 4px;
    border: 1px solid #ccc;
    border-radius: 4px;
    width: 160px;
}
.filter-form button {
    padding: 6px 12px;
    margin-left: 6px;
}

.product-grid {
    display: flex;
    flex-wrap: wrap;
    gap: 20px;
    justify-content: center;
    padding: 20px;
}

.product-card {
    background: #fff;
    width: 220px;
    border: 1px solid #dee2e6;
    border-radius: 8px;
    text-align: center;
    box-shadow: 0 2px 6px rgba(0,0,0,0.1);
    transition: transform 0.2s ease-in-out, box-shadow 0.2s;
    padding: 10px;
}
.product-card:hover {
    transform: scale(1.03);
    box-shadow: 0 4px 12px rgba(0,0,0,0.15);
}

.product-card img {
    width: 100%;
    height: 180px;
    object-fit: cover;
    border-radius: 4px;
}

.product-card h3 {
    font-size: 16px;
    color: #343a40;
    margin: 10px 0 5px 0;
}
.product-card .price {
    color: #e67e22;
    font-weight: bold;
    margin: 5px 0;
}

.actions {
    margin-top: 10px;
}

.actions .btn {
    margin: 2px;
    font-size: 13px;
}
</style>
</head>
<body>

<h2>Our Shoes Collection</h2>

<!-- Thanh công cụ: View Cart & Logout -->
<div class="top-bar">
    <a href="cart" class="btn">View Cart</a>
<%
    UserDTO user = (UserDTO) session.getAttribute("user");
    if (user != null) {
%>
    <span style="margin:0 10px;">|</span>
    <span>Welcome, <%= user.getName() %></span>
    <a href="logout" class="btn" style="margin-left:10px;">Logout</a>
<%
    }
%>
</div>

<!-- Bộ lọc: Category + Search -->
<form method="get" action="products" class="filter-form">
    <label for="categoryId">Category:</label>
    <select name="categoryId" id="categoryId">
        <option value="">-- All --</option>
<%
    List<CategoryDTO> categories = (List<CategoryDTO>) request.getAttribute("categories");
    String currentCategory = request.getParameter("categoryId");
    if (categories != null) {
        for (CategoryDTO c : categories) {
%>
        <option value="<%= c.getId() %>" <%= (c.getId()+"").equals(currentCategory) ? "selected" : "" %>>
            <%= c.getName() %>
        </option>
<%
        }
    }
%>
    </select>

    <label for="keyword">Search:</label>
    <input type="text" name="keyword" id="keyword"
           value="<%= request.getParameter("keyword") != null ? request.getParameter("keyword") : "" %>">
    <button type="submit" class="btn">Search</button>
</form>

<div class="product-grid">
<%
    List<ProductDTO> products = (List<ProductDTO>) request.getAttribute("products");
    if (products != null && !products.isEmpty()) {
        NumberFormat nf = NumberFormat.getInstance(new java.util.Locale("vi", "VN"));
        for (ProductDTO p : products) {
            String priceFormatted = nf.format((long)p.getPrice());
            String imagePath = (p.getImage() != null && !p.getImage().isEmpty()) ?
                (request.getContextPath() + "/uploads/" + p.getImage()) :
                (request.getContextPath() + "/images/default-shoe.jpg");
%>
    <div class="product-card">
        <img src="<%= imagePath %>" alt="<%= p.getName() %>">
        <h3><%= p.getName() %></h3>
        <p class="price"><%= priceFormatted %> VNĐ</p>
        <div class="actions">
            <a href="productDetail?id=<%= p.getId() %>" class="btn">View</a>
            <a href="cart?action=add&id=<%= p.getId() %>" class="btn">Add to Cart</a>
        </div>
    </div>
<%
        }
    } else {
%>
    <p style="text-align:center;">No products found.</p>
<%
    }
%>
</div>

</body>
</html>
