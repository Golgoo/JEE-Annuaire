<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<%@ taglib prefix="url-builder" uri="url_builder"%>

<html>
<head>
	<%@include file="/WEB-INF/jsp/includes/head-bootstrap.jsp" %>
	<link href="<c:url value="/annuaire.css" />" rel="stylesheet">
	<title>Login</title>
</head>

<body>
<%@ include file="/WEB-INF/jsp/includes/body-menu.jsp" %>
<div class="main">
	<form action="/login" method="post">
		<label for="email"><spring:message code="others.Email"/> : </label><br>
	  	<input type="text" id="email" name="email"><br>
	  	<label for="password"><spring:message code="others.MotDePass"/> : </label><br>
	  	<input type="text" id="password" name="password">
	  	<input type="submit" value="Login">
	  	<c:if test="${invalid_auth == true }">
			<div class="alert alert-danger" role="alert">
			  <spring:message code="others.IncorrectAuth"/>
			</div>
		</c:if>
	</form>
	
</div>
</body>

</html>