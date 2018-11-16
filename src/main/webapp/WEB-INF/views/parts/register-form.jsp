<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="<c:url value="/register"/>" method="POST" class="register-form">
	<h2 class="text-left">Dane osobowe</h2>
	
	<hr>

	<div class="form-group <c:if test="${not empty userValidate.firstnameError}">has-error</c:if>">
		<p class="error text-left">${userValidate.firstnameError}</p>
		<p class="text-left">Imię:</p>
		<input type="text" class="form-control" name="firstname" value="${user.firstname}" placeholder="Imię" autocomplete="off">
	</div>
	
	<div class="form-group <c:if test="${not empty userValidate.lastnameError}">has-error</c:if>">
		<p class="error text-left">${userValidate.lastnameError}</p>
		<p class="text-left">Nazwisko:</p>
		<input type="text" class="form-control" name="lastname" value="${user.lastname}" placeholder="Nazwisko" autocomplete="off">
	</div>
	
	<div class="form-group <c:if test="${not empty userValidate.pinError}">has-error</c:if>">
		<p class="error text-left">${userValidate.pinError}</p>
		<p class="text-left">Pesel:</p>
		<input type="text" class="form-control" name="pin" value="${user.pin}" placeholder="Pesel" autocomplete="off">
	</div>
	
	<div class="form-group <c:if test="${not empty userValidate.seriesNumberError}">has-error</c:if>">
		<p class="error text-left">${userValidate.seriesNumberError}</p>
		<p class="text-left">Seria i numer dowodu osobistego:</p>
		<input type="text" class="form-control" name="seriesNumber" value="${user.seriesNumber}" placeholder="Seria i numer dowodu osobistego" autocomplete="off">
	</div>
	
	<div class="form-group <c:if test="${not empty userValidate.expiryDateError}">has-error</c:if>">
		<p class="error text-left">${userValidate.expiryDateError}</p>
		<p class="text-left">Data ważności:</p>
		<input type="date" class="form-control" name="expiryDate" value="${user.expiryDate}" placeholder="Data ważności" autocomplete="off">
	</div>
	
	<div class="form-group <c:if test="${not empty userValidate.birthDateError}">has-error</c:if>">
		<p class="error text-left">${userValidate.birthDateError}</p>
		<p class="text-left">Data urodzenia:</p>
		<input type="date" class="form-control" name=birthDate value="${user.birthDate}" placeholder="Data urodzenia" autocomplete="off">
	</div>
	
	<div class="form-group <c:if test="${not empty userValidate.birthplaceError}">has-error</c:if>">
		<p class="error text-left">${userValidate.birthplaceError}</p>
		<p class="text-left">Miejsce urodzenia:</p>
		<input type="text" class="form-control" name="birthplace" value="${user.birthplace}" placeholder="Miejsce urodzenia" autocomplete="off">
	</div>
	
	<div class="form-group <c:if test="${not empty userValidate.mothersMaidenNameError}">has-error</c:if>">
		<p class="error text-left">${userValidate.mothersMaidenNameError}</p>
		<p class="text-left">Nazwisko rodowe matki:</p>
		<input type="text" class="form-control" name="mothersMaidenName" value="${user.mothersMaidenName}" placeholder="Nazwisko rodowe matki" autocomplete="off">
	</div>
	
	<br>
	
	<h2 class="text-left">Adres zameldowania</h2>
	
	<hr>
	
	<div class="form-group <c:if test="${not empty addressValidate.streetError}">has-error</c:if>">
		<p class="error text-left">${addressValidate.streetError}</p>
		<p class="text-left">Ulica:</p>
		<input type="text" class="form-control" name="street" value="${address.street}" placeholder="Ulica" autocomplete="off">
	</div>
	
	<div class="form-group <c:if test="${not empty addressValidate.houseNumberError}">has-error</c:if>">
		<p class="error text-left">${addressValidate.houseNumberError}</p>
		<p class="text-left">Numer domu, lokalu:</p>
		<input type="text" class="form-control" name="houseNumber" value="${address.houseNumber}" placeholder="Numer domu, lokalu" autocomplete="off">
	</div>
	
	<div class="form-group <c:if test="${not empty addressValidate.cityError}">has-error</c:if>">
		<p class="error text-left">${addressValidate.cityError}</p>
		<p class="text-left">Miejscowość:</p>
		<input type="text" class="form-control" name="city" value="${address.city}" placeholder="Miejscowość" autocomplete="off">
	</div>
	
	<div class="form-group <c:if test="${not empty addressValidate.zipCodeError}">has-error</c:if>">
		<p class="error text-left">${addressValidate.zipCodeError}</p>
		<p class="text-left">Kod pocztowy:</p>
		<input type="text" class="form-control" name="zipCode" value="${address.zipCode}" placeholder="Kod pocztowy" autocomplete="off">
	</div>
	
	<br>
	
	<h2 class="text-left">Kontakt</h2>
	
	<hr>
	
	<div class="form-group <c:if test="${not empty userValidate.phoneNumberError}">has-error</c:if>">
		<p class="error text-left">${userValidate.phoneNumberError}</p>
		<p class="text-left">Telefon komórkowy:</p>
		<input type="text" class="form-control" name="phoneNumber" value="${user.phoneNumber}" placeholder="Telefon komórkowy" autocomplete="off">
	</div>
	
	<div class="form-group <c:if test="${not empty userValidate.emailError}">has-error</c:if>">
		<p class="error text-left">${userValidate.emailError}</p>
		<p class="text-left">Adres email:</p>
		<input type="email" class="form-control" name="email" value="${user.email}" placeholder="Adres e-mail" autocomplete="off">
	</div>

	<input type="submit" class="btn btn-primary register-submit" value="Załóż nowy rachunek">
</form>