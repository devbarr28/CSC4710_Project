<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Login to Database</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            text-align: center;
        }

        h1 {
            color: #333;
        }

        table {
            margin-top: 20px;
            border-collapse: collapse;
            width: 40%;
            margin-left: auto;
            margin-right: auto;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #3498db;
            color: #fff;
        }

        form {
            margin-top: 20px;
        }

        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 8px;
            margin: 5px 0 22px 0;
            display: inline-block;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #3498db;
            color: #fff;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 3px;
        }

        input[type="submit"]:hover {
            background-color: #2980b9;
        }

        a {
            display: block;
            margin-top: 20px;
            padding: 15px 30px;
            text-decoration: none;
            color: #fff;
            background-color: #3498db;
            border-radius: 5px;
        }

        a:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>
    <center>
        <h1>Welcome to David Smith's Tree Cutting Login page!</h1>
        <div align="center">
            <p>${loginFailedStr}</p>
            <form action="login" method="post">
                <table border="1" cellpadding="5">
                    <tr>
                        <th>User name:</th>
                        <td>
                            <input type="text" name="username" size="45" autofocus>
                        </td>
                    </tr>
                    <tr>
                        <th>Password:</th>
                        <td>
                            <input type="password" name="password" size="45">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input type="submit" value="Login"/>
                        </td>
                    </tr>
                </table>
                <a href="register.jsp" target="_self">Register Here</a>
            </form>
        </div>
    </center>
</body>
</html>
