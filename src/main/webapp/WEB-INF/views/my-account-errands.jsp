<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="parts/top.jsp"/>

<div class="page my-account-errands text-center">
	<div class="hero"></div>
	<div class="container">
		<br>

		<a class="btn btn-primary" href="<c:url value="/my-account/errands/send"/>" role="button">Wykonaj zlecenie stałę</a>
		
		<a class="btn btn-primary" href="<c:url value="/my-account"/>" role="button">Moje konto</a>
		
		<br>
		
		<hr>
		
		<br>
		
		<h2>Twoje zlecenia stałe</h2>
		
		<br>
		
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-12">
				<div class="my-errands-details text-left">				
					<table>
						<tr>
							<th>Numer rachunku</th>
							<th>Kwota</th>
							<th>Tytuł</th>
							<th>Nazwa odbiorcy</th>
							<th>Adres odbiorcy</th>
							<th>Okres</th>
							<th>Data wysłania</th>
							
							
						</tr>
						<c:forEach items="${errands}" var="errand">
						   	<tr>
								<td class="text-center">${errand.accountNumber}</td>
								<td>${errand.amount} zł</td>
								<td>${errand.title}</td>
								<td>${errand.receiverName}</td>
								<td>${errand.receiverAddress}</td>
								<td>${errand.period} tygodnie</td>
								
								<fmt:parseDate value="${errand.sendDate}" pattern="yyyy-MM-dd" var="sendDate"/>
								<td><fmt:formatDate value="${sendDate}" pattern="dd.MM.yyyy"/></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="parts/bottom.jsp"/>