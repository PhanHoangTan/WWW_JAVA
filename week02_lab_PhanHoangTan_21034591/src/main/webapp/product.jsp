<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="iuh.backend.services.ProductService" %>
<%@ page import="iuh.backend.models.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="iuh.backend.models.Productimage" %>
<%@ page import="iuh.backend.models.Productprice" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product List</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f1f3f5;
            margin: 0;
            padding: 0;
        }
        #container {
            max-width: 900px;
            margin: auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 12px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
        }
        h3 {
            text-align: center;
            color: #007bff;
            margin-bottom: 20px;
            font-size: 2rem;
            text-transform: uppercase;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #007bff;
            color: #ffffff;
        }
        tr:hover {
            background-color: #f8f9fa;
        }
        .status-active { color: green; }
        .status-inactive { color: orange; }
        .status-terminated { color: red; }
        button {
            background-color: #007bff;
            color: #ffffff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 14px;
            transition: background-color 0.3s;
        }
        button:hover {
            background-color: #0056b3;
        }
        .insert-button {
            display: inline-block;
            margin-bottom: 20px;
            padding: 10px 20px;
            background-color: #28a745;
            color: #ffffff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
            text-align: center;
        }
        .insert-button:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>

<div id="container">
    <h3>Product List</h3>
    <button class="insert-button" onclick="window.location.href='insertProduct.jsp';">Insert New Product</button>
    <table>
        <thead>
        <tr>
            <th>Image</th>
            <th>Name</th>
            <th>Description</th>
            <th>Unit</th>
            <th>Manufacturer</th>
            <th>Status</th>
            <th>Price</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            ProductService productService = new ProductService();
            List<Product> productList = productService.getAll();
            for (Product product : productList) {
                long id = product.getId();
                Productimage randomImage = productService.getRandomProductImage(id);
        %>
        <tr>
            <td>
                <% if (randomImage != null) { %>
                <img src="<%= randomImage.getPath() %>" alt="<%= randomImage.getAlternative() %>" style="width: 80px; height: auto; border-radius: 5px;">
                <% } %>
            </td>
            <td><%= product.getName() %></td>
            <td><%= product.getDescription() %></td>
            <td><%= product.getUnit() %></td>
            <td><%= product.getManufacturerName() %></td>
            <td class="<%= product.getStatus().getValue() == 1 ? "status-active" : product.getStatus().getValue() == 0 ? "status-inactive" : "status-terminated" %>">
                <%= product.getStatus().getValue() == 1 ? "Đang kinh doanh" : product.getStatus().getValue() == 0 ? "Tạm ngưng" : "Không kinh doanh nữa" %>
            </td>
            <td>
                <% for(Productprice price : productService.getProductByPrice(id)) { %>
                <%= price.getPrice() %> đ<br>
                <% } %>
            </td>
            <td>
                <a href="updateProduct.jsp?id=<%= id %>" class="update">Update</a>
                <button onclick="window.location.href='controls?action=<%= product.getStatus().getValue() == 1 ? "deactivate_product" : "activate_product" %>&id=<%= id %>';">
                    <%= product.getStatus().getValue() == 1 ? "Activate" : "Deactivate" %>
                </button>
                <button onclick="window.location.href='controls?action=delete_product&id=<%= id %>';">Delete</button>
            </td>
        </tr>
        <button onclick="window.location.href='index.jsp';">Back</button>
        <% } %>
        </tbody>
    </table>
</div>

</body>
</html>
