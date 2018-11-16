<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="parts/top.jsp"/>

<div class="page my-account-loans-open text-center">
	<div class="hero"></div>
	<div class="container">		
		<form action="<c:url value="/my-account/loans/open"/>" method="POST" class="my-account-loans-open-form">		
			
			<div class="form-group <c:if test="${not empty loanValidate.amountError}">has-error</c:if>">
				<p class="error text-left">${loanValidate.amountError}</p>
				<p class="text-left">Kwota (PLN):</p>
				<input type="number" class="form-control" name="amount" step="any" value="${loan.amount}" placeholder="Kwota (PLN)" autocomplete="off">
			</div>
			<div class="form-group <c:if test="${not empty loanValidate.periodError}">has-error</c:if>">
				<p class="error text-left">${loanValidate.periodError}</p>
				<p class="text-left">Okres:</p>
				<select class="form-control" name="period">
					<option value="2">2 miesiące</option>
					<option value="4">4 miesiące</option>
					<option value="6">6 miesięcy</option>
					<option value="12">1 rok</option>
					<option value="24">2 lata</option>
					<option value="36">3 lata</option>
					<option value="60">5 lat</option>
					<option value="120">10 lat</option>
					<option value="180">15 lat</option>
				</select>
			</div>
			
			<input type="submit" class="btn btn-primary my-account-loans-open-submit" value="Weź pożyczkę">
			
			<a class="btn btn-primary" href="<c:url value="/my-account/loans"/>" role="button">Anuluj</a>
		</form>
	</div>
</div>

<jsp:include page="parts/bottom.jsp"/>