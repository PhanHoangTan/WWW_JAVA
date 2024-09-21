<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="iuh.backend.services.ProductService" %>
<%@ page import="iuh.backend.models.Productprice" %>
<%@ page import="java.util.List" %>
<%@ page import="iuh.backend.models.Productimage" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insert Product</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
        }
        #container {
            width: 90%;
            max-width: 700px;
            margin: 5% auto;
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
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        td {
            padding: 12px;
            text-align: left;
        }
        input[type="text"], select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ced4da;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input[type="submit"], input[type="reset"], button {
            background-color: #007bff;
            color: #ffffff;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }
        input[type="submit"]:hover, input[type="reset"]:hover, button:hover {
            background-color: #0056b3;
        }
        input[type="reset"] {
            background-color: #6c757d;
        }
        input[type="reset"]:hover {
            background-color: #5a6268;
        }
        .form-actions {
            text-align: right;
        }
    </style>
</head>
<body>
<div id="container">
    <h3>Insert New Product</h3>
    <form action="controls?action=insert_products" method="post">
        <table>
            <tr>
                <td>Product Name:</td>
                <td><input type="text" name="name" required></td>
            </tr>
            <tr>
                <td>Description:</td>
                <td><input type="text" name="description" required></td>
            </tr>
            <tr>
                <td>Unit:</td>
                <td><input type="text" name="unit" required></td>
            </tr>
            <tr>
                <td>Manufacturer:</td>
                <td><input type="text" name="manufacturer" required></td>
            </tr>
            <tr>
                <td>Status:</td>
                <td>
                    <select name="status" required>
                        <option value="ACTIVE">ACTIVE</option>
                        <option value="INACTIVE">INACTIVE</option>
                        <option value="TERMINATED">TERMINATED</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Price:</td>
                <td>
                    <input type="text" name="price" required>
                </td>
            </tr>
            <tr>
                <td>Image URL:</td>
                <td>
                    <input type="text" name="imagePath" required>
                </td>
            </tr>
        </table>
        <div class="form-actions">
            <input type="submit" value="Insert Product">
            <input type="reset" value="Reset">
            <button onclick="window.location.href='product.jsp';">Back</button>
        </div>
    </form>
</div>
</body>
</html>
