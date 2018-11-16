<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="parts/top.jsp"/>

<div class="page my-account-transfers text-center">
	<div class="hero"></div>
	<div class="container">
		<br>

		<a class="btn btn-primary" href="<c:url value="/my-account/transfers/send"/>" role="button">Wyślij przelew</a>
		
		<a class="btn btn-primary" href="<c:url value="/my-account"/>" role="button">Moje konto</a>
		
		<br>
		
		<hr>
		
		<br>
		
		<h2>Historia przelewów</h2>
		
		<br>
		
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-12">
				<div class="my-transfers-details text-left">				
					<table>
						<tr>
							<th>Numer rachunku</th>
							<th>Kwota</th>
							<th>Tytuł</th>
							<th>Nazwa odbiorcy</th>
							<th>Adres odbiorcy</th>
							<th>Data wysłania</th>
							
						</tr>
						<c:forEach items="${transfers}" var="transfer">
						   	<tr>
								<td class="text-center">${transfer.accountNumber}</td>
								<td>${transfer.amount} zł</td>
								<td>${transfer.title}</td>
								<td>${transfer.receiverName}</td>
								<td>${transfer.receiverAddress}</td>
								
								<fmt:parseDate value="${transfer.sendDate}" pattern="yyyy-MM-dd" var="sendDate"/>
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