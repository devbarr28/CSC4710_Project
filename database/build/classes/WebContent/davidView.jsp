<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>David Smith Home Page</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            text-align: center;
        }

        h1, h2 {
            color: #333;
        }

        table {
            margin-top: 20px;
            border-collapse: collapse;
            width: 80%;
            margin-left: auto;
            margin-right: auto;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #3498db;
            color: #fff;
        }

        form {
            display: inline-block;
        }

        input[type="submit"] {
            background-color: #3498db;
            color: #fff;
            border: none;
            padding: 5px 10px;
            cursor: pointer;
            border-radius: 3px;
        }

        input[type="submit"]:hover {
            background-color: #2980b9;
        }

        a {
            display: inline-block;
            margin-top: 20px;
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

            <br>
            <a href="counterRequest.jsp" target="_self">Make a Counter Request</a><br><br>
            <a href="billList.jsp" target="_self">View Clients Bills</a><br><br>
            <a href="login.jsp" target="_self">Logout</a><br><br>
        </div>
    </center>
</body>
</html>
