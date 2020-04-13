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
<title><spring:message code="group.GroupesListe" /></title>
</head>

<body>
	<%@ include file="/WEB-INF/jsp/includes/body-menu.jsp"%>

<div class="main">
	<c:set var="group_find_url">
		<url-builder:group_list />
	</c:set>

	<sb:group_table groups="${groups }"></sb:group_table>
</div>
</body>
</html>