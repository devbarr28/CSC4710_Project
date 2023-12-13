<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Counter Request Page</title>
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

        p {
            color: #777;
        }

        table {
            margin: 20px auto;
            border-collapse: collapse;
            width: 50%;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        form {
            margin: 20px auto;
            width: 50%;
        }

        input[type="text"], textarea {
            width: 100%;
            padding: 8px;
            margin: 5px 0 20px 0;
            display: inline-block;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #3498db;
            color: #fff;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
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
