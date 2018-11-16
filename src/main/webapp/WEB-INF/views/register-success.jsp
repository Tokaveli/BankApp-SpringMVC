<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="parts/top.jsp"/>

<div class="page register-success text-center">
	<div class="container">
		<h1>Pomyślnie założyłeś swój nowy rachunek!</h1>
		
		<br>
		<br>
		
		<p>Na podany numer wysłaliśmy do Ciebie wiadomość SMS.</p>
		<p>Na podany adres email również wysłaliśmy potwierdzenie utworzenia nowego rachunku.</p>
		
		<br>
		<br>
		
		<p>Twoje dane do logowania są następujące:</p>
		<p>Login: <strong>${login}</strong></p>
		<p>Hasło: <strong>${password}</strong></p>
		
		<br>
		<br>
		
		<p>Numer Twojego rachunku: <strong>${accountNumber}</strong></p>
		
		<br>
		<br>
		
		<p>Pamiętaj, aby przy pierwszym logowaniu zmienić hasło do swojego konta.</p>
		
		<br>
		
		<a class="btn btn-primary" href="<c:url value="/login"/>" role="button">Zaloguj się</a>
	</div>
</div>

<jsp:include page="parts/bottom.jsp"/>