<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="iuh.backend.services.EmployeeService" %>
<%@ page import="iuh.backend.models.Employee" %>
<%@ page import="java.util.Optional" %>
<%@ page import="iuh.backend.enums.EmployeeStatus" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Employee</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f9fa;
            color: #343a40;
        }
        .container {
            max-width: 600px;
            margin: 20px auto;
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
        label {
            display: block;
            margin: 10px 0 5px;
        }
        input, select {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ced4da;
            border-radius: 4px;
        }
        button {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 5px;
            background-color: #007bff;
            color: white;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }
        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="container">
    <h3>Update Employee</h3>
    <%
        long id = Long.parseLong(request.getParameter("id"));
        EmployeeService employeeService = new EmployeeService();
        Optional<Employee> employeeOpt = employeeService.findById(id); // Ensure this method exists

        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
    %>
    <form action="controls?action=update_employee" method="post">
        <%--@declare id="fullname"--%><%--@declare id="dob"--%><%--@declare id="email"--%><%--@declare id="phone"--%><%--@declare id="address"--%><%--@declare id="status"--%><input type="hidden" name="id" value="<%= employee.getId() %>">
        <label for="fullName">Full Name:</label>
        <input type="text" name="fullName" value="<%= employee.getFullName() %>" required>

        <label for="dob">Date of Birth:</label>
        <input type="date" name="dob" value="<%= employee.getDob() %>" required>

        <label for="email">Email:</label>
        <input type="email" name="email" value="<%= employee.getEmail() %>" required>

        <label for="phone">Phone:</label>
        <input type="text" name="phone" value="<%= employee.getPhone() %>" required>

        <label for="address">Address:</label>
        <input type="text" name="address" value="<%= employee.getAddress() %>" required>

        <label for="status">Status:</label>
        <select name="status" required>
            <option value="ACTIVE" <%= employee.getStatus() == EmployeeStatus.ACTIVE ? "selected" : "" %>>Active</option>
            <option value="INACTIVE" <%= employee.getStatus() == EmployeeStatus.INACTIVE ? "selected" : "" %>>Inactive</option>
        </select>

        <button type="submit">Update</button>
    </form>
    <% } else { %>
    <p>Employee not found.</p>
    <% } %>
</div>
</body>
</html>
