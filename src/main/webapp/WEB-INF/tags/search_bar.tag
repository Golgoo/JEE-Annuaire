<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>



<c:url var="current_url" value="${requestScope['javax.servlet.forward.request_uri']}"/>

<c:set var="find_inter">
	<spring:message code="others.Chercher" />
</c:set>
<form action="${current_url }" class="navbar-form navbar-right annuaire-search" role="search">
	<div class="input-group">
		<c:forEach items="${paramValues}" var="p">
			<c:choose>
				<c:when test="${p.key == 'pattern'}">
				</c:when>
				<%-- do not work with matrix parameters ( only the first one is retain ) --%>
				<c:otherwise>
					<input type="hidden" name="${p.key }" value="${p.value[0] }"/>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<input name="pattern" type="text" class="form-control" placeholder="${find_inter }" />
		
		<span class="input-group-btn">
				<button type="submit" class="btn btn-default">
					<span class="glyphicon glyphicon glyphicon-search"
						aria-hidden="true"></span>
				</button>
		</span>
	</div>
</form>