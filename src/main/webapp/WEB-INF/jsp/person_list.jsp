<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/includes/include.jsp"%>
<%@ taglib prefix="url-builder" uri="url_builder"%>
<%@ taglib prefix="sb" tagdir="/WEB-INF/tags"%>

<html>
<head>
<%@include file="/WEB-INF/jsp/includes/head-bootstrap.jsp"%>
<link href="<c:url value="/annuaire.css" />" rel="stylesheet">
<title><spring:message code="person.PersonnesListe" /></title>
</head>

<body>
	<%@ include file="/WEB-INF/jsp/includes/body-menu.jsp"%>
<div class="main">

	<h2> <spring:message code="others.PersonList1"/>Liste des personnes de l'annuaires </h2>
	
	<sb:person_table persons="${persons }"></sb:person_table>
	
</div>
</body>
</html>