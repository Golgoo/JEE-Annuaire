<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<%@ taglib prefix="url-builder" uri="url_builder"%>

<html>
<head>
	<%@include file="/WEB-INF/jsp/includes/head-bootstrap.jsp" %>
	<link href="<c:url value="/annuaire.css" />" rel="stylesheet">
	<title><spring:message code="others.Personne" /></title>
</head>

<body>
	<%@ include file="/WEB-INF/jsp/includes/body-menu.jsp" %>
	<div class="main">
	<h2>
		<c:out value="${person.firstName}"/>
	</h2>
		
	<h3> <spring:message code="others.Email" /> : ${person.email } </h3>
	<p> <spring:message code="others.Groupe" /> : ${person.group.nom }</p>
	
	<c:if test="${editable == true }">
		<c:set var="edit_base_url"> <url-builder:person_edit/> </c:set>
		<c:url var="edit_url" value="${edit_base_url }">
			<c:param name="id">${person.id }</c:param>
		</c:url>
		<a href="${edit_url }"><spring:message code="others.Editer" /></a>
	</c:if>
	</div>
</body>
</html>