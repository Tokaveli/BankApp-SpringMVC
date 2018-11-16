<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="parts/top.jsp"/>

<div class="page my-account-delete text-center">
	<div class="hero"></div>
	<div class="container">		
		<h1>Podaj nr konta na które chcesz przelać pozostałe środki.
		Danej operacji nie będzie można cofnąć!</h1>
		<form action="<c:url value="/my-account/delete"/>" method="POST" class="my-account-delete-form">		
			
			<div class="form-group <c:if test="${not empty transferValidate.accountNumberError}">has-error</c:if>">
				<p class="error text-left">${transferValidate.accountNumberError}</p>
				<p class="text-left">Numer rachunku:</p>
				<input type="number" class="form-control" name="accountNumber" value="${transfer.accountNumber}" placeholder="Numer rachunku" autocomplete="off">
			</div>
			<input type="submit" class="btn btn-primary my-account-delete-submit" value="Wyślij przelew i usuń konto">
			
			<a class="btn btn-primary" href="<c:url value="/my-account"/>" role="button">Anuluj</a>
		</form>
	</div>
</div>

<jsp:include page="parts/bottom.jsp"/>