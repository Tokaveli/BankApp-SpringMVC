<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="parts/top.jsp"/>

<div class="page my-account-password text-center">
	<div class="hero"></div>
	<div class="container">		
		<form action="<c:url value="/my-account/password"/>" method="POST" class="my-account-password-form">		
			<div class="form-group <c:if test="${not empty oldPasswordError}">has-error</c:if>">
				<p class="error text-left">${oldPasswordError}</p>
				<p class="text-left">Stare hasło:</p>
				<input type="password" class="form-control" name="oldPassword" value="${oldPassword}" placeholder="Stare hasło" autocomplete="off">
			</div>
			
			<div class="form-group <c:if test="${not empty newPasswordError}">has-error</c:if>">
				<p class="error text-left">${newPasswordError}</p>
				<p class="text-left">Nowe hasło:</p>
				<input type="password" class="form-control" name="newPassword" value="${newPassword}" placeholder="Nowe hasło" autocomplete="off">
			</div>
			
			<div class="form-group <c:if test="${not empty newPasswordRepeatError}">has-error</c:if>">
				<p class="error text-left">${newPasswordRepeatError}</p>
				<p class="text-left">Powtórz nowe hasło:</p>
				<input type="password" class="form-control" name="newPasswordRepeat" value="${newPasswordRepeat}" placeholder="Powtórz nowe hasło" autocomplete="off">
			</div>
		
			<input type="submit" class="btn btn-primary my-account-password-submit" value="Zmień hasło">
			
			<a class="btn btn-primary" href="<c:url value="/my-account"/>" role="button">Anuluj</a>
		</form>
	</div>
</div>

<jsp:include page="parts/bottom.jsp"/>