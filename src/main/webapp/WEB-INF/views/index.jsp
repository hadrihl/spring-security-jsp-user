<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Homepage</title>
</head>
<body>
	<h1>Welcome to spring-security-jsp-user page!</h1>
	<ul>
		<li><a href="/">home</a></li>
		
		<c:if test="${empty pageContext.request.remoteUser}">
		<li><a href="/signin">signin</a></li>
		</c:if>
		
		<li><a href="/signup">signup</a></li>
		
		<c:if test="${not empty pageContext.request.remoteUser}">
		<li>
			<form:form action="/logout" method="post">
				<button type="submit">Logout</button>
			</form:form>
		</li>
		</c:if>
	</ul>
	<footer><a href="/">spring-security-jsp-user</a> &copy; 2024. Made in Penang.</footer>
</body>
</html>