<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="parts/top.jsp"/>

<div class="page my-account-transfers-send text-center">
	<div class="hero"></div>
	<div class="container">		
		<form action="<c:url value="/my-account/transfers/send"/>" method="POST" class="my-account-transfers-send-form">		
			<div class="form-group <c:if test="${not empty transferValidate.receiverNameError}">has-error</c:if>">
				<p class="error text-left">${transferValidate.receiverNameError}</p>
				<p class="text-left">Nazwa odbiorcy:</p>
				<input type="text" class="form-control" name="receiverName" value="${transfer.receiverName}" placeholder="Nazwa odbiorcy" autocomplete="off">
			</div>
			
			<div class="form-group <c:if test="${not empty transferValidate.receiverAddressError}">has-error</c:if>">
				<p class="error text-left">${transferValidate.receiverAddressError}</p>
				<p class="text-left">Adres odbiorcy:</p>
				<input type="text" class="form-control" name="receiverAddress" value="${transfer.receiverAddress}" placeholder="Adres odbiorcy" autocomplete="off">
			</div>
			
			<div class="form-group <c:if test="${not empty transferValidate.accountNumberError}">has-error</c:if>">
				<p class="error text-left">${transferValidate.accountNumberError}</p>
				<p class="text-left">Numer rachunku:</p>
				<input type="number" class="form-control" name="accountNumber" value="${transfer.accountNumber}" placeholder="Numer rachunku" autocomplete="off">
			</div>
			
			<div class="form-group <c:if test="${not empty transferValidate.amountError}">has-error</c:if>">
				<p class="error text-left">${transferValidate.amountError}</p>
				<p class="text-left">Kwota (PLN):</p>
				<input type="number" class="form-control" name="amount" step="any" value="${transfer.amount}" placeholder="Kwota (PLN)" autocomplete="off">
			</div>
			
			<div class="form-group <c:if test="${not empty transferValidate.titleError}">has-error</c:if>">
				<p class="error text-left">${transferValidate.titleError}</p>
				<p class="text-left">Tytuł:</p>
				<input type="text" class="form-control" name="title" value="${transfer.title}" placeholder="Tytuł" autocomplete="off">
			</div>
		
			<input type="submit" class="btn btn-primary my-account-transfers-send-submit" value="Wyślij przelew">
			
			<a class="btn btn-primary" href="<c:url value="/my-account/transfers"/>" role="button">Anuluj</a>
		</form>
	</div>
</div>

<jsp:include page="parts/bottom.jsp"/>