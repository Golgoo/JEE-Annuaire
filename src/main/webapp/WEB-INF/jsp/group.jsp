<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<%@ taglib prefix="url-builder" uri="url_builder"%>

<html>
<head>
	<%@include file="/WEB-INF/jsp/includes/head-bootstrap.jsp" %>
	<link href="<c:url value="/annuaire.css" />" rel="stylesheet">
	<title><spring:message code="group.PageDuGroupe"></spring:message></title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/includes/body-menu.jsp" %>

<div class="main">
	<h2> ${group.nom } </h2>
	<h4> <spring:message code="group.NbrMembers"></spring:message> : ${group.members.size() }</h4>
		
	<c:set var="person_base_url">
		<url-builder:person_page />
	</c:set>
	<br />
	
	<sb:person_table persons="${group.members }"></sb:person_table>
</div>
</body>
</html>