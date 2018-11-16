<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="parts/top.jsp"/>

<div class="page home text-center">
	<div class="hero"></div>
	<div class="container">
		<div class="welcome">			
			<h2>Witamy w naszym banku!</h2>
			
			<br>
			
			<c:choose>
			    <c:when test="${sessionScope.authenticated}">
			    	<br>
			    
			    	<a class="btn btn-primary" href="<c:url value="/my-account"/>" role="button">Moje konto</a>
			    </c:when>    
			    <c:otherwise>					
					<h1>Skorzystaj z naszej atrakcyjnej <a href="<c:url value="/offer"/>">oferty</a>.</h1>
					
					<br>
					<br>
					
					<p>Jesteś już naszym klientem?</p>
					
					<br>
					
					<a class="btn btn-primary" href="<c:url value="/login"/>" role="button">Zaloguj się</a>
			    </c:otherwise>
			</c:choose>
			
			
		</div>
	</div>
</div>

<jsp:include page="parts/bottom.jsp"/>