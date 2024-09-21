<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="iuh.backend.services.CustomerService" %>
<%@ page import="iuh.backend.models.Customer" %>
<%@ page import="java.util.Optional" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Customer</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f8f9fa;
            color: #343a40;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
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
        label {
            display: block;
            margin: 10px 0 5px;
            font-weight: bold;
        }
        input[type="text"],
        input[type="email"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ced4da;
            border-radius: 5px;
        }
        input[type="submit"] {
            background-color: #007bff;
            color: #ffffff;
            border: none;
            padding: 10px 15px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
            width: 100%;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        .alert {
            color: #dc3545;
            text-align: center;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
<div class="container">
    <h3>Update Customer</h3>

    <%
        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
    %>
    <p class="alert">Customer ID is missing.</p>
    <%
    } else {
        try {
            long id = Long.parseLong(idParam);
            CustomerService customerService = new CustomerService();
            Optional<Customer> optionalCustomer = customerService.findById(id);

            if (optionalCustomer.isPresent()) {
                Customer customer = optionalCustomer.get(); // Lấy đối tượng Customer
    %>
    <form action="controls?action=update_customer&id=<%= customer.getId() %>" method="post">
        <label for="custName">Name:</label>
        <input type="text" id="custName" name="custName" value="<%= customer.getCustName() %>" required>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="<%= customer.getEmail() %>" required>

        <label for="phone">Phone:</label>
        <input type="text" id="phone" name="phone" value="<%= customer.getPhone() %>" required>

        <label for="address">Address:</label>
        <input type="text" id="address" name="address" value="<%= customer.getAddress() %>" required>

        <input type="submit" value="Update">
    </form>
    <%
    } else {
    %>
    <p class="alert">Customer not found.</p>
    <%
        }
    } catch (NumberFormatException e) {
    %>
    <p class="alert">Invalid customer ID format.</p>
    <%
            }
        }
    %>
</div>
</body>
</html>
