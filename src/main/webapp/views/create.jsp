<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<form:form action="save" modelAttribute="user">
	name:<form:input  path="first_name"/><br>
	lastname:<form:input  path="last_name"/><br>
	street:<form:input  path="street"/><br>
	address:<form:input  path="address"/><br>
	city:<form:input  path="city"/><br>
	state:<form:input  path="state"/><br>
	email:<form:input  path="email"/><br>
	phone:<form:input  path="phone"/><br>
	
	submit<input type="submit">
	</form:form>
	
	
	
	
</body>
</html>