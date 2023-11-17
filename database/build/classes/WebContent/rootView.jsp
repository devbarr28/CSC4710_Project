<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Root page</title>
</head>
<body>

<div align = "center">
	
	<form action = "initialize">
		<input type = "submit" value = "Initialize the Database"/>
	</form>
	<a href="login.jsp"target ="_self" > logout</a><br><br> 

<h1>List all users</h1>
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Users</h2></caption>
            <tr>
                <th>User name</th>
                <th>First Name</th>
                <th>Last Name
                <th>Password</th>
                <th>Role</th>
                <th>Credit Card</th>
                <th>Address</th>
                <th>Phone Number</th>
            </tr>
            <c:forEach var="Users" items="${listUsers}">
                <tr style="text-align:center">
                    <td><c:out value="${Users.username}" /></td>
                    <td><c:out value="${Users.firstName}" /></td>
                    <td><c:out value="${Users.lastName}" /></td>
                    <td><c:out value="${Users.password}" /></td>
                    <td><c:out value="${Users.role}" /></td>
                    <td><c:out value="${Users.creditCard}" /></td>
                    <td><c:out value="${Users.address}" /></td>
                    <td><c:out value="${Users.phoneNumber}" /></td>
            </c:forEach>
        </table>
	</div>
	</div>

</body>
</html>
