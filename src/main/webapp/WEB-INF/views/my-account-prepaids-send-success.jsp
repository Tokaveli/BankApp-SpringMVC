<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="parts/top.jsp"/>

<div class="page my-account-prepaids-send-success text-center">
	<div class="hero"></div>
	<div class="container">
			
		<h1>Pomyślnie zasiliłeś konto telefonu</h1>
		
		<br>
		<br>
		
		<p>Na podany numer wysłaliśmy do Ciebie wiadomość SMS.</p>
	
		
		<br>
		
		<h4>Dane doładowania są następujące:</h4>
		
		<p>Numer telefonu: <strong>${phoneNumber}</strong></p>
		<p>Operator Sieci: <strong>${phoneOperator}</strong></p>
		<p>Kwota: <strong>${amount} zł</strong></p>
		<fmt:parseDate value="${sendDate}" pattern="yyyy-MM-dd" var="sendDateParsed"/>
		<p>Data wysłania: <strong><fmt:formatDate value="${sendDateParsed}" pattern="dd.MM.yyyy"/></strong></p>
		
		<br>
		<br>
		
		<a class="btn btn-primary" href="<c:url value="/my-account/prepaids/send"/>" role="button">Doładowanie telefonu</a>
		<a class="btn btn-primary" href="<c:url value="/my-account"/>" role="button">Moje konto</a>
		
	</div>
</div>
<jsp:include page="parts/bottom.jsp"/>