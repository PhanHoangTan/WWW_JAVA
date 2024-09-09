<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #e9ecef;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .register-container {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 320px;
            max-width: 100%;
            text-align: center;
        }

        h1 {
            margin-bottom: 20px;
            color: #007bff;
        }

        label {
            display: block;
            margin-bottom: 8px;
            color: #495057;
        }

        input[type="text"],
        input[type="password"],
        input[type="email"],
        input[type="number"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ced4da;
            border-radius: 4px;
        }

        input[type="submit"] {
            width: 100%;
            padding: 12px;
            background-color: #007bff;
            border: none;
            border-radius: 4px;
            color: #ffffff;
            font-size: 16px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        .error-message {
            color: #dc3545;
            margin-top: 10px;
            font-size: 14px;
        }

        .success-message {
            color: #28a745;
            margin-top: 10px;
            font-size: 14px;
        }
    </style>
</head>
<body>
<div class="register-container">
    <h1>Register</h1>
    <form action="ControllerServlet" method="post">
        <input type="hidden" name="action" value="register">
        <label for="accountId">Account ID:</label>
        <input type="text" id="accountId" name="accountId" placeholder="Account ID" required>
        <label for="fullName">Full Name:</label>
        <input type="text" id="fullName" name="fullName" placeholder="Full Name" required>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" placeholder="Password" required>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" placeholder="Email" required>
        <label for="phone">Phone:</label>
        <input type="text" id="phone" name="phone" placeholder="Phone" required>
        <label for="status">Status:</label>
        <input type="number" id="status" name="status" placeholder="Status" required>
        <input type="submit" value="Register">
        <% if (request.getParameter("error") != null) { %>
        <p class="error-message">Registration failed. Please try again.</p>
        <% } %>
        <% if (request.getParameter("success") != null) { %>
        <p class="success-message">Registration successful! You can now <a href="index.jsp">login</a>.</p>
        <% } %>
    </form>
</div>
</body>
</html>
