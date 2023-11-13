<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head><title>Quote Request Page</title></head>
<body>
 <center>	<h1> Fill Out Your Quote Request Here </h1> </center>
	<div align="center">
		<p> ${errorOne } </p>
		<p> ${errorTwo } </p>
		<form action="submitRequest">
			<table border="1" cellpadding="5">
				<tr>
					<th>Tree Size: </th>
					<td align="center" colspan="3">
						<input type="text" name="treeSize" size="135"  value=" " onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Tree Height: </th>
					<td align="center" colspan="3">
						<input type="text" name="treeHeight" size="135" value="" onfocus="this.value=''">
					</td>
	
				</tr>
				<tr>
					<th>Tree Location: </th>
					<td align="center" colspan="3"> 
						<input type="text" name="treeLocation" size="135" value="" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Proximity to House: </th>
					<td align="center" colspan="3">
						<input type="text" name="houseProximity" size="135" value="" onfocus="this.value=''">
					</td>
				
				</tr>
				<tr>
					<th>Tree Images: </th>
					<td align="center" colspan="3">
						<input type="file" name="images" size="45" value="" onfocus="this.value=''">
						<input type="file" name="images" size="45" value="" onfocus="this.value=''">
						<input type="file" name="images" size="45" value="" onfocus="this.value=''">
					</td>
				
				</tr>
				<tr>
					<th>Any Notes for David: </th>
					<td align="center" colspan="3">
						<input type="text" name="notes" size="135" value="" onfocus="this.value=''">
					</td>
				
				</tr>
				
				<tr>
					<td align="center" colspan="5">
						<input type="Submit" value="Submit"/>
					</td>
				</tr>
			</table>
			<a href="clientView.jsp" target="_self">Return to Client View Page</a>
		</form>
	</div>
</body>
