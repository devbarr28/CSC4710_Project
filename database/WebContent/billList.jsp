<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<html>
<head>
    <title>Bills List</title>
</head>
<body>

<h2>Bills List</h2>

<%
 
%>

<table border="1">
    <thead>
        <tr>
            <th>ID</th>
            <th>Status</th>
            <th>Order ID</th>
            <th>Price</th>
            <th>Discount</th>
            <th>Balance</th>
            <th>Note</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="bill" items="${billList}">
            <tr>
                <td>${bill.getID()}</td>
                <td>${bill.getStatus()}</td>
                <td>${bill.getOrderID()}</td>
                <td>${bill.getPrice()}</td>
                <td>${bill.getDiscount()}</td>
                <td>${bill.getBalance()}</td>
                <td>${bill.getNote()}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
