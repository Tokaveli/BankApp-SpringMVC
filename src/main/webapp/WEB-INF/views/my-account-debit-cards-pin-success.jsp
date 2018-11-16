<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="parts/top.jsp"/>

<div class="page my-account-debit-cards-pin-success text-center">
	<div class="hero"></div>
	<div class="container">		
		<h1>Kod PIN został zmieniony</h1>
		
		<br>		
		
		<a class="btn btn-primary" href="<c:url value="/my-account"/>" role="button">Moje konto</a>
	</div>
</div>

<jsp:include page="parts/bottom.jsp"/>