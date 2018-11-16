<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="parts/top.jsp"/>

<div class="page my-account-loans-open-success text-center">
	<div class="hero"></div>
	<div class="container">		
		<h1>Udzielenie pożyczki przebiegło pomyślnie</h1>
		
		<br>		
		
		<a class="btn btn-primary" href="<c:url value="/my-account/loans"/>" role="button">Moje pożyczki</a>
		<a class="btn btn-primary" href="<c:url value="/my-account"/>" role="button">Moje konto</a>
	</div>
</div>

<jsp:include page="parts/bottom.jsp"/>