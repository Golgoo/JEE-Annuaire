<%@ include file="/WEB-INF/jsp/includes/include.jsp"%>
<%@ taglib prefix="url-builder" uri="url_builder"%>
<%@ taglib prefix="sb" tagdir="/WEB-INF/tags"%>

<c:set var="group_list_route">
	<url-builder:group_list />
</c:set>
<c:url var="group_list_url" value="${group_list_route }" />
<c:set var="person_list_route">
	<url-builder:person_list />
</c:set>
<c:url var="person_list_url" value="${person_list_route }" />
<c:set var="en_url">
	<sb:set_parameter name="language" value="en"></sb:set_parameter>
</c:set>
<c:set var="fr_url">
	<sb:set_parameter name="language" value="fr"></sb:set_parameter>
</c:set>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="navbar-header">
		<a class="navbar-brand" href="/"><spring:message
				code="others.Annuaire" /></a>
	</div>
	<div class="collapse navbar-collapse">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item"><a class="nav-link"
				href="${group_list_url }"><spring:message code="group.Groupes" /></a>
			</li>
			<li class="nav-item"><a class="nav-link"
				href="${person_list_url }"><spring:message
						code="person.Personnes" /></a>
			</li>
		</ul>

		<ul class="navbar-nav ml-auto annuaire-nav-droite">
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="#"
					id="navbarDropdownMenuLink" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false"> <spring:message code="others.locale" />
				</a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
					<a class="dropdown-item" href="${en_url }">EN</a> 
					<a class="dropdown-item" href="${fr_url }">FR</a>
				</div>
			</li>
			<c:choose>
				<c:when test="${isLogged == true}">
					<c:set var="logout_base_url">
						<url-builder:logout />
					</c:set>
					<c:url var="logout_url" value="${logout_base_url }" />
					<li class="nav-item annuaire-login">
						<a class="nav-link" href="${logout_url }">
							Logout
							<i class="fa fa-sign-in" aria-hidden="true"></i>
						</a>
					</li>
				</c:when>
				<c:otherwise>
					<c:set var="login_base_url">
						<url-builder:login />
					</c:set>
					<c:url var="login_url" value="${login_base_url }" />
					<li class="nav-item annuaire-login">
						<a class="nav-link" href="${login_url }">
							<i class="fa fa-sign-in" aria-hidden="true"></i>
 							Login
 						</a>
 					</li>
				</c:otherwise>
			</c:choose>
		</ul>

		<ul class="nav navbar-nav navbar-right">
			<sb:search_bar />
		</ul>

	</div>

</nav>
