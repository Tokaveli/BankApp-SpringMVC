<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="parts/top.jsp"/>

<div class="page my-account-deposits text-center">
	<div class="hero"></div>
	<div class="container">
		<br>

		<a class="btn btn-primary" href="<c:url value="/my-account/deposits/open"/>" role="button">Otwórz nową lokatę</a>
		
		<a class="btn btn-primary" href="<c:url value="/my-account"/>" role="button">Moje konto</a>
		
		<br>
		
		<hr>
		
		<br>
		
		<h2>Moje lokaty</h2>
		
		<br>
		
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-8">
				<div class="my-deposits-details text-left">				
					<table>
						<tr>
							<th>Okres (miesiące)</th>
							<th>Kwota</th>
							<th>Data otwarcia</th>
							<th>Data wygaśnięcia</th>
							
						</tr>
						<c:forEach items="${deposits}" var="deposit">
						   	<tr>
								<td class="text-center">${deposit.period}</td>
								<td>${deposit.amount} zł</td>
								
								<fmt:parseDate value="${deposit.openDate}" pattern="yyyy-MM-dd" var="openDate"/>
								<td><fmt:formatDate value="${openDate}" pattern="dd.MM.yyyy"/></td>
								
								<fmt:parseDate value="${deposit.closeDate}" pattern="yyyy-MM-dd" var="closeDate"/>
								<td><fmt:formatDate value="${closeDate}" pattern="dd.MM.yyyy"/></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="parts/bottom.jsp"/>