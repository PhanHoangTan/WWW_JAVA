<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insert Employee</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
        }
        #container {
            width: 90%;
            max-width: 600px;
            margin: 5% auto;
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
        table {
            width: 100%;
            border-collapse: collapse;
        }
        td {
            padding: 12px;
            text-align: left;
        }
        input[type="text"], input[type="date"], input[type="email"], input[type="tel"], select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ced4da;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input[type="submit"], input[type="reset"] {
            background-color: #007bff;
            color: #ffffff;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }
        input[type="submit"]:hover, input[type="reset"]:hover {
            background-color: #0056b3;
        }
        input[type="reset"] {
            background-color: #6c757d;
        }
        input[type="reset"]:hover {
            background-color: #5a6268;
        }
        .form-actions {
            text-align: right;
        }
    </style>
</head>
<body>
<div id="container">
    <h3>Insert New Employee</h3>
    <form action="controls?action=insert_employee" method="post">
        <table>
            <tr>
                <td>Full Name:</td>
                <td><input type="text" name="fullName" required></td>
            </tr>
            <tr>
                <td>Birth Date:</td>
                <td><input type="date" name="dob" required></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><input type="email" name="email" required></td>
            </tr>
            <tr>
                <td>Phone:</td>
                <td><input type="tel" name="phone" required></td>
            </tr>
            <tr>
                <td>Address:</td>
                <td><input type="text" name="address" required></td>
            </tr>
            <tr>
                <td>Status:</td>
                <td>
                    <select name="status" required>
                        <option value="ACTIVE">ACTIVE</option>
                        <option value="REST">REST</option>
                        <option value="QUIT">QUIT</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="2" class="form-actions">
                    <input type="submit" value="Insert">
                    <input type="reset" value="Reset">
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
