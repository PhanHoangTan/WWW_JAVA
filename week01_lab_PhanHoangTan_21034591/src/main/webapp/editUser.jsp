<%@ page import="vn.edu.iuh.fit.week1.entities.Account" %>
<%@ page import="vn.edu.iuh.fit.week1.entities.Role" %>
<%@ page import="vn.edu.iuh.fit.week1.repositories.AccountRepository" %>
<%@ page import="vn.edu.iuh.fit.week1.repositories.RoleRepository" %>
<%@ page import="vn.edu.iuh.fit.week1.repositories.GrantAccessRepository" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chỉnh Sửa Tài Khoản</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 50%;
            margin: 0 auto;
            padding: 20px;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-top: 20px;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        label {
            margin-bottom: 8px;
            font-weight: bold;
        }
        input[type="text"],
        input[type="email"],
        input[type="password"] {
            padding: 8px;
            margin-bottom: 16px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        button {
            padding: 10px;
            background-color: #28a745;
            border: none;
            border-radius: 4px;
            color: #fff;
            font-size: 16px;
            cursor: pointer;
        }
        button:hover {
            background-color: #218838;
        }
        .back-link {
            display: block;
            text-align: center;
            margin-top: 20px;
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
    <h1>Chỉnh Sửa Tài Khoản</h1>
    <%
        // Fetch account details based on ID
        String id = request.getParameter("id");
        AccountRepository accountRepository = new AccountRepository();
        Account account = accountRepository.getAccountById(String.valueOf(id));

        // Fetch all roles
        RoleRepository roleRepository = new RoleRepository();
        List<Role> allRoles = roleRepository.getAllRoles();

        // Fetch roles assigned to the current account
        GrantAccessRepository grantAccessRepository = new GrantAccessRepository();
        List<Role> userRoles = grantAccessRepository.getRolesByAccountId(account.getAccountId());

        if (account != null) {
    %>
    <form action="ControllerServlet" method="post">
        <input type="hidden" name="id" value="<%= account.getAccountId() %>">

        <label for="username">Username:</label>
        <input type="text" id="username" name="username" value="<%= account.getFullName() %>" required>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="<%= account.getEmail() %>" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" value="<%= account.getPassword() %>" required>

        <label for="phone">Phone:</label>
        <input type="text" id="phone" name="phone" value="<%= account.getPhone() %>" required>

        <label for="status">Status:</label>
        <input type="text" id="status" name="status" value="<%= account.getStatus() %>" required>

        <label>Roles:</label>
        <%
            // Check if the roles list is not null and contains roles
            if (allRoles != null && !allRoles.isEmpty()) {
                for (Role role : allRoles) {
                    // Determine if the role is assigned to the user
                    boolean isChecked = userRoles.stream().anyMatch(r -> r.getRoleId().equals(role.getRoleId()));
        %>
        <label>
            <input type="checkbox" name="roles" value="<%= role.getRoleId() %>" <%= isChecked ? "checked" : "" %>>
            <%= role.getRoleName() %>
        </label>
        <%
                }
            }
        %>

        <button type="submit" name="action" value="updateuser">Lưu Thay Đổi</button>
    </form>
    <a href="dashboard.jsp" class="back-link">Quay lại danh sách tài khoản</a>
    <%
    } else {
    %>
    <p>Tài khoản không tồn tại.</p>
    <a href="dashboard.jsp" class="back-link">Quay lại danh sách tài khoản</a>
    <%
        }
    %>
</div>
</body>
</html>
