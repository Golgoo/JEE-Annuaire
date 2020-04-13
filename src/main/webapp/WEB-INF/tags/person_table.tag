<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="persons" required="true"
	description="list des personnes" type="java.util.Collection"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ taglib prefix="sb" tagdir="/WEB-INF/tags"%>

<c:set var="id_asc_param">
	<sb:set_parameter name="sort" value="id_asc" />
</c:set>
<c:set var="id_desc_param">
	<sb:set_parameter name="sort" value="id_desc" />
</c:set>
<c:set var="lastName_asc_param">
	<sb:set_parameter name="sort" value="lastName_asc" />
</c:set>
<c:set var="lastName_desc_param">
	<sb:set_parameter name="sort" value="lastName_desc" />
</c:set>
<c:set var="firstName_asc_param">
	<sb:set_parameter name="sort" value="firstName_asc" />
</c:set>
<c:set var="firstName_desc_param">
	<sb:set_parameter name="sort" value="firstName_desc" />
</c:set>

<%@ taglib prefix="url-builder" uri="url_builder"%>

<c:set var="person_base_url">
	<url-builder:person_page />
</c:set>
<br />
<table class="table table-striped">
	<thead>
		<tr>
			<th scope="col"><spring:message code="others.id" /> 
				<a href="${id_asc_param }"><span class="glyphicon glyphicon-chevron-down"></span></a>
				<a href="${id_desc_param }"><span class="glyphicon glyphicon-chevron-up"></span></a>
			</th>
			<th scope="col"><spring:message code="person.NomDeFamille" /> 
				<a href="${lastName_asc_param }"><span class="glyphicon glyphicon-chevron-down"></span></a> 
				<a href="${lastName_desc_param }"><span class="glyphicon glyphicon-chevron-up"></span></a>
			</th>
			<th scope="col"><spring:message code="person.Prenom" />
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