<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="url" required="true" description="url"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<c:url var="find_url" value="${url }" />
<c:set var="find_inter">
	<spring:message code="others.Chercher" />
</c:set>
<form action="${find_url }" class="navbar-form navbar-right annuaire-search" role="search">
	<div class="input-group">
		<input name="pattern" type="text" class="form-control" placeholder="${find_inter }" />
		<span class="input-group-btn">
				<button type="submit" class="btn btn-default">
					<span class="glyphicon glyphicon glyphicon-search"
						aria-hidden="true"></span>
				</button>
		</span>
	</div>
</form>