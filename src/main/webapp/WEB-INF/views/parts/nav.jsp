<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav>
	<a href="<c:url value="/offer"/>">Oferta</a>
	
	<c:choose>
	    <c:when test="${sessionScope.authenticated}">
	    	<a href="<c:url value="/my-account"/>">Moje konto</a>
	        <a href="<c:url value="/logout"/>">Wyloguj się</a>
	    </c:when>    
	    <c:otherwise>
	    	<a href="<c:url value="/login"/>">Logowanie</a>
	    </c:otherwise>
	</c:choose>
	
	<a href="<c:url value="/contact"/>">Kontakt</a>
	<a href="<c:url value="/about-us"/>">O nas</a>
</nav>