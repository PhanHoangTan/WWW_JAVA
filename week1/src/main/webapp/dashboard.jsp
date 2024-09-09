<%@ page import="vn.edu.iuh.fit.week1.entities.Account" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.iuh.fit.week1.repositories.AccountRepository" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh Sách Tài Khoản</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .container {
            width: 80%;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #333;
            margin-bottom: 20px;
        }
        a {
            text-decoration: none;
            color: #007bff;
        }
        a:hover {
            text-decoration: underline;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .action-buttons button, .action-buttons a {
            margin-right: 10px;
        }
        .edit-form {
            display: none;
            background-color: #f9f9f9;
            padding: 20px;
            border-top: 1px solid #ddd;
        }
        .edit-form form {
            display: flex;
            flex-direction: column;
        }
        .edit-form label {
            margin: 10px 0 5px;
        }
        .edit-form input {
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .edit-form button {
            margin-top: 10px;
        }
        .back-link {
            display: inline-block;
            margin-bottom: 20px;
        }
    </style>
    <script>
        function toggleEditForm(id) {
            const form = document.getElementById('edit-form-' + id);
            form.style.display = form.style.display === 'none' ? 'block' : 'none';
        }
    </script>
</head>
<body>
<div class="container">
    <h1>Danh Sách Tài Khoản</h1>
    <a href="addUser.jsp">Thêm Tài Khoản</a>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Email</th>
            <th>Password</th>
            <th>Phone</th>
            <th>Status</th>
            <th>Hành Động</th>
        </tr>
        </thead>
        <tbody>
        <%
            // Fetch the updated list of accounts
            AccountRepository accountRepository = new AccountRepository();
            List<Account> accounts = accountRepository.getAllAccounts();
            if (accounts != null && !accounts.isEmpty()) {
                for (Account account : accounts) {
        %>
        <tr>
            <td><%= account.getAccountId() %></td>
            <td><%= account.getFullName() %></td>
            <td><%= account.getEmail() %></td>
            <td><%= account.getPassword() %></td>
            <td><%= account.getPhone() %></td>
            <td><%= account.getStatus() %></td>
            <td class="action-buttons">
                <a href="editUser.jsp?id=<%= account.getAccountId() %>">Sửa</a>

                <a href="ControllerServlet?action=deleteuser&id=<%= account.getAccountId() %>"
                   onclick="return confirm('Bạn có chắc chắn muốn xóa tài khoản này?');"
                   class="delete-button">Xóa</a>

            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="7">Không có tài khoản nào.</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>
