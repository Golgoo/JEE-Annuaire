<%@ include file="/WEB-INF/jsp/includes/include.jsp"%>
<%@ taglib prefix="url-builder" uri="url_builder"%>
<%@ taglib prefix="sb" tagdir="/WEB-INF/tags"%>

<c:set var="group_list_route">
	<url-builder:group_list />
</c:set>
<c:url var="group_list_url" value="${group_list_route }"/>
<c:set var="person_list_route">
	<url-builder:person_list />
</c:set>
<c:url var="person_list_url" value="${person_list_route }"/>

<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="/"><spring:message code="others.Annuaire"/></a>
		</div>
		<ul class="nav navbar-nav">
			<li class="nav-item"><a class="nav-link" href="${group_list_url }"><spring:message code="group.Groupes"/></a></li>
			<li class="nav-item"><a class="nav-link" href="${person_list_url }"><spring:message code="person.Personnes"/></a></li>
		</ul>
		
		
		
		<ul class="nav navbar-nav navbar-right">
			<li class="nav-item dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"><spring:message code="others.locale"/> <span class="caret"></span></a>
				<ul class="dropdown-menu">
					<c:set var="en_url"> <sb:set_parameter name="language" value="en"></sb:set_parameter> </c:set>
					<li class="nav-item"><a class="nav-link"
						href="${en_url }">EN</a></li>
					<c:set var="fr_url"> <sb:set_parameter name="language" value="fr"></sb:set_parameter> </c:set>
					<li class="nav-item"><a class="nav-link"
						href="${fr_url }">FR</a></li>
				</ul>
			</li>
		</ul>
		
		
		
		<ul class="nav navbar-nav navbar-right annuaire-nav-droite">
			<c:choose>
				<c:when test="${isLogged == true}">
					<c:set var="logout_base_url">
						<url-builder:logout />
					</c:set>
					<c:url var="logout_url" value="${logout_base_url }" />
					<li class="nav-item"><a class="nav-link" href="${logout_url }"> Logout<span
							class="glyphicon glyphicon-log-in"></span></a></li>
				</c:when>
				<c:otherwise>
					<c:set var="login_base_url">
						<url-builder:login />
					</c:set>
					<c:url var="login_url" value="${login_base_url }" />
					<li class="nav-item"><a class="nav-link" href="${login_url }"><span
							class="glyphicon glyphicon-log-in"></span> Login</a></li>
				</c:otherwise>
			</c:choose>

		</ul>
		
		<ul class="nav navbar-nav navbar-right">	
			<sb:search_bar/>
		</ul>
		

	</div>
</nav>
