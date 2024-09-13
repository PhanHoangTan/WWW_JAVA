<%@ page import="vn.edu.iuh.fit.week1.entities.Account" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.iuh.fit.week1.repositories.AccountRepository" %>
<%@ page import="vn.edu.iuh.fit.week1.entities.Role" %>
<%@ page import="vn.edu.iuh.fit.week1.repositories.RoleRepository" %>
<%@ page import="vn.edu.iuh.fit.week1.repositories.GrantAccessRepository" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh Sách Tài Khoản và Gán Vai Trò</title>
    <style>
        /* Your existing styles remain unchanged */
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
        .role-table {
            margin-top: 40px;
        }
        .role-select {
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .assign-role-form {
            display: flex;
            align-items: center;
        }
        .assign-role-form button {
            margin-left: 10px;
        }
    </style>
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

    <!-- New Role Assignment Section -->
    <h2>Gán Vai Trò Cho Tài Khoản</h2>
    <table class="role-table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Vai Trò</th>
        </tr>
        </thead>
        <tbody>
        <%
            // Fetch the list of roles and accounts
            RoleRepository roleRepository = new RoleRepository();
            List<Role> roles = roleRepository.getAllRoles();
            GrantAccessRepository grantAccessRepository = new GrantAccessRepository();

            // Display accounts with role assignment options
            if (accounts != null && !accounts.isEmpty()) {
                for (Account account : accounts) {
                    // Fetch the list of roles assigned to the account
                    List<Role> userRoles = grantAccessRepository.getRolesByAccountId(account.getAccountId());
                    Set<String> userRoleIds = userRoles.stream()
                            .map(Role::getRoleId)
                            .collect(Collectors.toSet()); // Collect role IDs into a set for easy checking
        %>
        <tr>
            <td><%= account.getAccountId() %></td>
            <td><%= account.getFullName() %></td>
            <td>
                <form class="assign-role-form" action="ControllerServlet" method="post">
                    <input type="hidden" name="action" value="assignrole"> <!-- Correct action value -->
                    <input type="hidden" name="accountId" value="<%= account.getAccountId() %>">
                    <%
                        // Check if the roles list is not null and contains roles
                        if (roles != null && !roles.isEmpty()) {
                            // Iterate through each role
                            for (Role role : roles) {
                                // Determine if the current role is assigned to the user
                                boolean isChecked = userRoleIds.contains(role.getRoleId());
                    %>
                    <label>
                        <input type="checkbox" name="roleIds" value="<%= role.getRoleId() %>" <%= isChecked ? "checked" : "" %> >
                        <%= role.getRoleName() %>
                    </label><br>
                    <%
                        }
                    } else {
                    %>
                    <p>No roles available</p>
                    <%
                        }
                    %>
                    <button type="submit" name="action" value="assignrole">Gán Vai Trò</button> <!-- Simplified button -->
                </form>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="3">Không có tài khoản nào để gán vai trò.</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>





</div>
</body>
</html>
