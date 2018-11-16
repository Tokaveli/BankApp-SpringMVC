<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="parts/top.jsp"/>

<div class="page my-account-errands-send text-center">
	<div class="hero"></div>
	<div class="container">		
		<form action="<c:url value="/my-account/errands/send"/>" method="POST" class="my-account-errands-send-form">		
			<div class="form-group <c:if test="${not empty errandValidate.receiverNameError}">has-error</c:if>">
				<p class="error text-left">${errandValidate.receiverNameError}</p>
				<p class="text-left">Nazwa odbiorcy:</p>
				<input type="text" class="form-control" name="receiverName" value="${errand.receiverName}" placeholder="Nazwa odbiorcy" autocomplete="off">
			</div>
			
			<div class="form-group <c:if test="${not empty errandValidate.receiverAddressError}">has-error</c:if>">
				<p class="error text-left">${errandValidate.receiverAddressError}</p>
				<p class="text-left">Adres odbiorcy:</p>
				<input type="text" class="form-control" name="receiverAddress" value="${errand.receiverAddress}" placeholder="Adres odbiorcy" autocomplete="off">
			</div>
			
			<div class="form-group <c:if test="${not empty errandValidate.accountNumberError}">has-error</c:if>">
				<p class="error text-left">${errandrValidate.accountNumberError}</p>
				<p class="text-left">Numer rachunku:</p>
				<input type="number" class="form-control" name="accountNumber" value="${errand.accountNumber}" placeholder="Numer rachunku" autocomplete="off">
			</div>
			
			<div class="form-group <c:if test="${not empty errandValidate.amountError}">has-error</c:if>">
				<p class="error text-left">${errandValidate.amountError}</p>
				<p class="text-left">Kwota (PLN):</p>
				<input type="number" class="form-control" name="amount" step="any" value="${errand.amount}" placeholder="Kwota (PLN)" autocomplete="off">
			</div>
			
			<div class="form-group <c:if test="${not empty errandValidate.titleError}">has-error</c:if>">
				<p class="error text-left">${errandValidate.titleError}</p>
				<p class="text-left">Tytuł:</p>
				<input type="text" class="form-control" name="title" value="${errand.title}" placeholder="Tytuł" autocomplete="off">
			</div>
			
					<div class="form-group <c:if test="${not empty errandValidate.periodError}">has-error</c:if>">
				<p class="error text-left">${errandValidate.periodError}</p>
				<p class="text-left">Okres zlecenia:</p>
				<select class="form-control" name="period">
					<option value="1">1 tydzień</option>
					<option value="2">2 tygodnie</option>
					<option value="3">3 tygodnie</option>
					<option value="4">4 tygodnie</option>	
				</select>
			</div>
		
			<input type="submit" class="btn btn-primary my-account-errands-send-submit" value="Wykonaj zlecenie stałe">
			
			<a class="btn btn-primary" href="<c:url value="/my-account/errands"/>" role="button">Anuluj</a>
		</form>
	</div>
</div>

<jsp:include page="parts/bottom.jsp"/>