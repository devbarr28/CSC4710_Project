<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>David Smith home page</title>
</head>
<body>
    <center>
        <h1>Welcome David!</h1>
        <h2>Quotes:</h2>
        <div align="center">
            <p>${errorOne}</p>
            <p>${errorTwo}</p>

            <!-- Display quotes in a table -->
            <table border="1" cellpadding="5">
                <tr>
                    <th>Quote ID</th>
                    <th>Client Name</th>
                    <th>Notes</th>
                    <th>Action</th>
                </tr>
                
                <c:forEach var="quote" items="${quotesList}">
                    <tr>
                        <td>${quote.id}</td>
                        <td>${quote.clientName}</td>
                        <td>${quote.notes}</td>
                        <td>
                            <form action="acceptQuote" method="post">
                                <input type="hidden" name="quoteId" value="${quote.id}" />
                                <input type="submit" value="Accept" />
                            </form>
                            <form action="rejectQuote" method="post">
                                <input type="hidden" name="quoteId" value="${quote.id}" />
                                <input type="submit" value="Reject" />
                            </form>
                            <form action="counterRequest.jsp" method="get">
                                <input type="hidden" name="quoteId" value="${quote.id}" />
                                <input type="submit" value="Counter Request" />
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>

            <form action="submitRequest" method="post" enctype="multipart/form-data">
                <br>
                <input type="submit" value="Submit Request"/>
            </form>

            <br>
            <a href="login.jsp" target="_self">Logout</a><br><br>
            <a href="clientView.jsp" target="_self">Return to Client View Page</a>
        </div>
    </center>
</body>
</html>
