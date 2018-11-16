<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="parts/top.jsp"/>

<div class="page login-success text-center">
	<div class="container">
		<h1>Pomyślnie zalogowałeś się na swoje konto</h1>
		
		<br>
		<br>
		
		<p>
			<a class="btn btn-primary" href="<c:url value="/my-account"/>" role="button">Moje konto</a>
		</p>
	</div>
</div>

<jsp:include page="parts/bottom.jsp"/>