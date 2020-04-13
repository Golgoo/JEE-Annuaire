<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<%@ taglib prefix="url-builder" uri="url_builder"%>

<html>
<head>
	<%@include file="/WEB-INF/jsp/includes/head-bootstrap.jsp" %>
	<link href="<c:url value="/annuaire.css" />" rel="stylesheet">
	<title>Password recovery</title>
</head>

<body>
<%@ include file="/WEB-INF/jsp/includes/body-menu.jsp" %>
<div class="main">
	<h2>Password recovery</h2>
	
	<h4>Enter your directory's email : </h4>
	<form action="/lostpw" method="post">
		<label for="email"><spring:message code="person.Email"/> : </label><br>
	  	<input type="text" id="email" name="email"><br>
	  	<input type="submit" value="Next">
	  	<c:if test="${error == true }">
			<div class="alert alert-danger" role="alert">
			  This email do not exist in the directory
			</div>
		</c:if>
	</form>
	<sb:cancel_lostpassword/>
</div>
</body>

</html>