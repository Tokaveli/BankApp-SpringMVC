<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="parts/top.jsp"/>

<div class="page my-account-password-success text-center">
	<div class="hero"></div>
	<div class="container">		
		<h1>Hasło zostało zmienione</h1>
		
		<br>
		
		<p>Zostaniesz wylogowany</p>
		
		<br>		
		
		<a class="btn btn-primary" href="<c:url value="/"/>" role="button">Strona główna</a>
	</div>
</div>

<jsp:include page="parts/bottom.jsp"/>