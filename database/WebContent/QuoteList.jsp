<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>All User list</title>
</head>
<body>
   <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of People</h2></caption>
            <tr>
				<th>Client ID</th>
                <th>Price</th>
                <th>scheduled Start</th>
                <th>Scheduled End</th>
            </tr>
            <c:forEach var="Users" items="${listAllQuotes}">
                <tr style="text-align:center">
                    <td><c:out value="${QuoteRequest.clientID}" /></td>
                    <td><c:out value="${QuoteRequest.price}" /></td>
                    <td><c:out value="${QuoteRequest.scheduleStart}" /></td>
                    <td><c:out value="${QuoteRequest.scheduleEnd}" /></td>
                   
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>
