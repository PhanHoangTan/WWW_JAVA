<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>LAB WEEK 2</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            background: linear-gradient(135deg, #f3f4f6, #d3d4d9);
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            color: #495057;
        }
        .container {
            max-width: 700px;
            margin: 0 auto;
            padding: 30px;
            background: #ffffff;
            border-radius: 12px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
            text-align: center;
        }
        h1 {
            font-size: 2.8rem;
            color: #007bff;
            margin-bottom: 30px;
            letter-spacing: 1px;
            text-transform: uppercase;
            font-weight: 700;
        }
        .nav-links {
            display: flex;
            justify-content: space-around;
            gap: 15px;
            flex-wrap: wrap;
        }
        .nav-links a {
            text-decoration: none;
            color: #ffffff;
            background-color: #007bff;
            padding: 15px 40px;
            border-radius: 8px;
            font-size: 1.2rem;
            font-weight: 500;
            transition: background-color 0.3s, transform 0.2s;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        .nav-links a:hover {
            background-color: #0056b3;
            transform: translateY(-5px);
        }
        .nav-links a:active {
            background-color: #004494;
            transform: translateY(0);
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Welcome to My Web Application</h1>
    <div class="nav-links">
        <a href="product.jsp">Product</a>
        <a href="Employees.jsp">Employees</a>
        <a href="Customer.jsp">Customer</a>
    </div>
</div>
</body>
</html>
