<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ taglib prefix="url-builder" uri="url_builder"%>

<c:set var="cancel_url"> <url-builder:lostpw-cancel/> </c:set>
<a href="${cancel_url }">Cancel</a>