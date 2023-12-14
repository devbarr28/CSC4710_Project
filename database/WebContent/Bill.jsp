<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Client Bill</title>
</head>
<body>
    <div align="center">
        <h2>Client Bill</h2>

        <table border="1" cellpadding="5">
            <thead>
                <tr>
                    <th>Bill ID</th>
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
                        <td>${bill.getBillID()}</td>
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
        <br>
        <a href="clientView.jsp" target="_self">Return to Client Home</a>
    </div>
</body>
</html>
