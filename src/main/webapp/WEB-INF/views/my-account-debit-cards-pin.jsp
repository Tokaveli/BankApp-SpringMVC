<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="parts/top.jsp"/>

<div class="page my-account-debit-cards-pin text-center">
	<div class="hero"></div>
	<div class="container">		
		<form action="<c:url value="/my-account/debit-cards/pin"/>" method="POST" class="my-account-debit-cards-pin-form">		
			<div class="form-group <c:if test="${not empty oldPinError}">has-error</c:if>">
				<p class="error text-left">${oldPinError}</p>
				<p class="text-left">Stary kod PIN:</p>
				<input type="password" class="form-control" name="oldPin" value="${oldPin}" placeholder="Stary kod PIN" autocomplete="off">
			</div>
			
			<div class="form-group <c:if test="${not empty newPinError}">has-error</c:if>">
				<p class="error text-left">${newPinError}</p>
				<p class="text-left">Nowy kod PIN:</p>
				<input type="password" class="form-control" name="newPin" value="${newPin}" placeholder="Nowy kod PIN" autocomplete="off">
			</div>
			
			<div class="form-group <c:if test="${not empty newPinRepeatError}">has-error</c:if>">
				<p class="error text-left">${newPinRepeatError}</p>
				<p class="text-left">Powtórz nowy kod PIN:</p>
				<input type="password" class="form-control" name="newPinRepeat" value="${newPinRepeat}" placeholder="Powtórz nowy kod PIN" autocomplete="off">
			</div>
		
			<input type="submit" class="btn btn-primary my-account-debit-cards-pin-submit" value="Zmień kod PIN">
			
			<a class="btn btn-primary" href="<c:url value="/my-account"/>" role="button">Anuluj</a>
		</form>
	</div>
</div>

<jsp:include page="parts/bottom.jsp"/>