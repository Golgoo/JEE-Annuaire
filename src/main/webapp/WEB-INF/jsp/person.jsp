<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/includes/include.jsp"%>
<%@ taglib prefix="url-builder" uri="url_builder"%>

<html>
<head>
<%@include file="/WEB-INF/jsp/includes/head-bootstrap.jsp"%>
<link href="<c:url value="/annuaire.css" />" rel="stylesheet">
<title><spring:message code="person.Personne" /></title>
</head>

<body>
	<%@ include file="/WEB-INF/jsp/includes/body-menu.jsp"%>
	<div class="main">
		<div class="container person-cont">
			<div class="row cont">
				<div class="col-12">
					<div class="row name align-items-center h-100">
						<div class="text-center col-4 offset-2">${person.lastName}</div>
						<div class="text-center col-4 ">${person.firstName}</div>
					</div>
				</div>
			</div>
			<div class="row space"></div>
			<div class="row cont">
				<div class="col-12">
					<div class="row align-items-center h-100">
						<div class="col-1">
							<i class="fa fa-users fa-2x"></i>
						</div>
						<div class="col-4 content">${person.group.nom}</div>
						<div class="col-1 offset-1">
							<i class="fa fa-globe fa-2x"></i>
						</div>
						<div class="col-5 content">
							<i> ${person.website}</i>
						</div>
					</div>
				</div>
			</div>
			<div class="row space"></div>
			<div class="row cont">
				<div class="col-12">
					<div class="row align-items-center h-100">
						<div class="col-1">
							<i class="fa fa-envelope-square fa-2x"></i>
						</div>
						<div class="col-4">
							<c:choose>
								<c:when test="${not empty person.email}">
									${person.email }
								</c:when>
								<c:otherwise>
									<i class="fa fa-times-circle fa-2x content-hide"></i>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="col-1 offset-1">
							<i class="fa fa-birthday-cake fa-2x "></i>
						</div>
						<div class="col-5">
							<c:choose>
								<c:when test="${not empty person.birthDay }">
									${person.birthDay }
								</c:when>
								<c:otherwise>
									<i class="fa fa-times-circle fa-2x content-hide"></i>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
		</div>

		<c:if test="${editable == true }">
			<c:set var="edit_base_url">
				<url-builder:person_edit />
			</c:set>
			<c:url var="edit_url" value="${edit_base_url }">
				<c:param name="id">${person.id }</c:param>
			</c:url>
			<a href="${edit_url }"><spring:message code="others.Editer" /></a>
		</c:if>

	</div>
</body>
</html>