<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/includes/include.jsp"%>
<%@ taglib prefix="url-builder" uri="url_builder"%>

<html>
<head>
	<%@include file="/WEB-INF/jsp/includes/head-bootstrap.jsp"%>
	<link href="<c:url value="/annuaire.css" />" rel="stylesheet">
	<title><spring:message code="person.PersonEdition"/></title>
</head>

<body>
	<%@ include file="/WEB-INF/jsp/includes/body-menu.jsp"%>
<div class="main">
	<c:set var="submit_value"><spring:message code="others.Enregistrer"/></c:set>
	<div class="container">
		<h1><spring:message code="person.ProfileEdition"/></h1>

		<form:form method="POST" modelAttribute="person">

			<form:errors path="*" cssClass="alert alert-danger" element="div" />

			<div class="form-group">
				<label for="firstName"><spring:message code="person.Prenom"/>:</label>
				<form:input class="form-control" path="firstName" />
				<form:errors path="firstName" cssClass="alert alert-warning"
					element="div" />
			</div>
			<div class="form-group">
				<label for="lastName"><spring:message code="person.NomDeFamille"/>:</label>
				<form:input class="form-control" path="lastName" />
				<form:errors path="lastName" cssClass="alert alert-warning"
					element="div" />
			</div>
			<div class="form-group">
				<label for="website"><spring:message code="person.SiteWeb"/>:</label>
				<form:input class="form-control" path="website" />
				<form:errors path="website" cssClass="alert alert-warning"
					element="div" />
			</div>
			<div class="form-group">
				<label for="birthDay"><spring:message code="person.DateNaissance"/>:</label>
				<form:input class="form-control" path="birthDay" type="date" value="${person.birthDay }"/>
				<form:errors path="birthDay" cssClass="alert alert-warning"
					element="div" />
			</div>
			<div class="form-group">
				<label for=group><spring:message code="group.Groupe"/>:</label>
				<form:select path="group" multiple="false" class="form-control">
					<c:forEach items="${groups }" var="group">
						<c:choose>
							<c:when test="${group.key eq person.group.id}">
								<form:option value="${group.key }" selected="true">${group.value.nom }</form:option>
							</c:when>
							<c:otherwise>
								<form:option value="${group.key }">${group.value.nom }</form:option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</form:select>
				<form:errors path="group" cssClass="alert alert-warning"
					element="div" />
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-info">${submit_value }</button>
			</div>
			<c:set var="person_page_base"> <url-builder:person_page/> </c:set>
			<c:url var="person_page_url" value="${person_page_base }">
				<c:param name="id">${person.id }</c:param>
			</c:url>
			<div class="form-group">
				<a href="${person_page_url }" class="btn btn-warning"><spring:message code="others.Annuler"/> </a>
			</div>
		</form:form>
	</div>
</div>
</body>
</html>