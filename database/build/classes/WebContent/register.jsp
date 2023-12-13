<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Registration</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            margin-top: 50px;
        }
        table {
            margin: 0 auto;
        }
        th, td {
            padding: 10px;
        }
        input {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            cursor: pointer;
        }
        a {
            display: block;
            margin-top: 20px;
            color: #4CAF50;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <div align="center">
        <p>${errorOne}</p>
        <p>${errorTwo}</p>
        <form action="register">
            <table border="1" cellpadding="5">
                <tr>
                    <th>User name:</th>
                    <td align="center" colspan="3">
                        <input type="text" name="username" size="45" value="" onfocus="this.value=''">
                    </td>
                </tr>
                <tr>
                    <th>Role:</th>
                    <td align="center" colspan="3">
                        <input type="text" name="role" size="45" value="" onfocus="this.value=''">
                    </td>
                </tr>
                <tr>
					<th>Password: </th>
					<td align="center" colspan="3"> 
						<input type="password" name="password" size="45" value="" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Password Confirmation: </th>
					<td align="center" colspan="3">
						<input type="password" name="confirmation" size="45" value="" onfocus="this.value=''">
					</td>
				
				</tr>
				<tr>
					<th>Credit Card Number: </th>
					<td align="center" colspan="3">
						<input type="text" name="creditCard" size="45" value="" onfocus="this.value=''">
					</td>
				
				</tr>
				<tr>
					<th>Address: </th>
					<td align="center" colspan="3">
						<input type="text" name="address" size="45" value="" onfocus="this.value=''">
					</td>
				
				</tr>
				<tr>
					<th>Phone Number: </th>
					<td align="center" colspan="3">
						<input type="text" name="phoneNumber" size="45" value="" onfocus="this.value=''">
					</td>
				
				</tr>
                <tr>
                    <td align="center" colspan="5">
                        <input type="submit" value="Register"/>
                    </td>
                </tr>
            </table>
            <a href="login.jsp" target="_self">Return to Login Page</a>
        </form>
    </div>
</body>
</html>
