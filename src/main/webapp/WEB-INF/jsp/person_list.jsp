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
<title><spring:message code="others.PersonnesListe" /></title>
</head>

<body>
	<%@ include file="/WEB-INF/jsp/includes/body-menu.jsp"%>
<div class="main">
	<c:set var="find_url">
		<url-builder:person_list />
	</c:set>
	<sb:search_bar url="${find_url }"></sb:search_bar>
	
	<c:set var="id_asc_param"> <sb:set_parameter name="sort" value="id_asc"/></c:set>
	<c:set var="id_desc_param"> <sb:set_parameter name="sort" value="id_desc"/></c:set>
	<c:set var="lastName_asc_param"> <sb:set_parameter name="sort" value="lastName_asc"/></c:set>
	<c:set var="lastName_desc_param"> <sb:set_parameter name="sort" value="lastName_desc"/></c:set>
	<c:set var="firstName_asc_param"> <sb:set_parameter name="sort" value="firstName_asc"/></c:set>
	<c:set var="firstName_desc_param"> <sb:set_parameter name="sort" value="firstName_desc"/></c:set>
	
	<c:set var="person_base_url">
		<url-builder:person_page />
	</c:set>
	<br />
	<table class="table table-striped">
		<thead>
			<tr>
				<th scope="col">
					<spring:message code="others.id"/>
					<a href="${id_asc_param }"><span class="glyphicon glyphicon-chevron-down"></span></a>
					<a href="${id_desc_param }"><span class="glyphicon glyphicon-chevron-up"></span></a>
        		</th>
				<th scope="col">
					<spring:message code="others.NomDeFamille"/>
					<a href="${lastName_asc_param }"><span class="glyphicon glyphicon-chevron-down"></span></a>
					<a href="${lastName_desc_param }"><span class="glyphicon glyphicon-chevron-up"></span></a>
				</th>
				<th scope="col">
					<spring:message code="others.Prenom"/>
					<a href="${firstName_asc_param }"><span class="glyphicon glyphicon-chevron-down"></span></a>
					<a href="${firstName_desc_param }"><span class="glyphicon glyphicon-chevron-up"></span></a>
				</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${persons }" var="person">
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