<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Dashboard</title>
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
        h2 {
            color: #555;
        }
        p {
            font-size: 16px;
            line-height: 1.6;
            color: #333;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>User Dashboard</h1>
    <h2>Account Information</h2>
    <p><strong>Account ID:</strong> ${account.accountId}</p>
    <p><strong>Full Name:</strong> ${account.fullName}</p>
    <p><strong>Email:</strong> ${account.email}</p>
    <p><strong>Phone:</strong> ${account.phone}</p>
    <p><strong>Status:</strong> ${account.status}</p>
</div>
</body>
</html>
