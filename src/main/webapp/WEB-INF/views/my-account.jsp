<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="parts/top.jsp"/>

<div class="page my-account text-center">
	<div class="hero"></div>
	<div class="container">
		<h2>Operacje</h2>
	
		<br>
	
		<a class="btn btn-primary" href="<c:url value="/my-account/transfers"/>" role="button">Przelewy</a>
		<a class="btn btn-primary" href="<c:url value="/my-account/deposits"/>" role="button">Moje lokaty</a>
		<a class="btn btn-primary" href="<c:url value="/my-account/password"/>" role="button">Zmień hasło</a>
		<a class="btn btn-primary" href="<c:url value="/my-account/debit-cards/pin"/>" role="button">Zmień kod PIN</a>
		<a class="btn btn-primary" href="<c:url value="/my-account/prepaids/send"/>" role="button">Doladowanie telefonu</a>
		<a class="btn btn-primary" href="<c:url value="/my-account/errands"/>" role="button">Zlecenia stałe</a>
		<a class="btn btn-primary" href="<c:url value="/my-account/loans"/>" role="button">Pożyczki</a>
		<a class="btn btn-primary" href="<c:url value="/my-account/delete"/>" role="button">Zamknij konto</a>
		<br>
		
		<hr>
		
		<br>
		
		<h3>Mój rachunek</h3>
		
		<br>
		
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-8">
				<div class="my-account-details">
					<p>Numer rachunku: <strong>${accountNumber}</strong></p>
					<p>Saldo: <strong>${balance} PLN</strong></p>
				</div>
			</div>
		</div>

		<br>
		
		<hr>
		
		<h2>Moje dane</h2>
		
		<br>
		
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-8">
				<div class="my-account-details">				
					<table>
						<tr>
							<td>Imię:</td>
							<td>${user.firstname}</td>
						</tr>
						<tr>
							<td>Nazwisko:</td>
							<td>${user.lastname}</td>
						</tr>
						<tr>
							<td>Pesel:</td>
							<td>${user.pin}</td>
						</tr>
						<tr>
							<td>Seria i numer dowodu osobistego:</td>
							<td>${user.seriesNumber}</td>
						</tr>
						<tr>
							<td>Data wygasniecia:</td>
							<fmt:parseDate value="${user.expiryDate}" pattern="yyyy-MM-dd" var="expiryDate"/>
							<td><fmt:formatDate value="${expiryDate}" pattern="dd.MM.yyyy"/></td>
						</tr>
						<tr>
							<td>Data urodzin:</td>
							<fmt:parseDate value="${user.birthDate}" pattern="yyyy-MM-dd" var="birthDate"/>
							<td><fmt:formatDate value="${birthDate}" pattern="dd.MM.yyyy"/></td>
						</tr>
						<tr>
							<td>Miejsce urodzin:</td>
							<td>${user.birthplace}</td>
						</tr>
						<tr>
							<td>Nazwisko rodowe matki:</td>
							<td>${user.mothersMaidenName}</td>
						</tr>
						<tr>
							<td>Adres email:</td>
							<td>${user.email}</td>
						</tr>
						<tr>
							<td>Telefon komórkowy:</td>
							<td>${user.phoneNumber}</td>
						</tr>
						<tr>
							<td>Ulica:</td>
							<td>${user.address.street}</td>
						</tr>
						<tr>
							<td>Numer domu, lokalu:</td>
							<td>${user.address.houseNumber}</td>
						</tr>
						<tr>
							<td>Miejscowość:</td>
							<td>${user.address.city}</td>
						</tr>
						<tr>
							<td>Kod pocztowy:</td>
							<td>${user.address.zipCode}</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		
		<br>
		<br>
		
		<hr>
		
		<h3>Karta debetowa</h3>
		
		<br>
		
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-8">
				<div class="my-account-debit-card-details">				
					<table>
						<tr>
							<td>Numer karty:</td>
							<td>${debitCard.cardNumber}</td>
						</tr>
						<tr>
							<td>Numer PIN:</td>
							<td>${debitCard.pin}</td>
						</tr>
						<tr>
							<td>Data ważności:</td>
							<fmt:parseDate value="${debitCard.expiryDate}" pattern="yyyy-MM-dd" var="expiryDate"/>
							<td><fmt:formatDate value="${expiryDate}" pattern="MM/yyyy"/></td>
						</tr>
						<tr>
							<td>Kod CVV:</td>
							<td>${debitCard.cvv}</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="parts/bottom.jsp"/>