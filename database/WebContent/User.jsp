<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User</title>
</head>

<a href="login.jsp"target ="_self" > logout</a><br><br> 

<h1>List all users</h1>
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Users</h2></caption>
            <tr>
                <th>Username</th>
                <th>Password</th>
                <th>Role</th>
                <th>Credit Card</th>
                <th>Address</th>
                <th>Phone Number</th>
                
            </tr>
            <c:forEach var="user" items="${get_user}">
                <tr style="text-align:center">
                    <td>"${Users.username}" </td>
                    <td>"${Users.password}"</td>
                    <td>"${Users.role}"</td>
                    <td>"${Users.creditCard}"</td>
                    <td>"${Users.address}"</td>
                    <td>"${Users.phoneNumber}"</td>
                 </tr>
            </c:forEach>
          </table>
	</div>
<body>

</body>
</html>
