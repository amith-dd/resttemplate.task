<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<table border="2px">
	<tr>
		<th>uuid</th> 
		<th>first_name</th>
		<th>last_name</th>
		<th>street</th>
		<th>address</th>
		<th>city</th>
		<th>state</th>
		<th>email</th>
		<th>phone</th>
	</tr>
	
	<c:forEach var="emp" items="${users}">
	<tr>
	<td>${emp.uuid}</td>
	<td>${emp.first_name}</td>
	<td>${emp.last_name}</td>
	<td>${emp.street}</td>
	<td>${emp.address}</td>
	<td>${emp.city}</td>
	<td>${emp.state}</td>
	<td>${emp.email}</td>
	<td>${emp.phone}</td>
	<td><a href="delete?id=${emp.uuid}">delete</a> </td>
	<td><a href="edit?id=${emp.uuid}">edit</a> </td>
	
	</tr>
	
	</c:forEach>
	
	</table> 
	
</body>
</html>