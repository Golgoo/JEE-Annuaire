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
	
	<h4>Please select a new password : </h4>
	<form action="/lostpw" method="post">
		<label for="newpassword">Password : </label><br>
	  	<input type="password" id="newpassword" name="newpassword"><br>
	  	<input type="submit" value="Finish">
	  	<c:if test="${error == true }">
			<div class="alert alert-danger" role="alert">
			  Unable to save this new password
			</div>
		</c:if>
	</form>
	<sb:cancel_lostpassword/>
</div>
</body>

</html>