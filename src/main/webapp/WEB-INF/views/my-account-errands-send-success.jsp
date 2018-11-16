<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="parts/top.jsp"/>

<div class="page my-account-errands-send-success text-center">
	<div class="hero"></div>
	<div class="container">		
		<h1>Pomyślnie złożyłeś wniosek o zlecenie stałe</h1>
		
		<br>
		<br>
		
		<p>Na podany numer wysłaliśmy do Ciebie wiadomość SMS.</p>
		
		<br>
		<br>
		
		<h4>Dane zlecenia stałego są następujące:</h4>
		<br>
		<p>Numer rachunku: <strong>${accountNumber}</strong></p>
		<p>Kwota: <strong>${amount} zł</strong></p>
		<p>Tytuł: <strong>${title}</strong></p>
		<p>Nazwa odbiorcy: <strong>${receiverName}</strong></p>
		<p>Adres odbiorcy: <strong>${receiverAddress}</strong></p>
		<p>Okres zlecenia: <strong>${period} tygodnie</strong></p>

		<fmt:parseDate value="${sendDate}" pattern="yyyy-MM-dd" var="sendDateParsed"/>
		<p>Data wysłania: <strong><fmt:formatDate value="${sendDateParsed}" pattern="dd.MM.yyyy"/></strong></p>
		
		<br>
		<br>
		
		
		
		<a class="btn btn-primary" href="<c:url value="/my-account/errands"/>" role="button">Zlecenia stałe</a>
		<a class="btn btn-primary" href="<c:url value="/my-account"/>" role="button">Moje konto</a>
	</div>
</div>

<jsp:include page="parts/bottom.jsp"/>