<%@ page import="vn.edu.iuh.fit.week1.entities.Account" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh Sách Tài Khoản</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #333;
            text-align: center;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Danh Sách Tài Khoản</h1>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Email</th>
            <th>Password</th>
            <th>Phone</th>
            <th>Status</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Account> accounts = (List<Account>) request.getAttribute("accounts");
            if (accounts != null) {
                for (Account account : accounts) {
        %>
        <tr>
            <td><%= account.getAccountId() %></td>
            <td><%= account.getFullName() %></td>
            <td><%= account.getEmail() %></td>
            <td><%= account.getPassword() %></td>
            <td><%= account.getPhone() %></td>
            <td><%= account.getStatus() %></td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="6">Không có tài khoản nào.</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>
