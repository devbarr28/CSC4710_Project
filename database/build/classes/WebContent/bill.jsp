<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<html>
<head>
    <title>Client Bill</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            text-align: center;
        }

        h2 {
            text-align: center;
            color: #333;
        }

        table {
            width: 80%;
            border-collapse: collapse;
            margin: 20px auto;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #3498db;
            color: #fff;
        }

        tr:hover {
            background-color: #f5f5f5;
        }
    </style>
</head>
<body>

    <h2>Client Bill</h2>

    <table border="1">
        <tr>
            <th>Bill ID</th>
            <th>Status</th>
            <th>Order ID</th>
            <th>Price</th>
            <th>Discount</th>
            <th>Balance</th>
            <th>Note</th>
        </tr>

        <tr>
            <td>${bill.getID()}</td>
            <td>${bill.getStatus()}</td>
            <td>${bill.getOrderID()}</td>
            <td>${bill.getPrice()}</td>
            <td>${bill.getDiscount()}</td>
            <td>${bill.getBalance()}</td>
            <td>${bill.getNote()}</td>
        </tr>
       
    </table>
		<a href="clientView.jsp"target ="_self" > Back to your Home Page</a><br><br>
</body>
</html>
