<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<%@ taglib prefix="url-builder" uri="url_builder"%>

<html>
<head>
	<%@include file="/WEB-INF/jsp/includes/head-bootstrap.jsp" %>
	<link href="<c:url value="/annuaire.css" />" rel="stylesheet">
	<title><spring:message code="others.PageDuGroupe"></spring:message></title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/includes/body-menu.jsp" %>

<div class="main">
	<h2> ${group.nom }</h2>
	
	<c:set var="person_base_url">
		<url-builder:person_page />
	</c:set>
	<br />
	<table class="table table-striped">
		<thead>
			<tr>
				<th scope="col"><spring:message code="others.id"/></th>
				<th scope="col"><spring:message code="others.NomDeFamille"/></th>
				<th scope="col"><spring:message code="others.Prenom"/></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${group.members }" var="person">
				<c:url var="person_url" value="${person_base_url }">
					<c:param name="id">${person.id }</c:param>
				</c:url>
				<tr>
					<th scope="row"><a href="${person_url }">${person.id }</a></th>
					<td><a href="${person_url }">${person.lastName }</a></td>
					<td><a href="${person_url }">${person.firstName }</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</body>
</html>