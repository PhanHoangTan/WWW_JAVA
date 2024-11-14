<%@ page import="vn.edu.iuh.fit.week1.entities.Account" %>
<%@ page import="vn.edu.iuh.fit.week1.repositories.AccountRepository" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chỉnh Sửa Tài Khoản</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 20px;
        }
        form {
            max-width: 600px;
            margin: 0 auto;
        }
        form label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }
        form input {
            margin-bottom: 12px;
            padding: 10px;
            width: calc(100% - 22px); /* Adjust for padding and border */
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        form input[readonly] {
            background-color: #f5f5f5;
            cursor: not-allowed;
        }
        form button {
            padding: 10px 15px;
            color: #fff;
            background-color: #007bff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        form button:hover {
            background-color: #0056b3;
        }
        .back-link {
            display: block;
            margin: 20px auto;
            text-align: center;
            color: #007bff;
            text-decoration: none;
        }
        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Thêm Tài Khoản</h1>
    <form action="ControllerServlet" method="post">
        <label for="id">ID:</label>
        <input type="text" id="id" name="id" value="<%= request.getParameter("id") %>" required ><br>

        <label for="username">Username:</label>
        <input type="text" id="username" name="username" value="<%= request.getParameter("username") %>" required><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="<%= request.getParameter("email") %>" required><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" value="<%= request.getParameter("password") %>" required><br>

        <label for="phone">Phone:</label>
        <input type="text" id="phone" name="phone" value="<%= request.getParameter("phone") %>" required><br>

        <label for="status">Status:</label>
        <input type="text" id="status" name="status" value="<%= request.getParameter("status") %>" required><br>

        <button type="submit" name="action" value="adduser">Lưu Thay Đổi</button>
    </form>
    <a href="dashboard.jsp" class="back-link">Quay lại danh sách tài khoản</a>
</div>
</body>
</html>
