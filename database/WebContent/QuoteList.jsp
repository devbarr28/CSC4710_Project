<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE>
<html>
<head>
    <title>All User list</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
        }
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            text-align: center;
            border: 1px solid #ddd;
        }
        caption {
            font-size: 1.5em;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Quotes</h2></caption>
            <tr>
                <th>Client ID</th>
                <th>Price</th>
                <th>Scheduled Start</th>
                <th>Scheduled End</th>
            </tr>
            <c:forEach var="quoteRequest" items="${listAllQuotes}">
                <tr>
                    <td><c:out value="${quoteRequest.clientID}" /></td>
                    <td><c:out value="${quoteRequest.price}" /></td>
                    <td><c:out value="${quoteRequest.scheduleStart}" /></td>
                    <td><c:out value="${quoteRequest.scheduleEnd}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
