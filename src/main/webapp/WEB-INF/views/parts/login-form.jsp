<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="<c:url value="/login"/>" method="POST" class="login-form">
	<p class="error text-left">${accountAuthentication.loginError}</p>
	
	<div class="form-group <c:if test="${not empty accountAuthentication.loginError}">has-error</c:if>">
		<div class="input-group">
			<span class="input-group-addon">
				<i class="material-icons">person</i>
			</span>
			
			<input type="text" class="form-control" name="login" placeholder="Twój login (9 cyfr)" autocomplete="off" autofocus>
		</div>
	</div>
	
	<div class="form-group <c:if test="${not empty accountAuthentication.loginError}">has-error</c:if>">
		<div class="input-group">
			<span class="input-group-addon">
				<i class="material-icons">lock</i>
			</span>
			
			<input type="password" class="form-control" name="password" placeholder="Twoje hasło">
		</div>
	</div>
	
	<input type="submit" class="btn btn-primary" value="Zaloguj się">
</form>