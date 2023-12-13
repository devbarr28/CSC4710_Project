<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Client page</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            text-align: center;
        }

        h1 {
            text-align: center;
            color: #333;
        }

        a {
            display: inline-block;
            margin: 10px;
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

    <h1>Welcome to Client View! View your Quotes Here</h1>

    <center>
        <a href="quoteRequest.jsp" target="_self">Request a Quote</a><br><br>
        <a href="bill.jsp" target="_self">View Your Bill</a><br><br>
        <a href="login.jsp" target="_self">Logout</a><br><br>
    </center>

</body>
</html>
