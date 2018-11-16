<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="parts/top.jsp"/>

<div class="page my-account-prepaids-send text-center">
	<div class="hero"></div>
	<div class="container">		
		
		<form action="<c:url value="/my-account/prepaids/send"/>" method="POST" class="my-account-prepaids-send-form">			
			
			
			<div class="form-group <c:if test="${not empty prepaidValidate.phoneNumberError}">has-error</c:if>">
				<p class="error text-left">${prepaidValidate.phoneNumberError}</p>
				<p class="text-center">Numer telefonu:</p>
				<input type="text" class="form-control" name="phoneNumber" value="${prepaid.phoneNumber}" placeholder="Numer telefonu" autocomplete="off">
			</div>
			
			<div>
				<p class="text-left">Operator sieci:</p>
				<select class="form-control" name="phoneOperator">
					<option value="Orange">Orange</option>
					<option value="Mobile Vikings">Mobile Vikings</option>
					<option value="Play">Play</option>
					<option value="Plus">Plus</option>
					<option value="Red Bull Mobile">Red Bull Mobile</option>
					<option value="T-Mobile">T-Mobile</option>
				</select>
			</div>
			<br>
			
			<div>
				<p class="text-left">Kwota (PLN):</p>
				<select class="form-control" name="amount">
					<option value="5">5 PLN</option>
					<option value="10">10 PLN</option>
					<option value="30">30 PLN</option>
					<option value="50">50 PLN</option>
					<option value="100">100 PLN</option>
					<option value="150">150 PLN</option>
				</select>
			<br>
			
		
			</div>
			<input type="submit" class="btn btn-primary my-account-prepaids-send-submit" value="Dokonaj doładowania">
			
			<a class="btn btn-primary" href="<c:url value="/my-account"/>" role="button">Anuluj</a>
		</form>
	</div>
</div>
			


<jsp:include page="parts/bottom.jsp"/>