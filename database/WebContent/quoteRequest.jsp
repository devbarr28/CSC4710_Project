<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Quote Request Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
        }
        h1 {
            text-align: center;
        }
        table {
            width: 60%;
            margin: 20px auto;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            text-align: center;
            border: 1px solid #ddd;
        }
        input[type="text"], input[type="file"] {
            width: 90%;
            padding: 8px;
            margin: 8px 0;
            box-sizing: border-box;
        }
        input[type="Submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 20%;
            margin-top: 10px;
        }
        a {
            display: block;
            text-align: center;
            margin-top: 10px;
            color: #4CAF50;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <h1>Fill Out Your Quote Request Here</h1>
    <div align="center">
        <p>${errorOne}</p>
        <p>${errorTwo}</p>
        <form action="submitRequest">
            <table border="1" cellpadding="5">
                <tr>
                    <th>Tree Size:</th>
                    <td align="center" colspan="3">
                        <input type="text" name="treeSize" value="" onfocus="this.value=''" />
                    </td>
                </tr>
                <tr>
                    <th>Tree Height:</th>
                    <td align="center" colspan="3">
                        <input type="text" name="treeHeight" value="" onfocus="this.value=''" />
                    </td>
                </tr>
                <tr>
                    <th>Proximity to House:</th>
                    <td align="center" colspan="3">
                        <input type="text" name="houseProximity" value="" onfocus="this.value=''" />
                    </td>
                </tr>
                <tr>
                    <th>Tree Images:</th>
                    <td align="center" colspan="3">
                        <input type="file" name="images" value="" onfocus="this.value=''" />
                        <input type="file" name="images" value="" onfocus="this.value=''" />
                        <input type="file" name="images" value="" onfocus="this.value=''" />
                    </td>
                </tr>
                <tr>
                    <th>Any Notes for David:</th>
                    <td align="center" colspan="3">
                        <input type="text" name="notes" value="" onfocus="this.value=''" />
                    </td>
                </tr>
                <tr>
                    <td align="center" colspan="5">
                        <input type="Submit" value="Submit" />
                    </td>
                </tr>
            </table>
            <a href="clientView.jsp" target="_self">Return to Client View Page</a>
        </form>
    </div>
</body>
</html>
