<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<%@ taglib prefix="url-builder" uri="url_builder"%>

<html>
<head>
	<%@include file="/WEB-INF/jsp/includes/head-bootstrap.jsp" %>
	<link href="<c:url value="/annuaire.css" />" rel="stylesheet">
	<title><spring:message code="others.Annuaire"/></title>
</head>

<body>

	<%@ include file="/WEB-INF/jsp/includes/body-menu.jsp" %>
	<div class="main">
		<h1><spring:message code="others.Bienvenu"/></h1>
	</div>
</body>
</html>