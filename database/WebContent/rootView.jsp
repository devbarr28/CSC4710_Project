<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Root page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            margin-top: 20px;
        }
        table {
            margin: 20px auto;
            border-collapse: collapse;
            width: 80%;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ddd;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        caption, h1 {
            margin-top: 20px;
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
    <form action="initialize">
        <input type="submit" value="Initialize the Database"/>
    </form>
    <a href="login.jsp" target="_self">Logout</a><br><br>

    <h1>List all users</h1>
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Users</h2></caption>
            <tr>
                <th>User name</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Password</th>
                <th>Role</th>
                <th>Credit Card</th>
                <th>Address</th>
                <th>Phone Number</th>
            </tr>
            <c:forEach var="Users" items="${listUser}">
                <tr style="text-align:center">
                    <td><c:out value="${Users.username}" /></td>
                    <td><c:out value="${Users.firstName}" /></td>
                    <td><c:out value="${Users.lastName}" /></td>
                    <td><c:out value="${Users.password}" /></td>
                    <td><c:out value="${Users.role}" /></td>
                    <td><c:out value="${Users.creditCard}" /></td>
                    <td><c:out value="${Users.address}" /></td>
                    <td><c:out value="${Users.phoneNumber}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<h1>Big Clients</h1>
<table border="1" cellpadding="6">
    <tr>
        <th>Client ID</th>
        <th>Name</th>
        <th>Cut Trees Count</th>
    </tr>
    <c:forEach var="client" items="${bigClients}">
        <tr>
            <td>${client.id}</td>
            <td>${client.name}</td>
            <td>${client.cutTreesCount}</td>
        </tr>
    </c:forEach>
</table>

<!-- Easy Clients Table -->
<h1>Easy Clients</h1>
<table border="1" cellpadding="6">
    <tr>
        <th>Client ID</th>
        <th>Name</th>
    </tr>
    <c:forEach var="client" items="${easyClients}">
        <tr>
            <td>${client.id}</td>
            <td>${client.name}</td>
        </tr>
    </c:forEach>
</table>

<!-- One Tree Quotes Table -->
<h1>One Tree Quotes</h1>
<table border="1" cellpadding="6">
    <tr>
        <th>Quote ID</th>
        <th>Client Name</th>
        <th>Notes</th>
    </tr>
    <c:forEach var="quote" items="${oneTreeQuotes}">
        <tr>
            <td>${quote.id}</td>
            <td>${quote.clientName}</td>
            <td>${quote.notes}</td>
        </tr>
    </c:forEach>
</table>

<!-- Prospective Clients Table -->
<h1>Prospective Clients</h1>
<table border="1" cellpadding="6">
    <tr>
        <th>Client ID</th>
        <th>Name</th>
    </tr>
    <c:forEach var="client" items="${prospectiveClients}">
        <tr>
            <td>${client.id}</td>
            <td>${client.name}</td>
        </tr>
    </c:forEach>
</table>

<!-- Highest Trees Table -->
<h1>Highest Trees</h1>
<table border="1" cellpadding="6">
    <tr>
        <th>Tree ID</th>
        <th>Height</th>
    </tr>
    <c:forEach var="tree" items="${highestTrees}">
        <tr>
            <td>${tree.id}</td>
            <td>${tree.height}</td>
        </tr>
    </c:forEach>
</table>

<!-- Overdue Bills Table -->
<h1>Overdue Bills</h1>
<table border="1" cellpadding="6">
    <tr>
        <th>Bill ID</th>
        <th>Amount</th>
    </tr>
    <c:forEach var="bill" items="${overdueBills}">
        <tr>
            <td>${bill.id}</td>
            <td>${bill.amount}</td>
        </tr>
    </c:forEach>
</table>

<!-- Bad Clients Table -->
<h1>Bad Clients</h1>
<table border="1" cellpadding="6">
    <tr>
        <th>Client ID</th>
        <th>Name</th>
    </tr>
    <c:forEach var="client" items="${badClients}">
        <tr>
            <td>${client.id}</td>
            <td>${client.name}</td>
        </tr>
    </c:forEach>
</table>

<!-- Good Clients Table -->
<h1>Good Clients</h1>
<table border="1" cellpadding="6">
    <tr>
        <th>Client ID</th>
        <th>Name</th>
    </tr>
    <c:forEach var="client" items="${goodClients}">
        <tr>
            <td>${client.id}</td>
            <td>${client.name}</td>
        </tr>
    </c:forEach>
</table>

<!-- Statistics Table -->
<h1>Statistics</h1>
<table border="1" cellpadding="6">
    <tr>
        <th>Client ID</th>
        <th>Total Trees</th>
        <th>Total Due Amount</th>
        <th>Total Paid Amount</th>
        <th>Date of Work</th>
    </tr>
    <c:forEach var="stat" items="${statistics}">
        <tr>
            <td>${stat.clientId}</td>
            <td>${stat.totalTrees}</td>
            <td>${stat.totalDueAmount}</td>
            <td>${stat.totalPaidAmount}</td>
            <td>${stat.dateOfWork}</td>
        </tr>
    </c:forEach>
</table>
	

</body>
</html>
