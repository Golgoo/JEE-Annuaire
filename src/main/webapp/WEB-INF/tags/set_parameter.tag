<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="name" required="true" description="nom du paramètre"%>
<%@ attribute name="value" required="true" description="valeur du paramètre"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

 <%-- https://stackoverflow.com/questions/33240320/adding-new-parameter-to-current-url-using-jstl  --%>

<c:url value="${requestScope['javax.servlet.forward.request_uri']}">

<c:forEach items="${paramValues}" var="p">
	<c:choose>
		<c:when test="${p.key == name}">
			<c:param name="${name }" value="${value }"/>
		</c:when>
		<c:otherwise>
			<c:forEach items="${p.value}" var="val">
           		<c:param name="${p.key}" value="${val}"/>
            </c:forEach>
		</c:otherwise>
	</c:choose>
</c:forEach>

<c:if test="${empty param[name] }">
	<c:param name="${name }" value="${value }"/>
</c:if>

</c:url>