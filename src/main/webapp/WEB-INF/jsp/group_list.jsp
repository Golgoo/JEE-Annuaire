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
<title><spring:message code="others.GroupesListe" /></title>
</head>

<body>
	<%@ include file="/WEB-INF/jsp/includes/body-menu.jsp"%>

<div class="main">
	<c:set var="group_find_url">
		<url-builder:group_list />
	</c:set>
	<sb:search_bar url="${group_find_url }"></sb:search_bar>

	<c:set var="groupe_base_url">
		<url-builder:group_page />
	</c:set>
	<c:set var="id_asc_param"> <sb:set_parameter name="sort" value="id_asc"/></c:set>
	<c:set var="id_desc_param"> <sb:set_parameter name="sort" value="id_desc"/></c:set>
	<c:set var="name_asc_param"> <sb:set_parameter name="sort" value="name_asc"/></c:set>
	<c:set var="name_desc_param"> <sb:set_parameter name="sort" value="name_desc"/></c:set>

	<br/>
	<table class="table table-striped">
		<thead>
			<tr>
				<th scope="col">
					<spring:message code="others.id"/>
					<a href="${id_asc_param }"><span class="glyphicon glyphicon-chevron-down"></span></a>
					<a href="${id_desc_param }"><span class="glyphicon glyphicon-chevron-up"></span></a>
        		</th>
				<th scope="col">
					<spring:message code="others.Nom"/>
					<a href="${name_asc_param }"><span class="glyphicon glyphicon-chevron-down"></span></a>
					<a href="${name_desc_param }"><span class="glyphicon glyphicon-chevron-up"></span></a>
        		</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${groups }" var="group">
				<c:url var="groupe_url" value="${groupe_base_url }">
					<c:param name="id">${group.id }</c:param>
				</c:url>
			<tr>
				<th scope="row"><a href="${groupe_url }">${group.id }</a></th>
				<td><a href="${groupe_url }">${group.nom }</a></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</body>
</html>