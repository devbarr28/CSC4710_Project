<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Counter Request Page</title>
</head>
<body>
    <center>
        <h1>Counter Request Page</h1>
        <div align="center">
            <p>${error}</p>
            
            <h2>Current Request:</h2>
            <p>Quote ID: ${currentQuote.id}</p>
            <p>Client Name: ${currentQuote.username}</p>
            <p>Request: ${currentQuote.request}</p>

            <h2>Make Counter Request:</h2>
            <form action="submitCounterRequest" method="post" enctype="multipart/form-data">
                <input type="hidden" name="quoteId" value="${currentQuote.id}" />
                
                <table border="1" cellpadding="5">
                    <tr>
                        <th>Counter Price</th>
                        <td>
                            <input type="text" name="counterPrice" value="${currentQuote.price}" />
                        </td>
                    </tr>
                    <tr>
                        <th>Notes</th>
                        <td>
                            <textarea name="counterRequest" rows="4" cols="50"></textarea>
                        </td>
                    </tr>
                </table>

                <br>
                <input type="submit" value="Submit Counter Request" />
            </form>

            <br>
            <a href="davidView.jsp" target="_self">Return to David View Page</a>
        </div>
    </center>
</body>
</html>
