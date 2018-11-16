<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="parts/top.jsp"/>

<div class="page my-account-transfers-send-success text-center">
	<div class="hero"></div>
	<div class="container">		
		<h1>Pomyślnie wysłałeś przelew</h1>
		
		<br>
		<br>
		
		<p>Na podany numer wysłaliśmy do Ciebie wiadomość SMS.</p>
		<p>Na podany adres email również wysłaliśmy potwierdzenie wysłania przelewu.</p>
		
		<br>
		<br>
		
		<p>Dane przelewu są następujące:</p>
		<p>Numer rachunku: <strong>${accountNumber}</strong></p>
		<p>Kwota: <strong>${amount} zł</strong></p>
		<p>Tytuł: <strong>${title}</strong></p>
		<p>Nazwa odbiorcy: <strong>${receiverName}</strong></p>
		<p>Adres odbiorcy: <strong>${receiverAddress}</strong></p>

		<fmt:parseDate value="${sendDate}" pattern="yyyy-MM-dd" var="sendDateParsed"/>
		<p>Data wysłania: <strong><fmt:formatDate value="${sendDateParsed}" pattern="dd.MM.yyyy"/></strong></p>
		
		<br>
		<br>
		
		<p>Pieniądze zostaną wysłane przy najbliższej sesji</p>
		
		<br>		
		
		<a class="btn btn-primary" href="<c:url value="/my-account/transfers"/>" role="button">Przelewy</a>
		<a class="btn btn-primary" href="<c:url value="/my-account"/>" role="button">Moje konto</a>
	</div>
</div>

<jsp:include page="parts/bottom.jsp"/>