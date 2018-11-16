<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="parts/top.jsp"/>

<div class="page my-account-loans text-center">
	<div class="hero"></div>
	<div class="container">
		<br>

		<a class="btn btn-primary" href="<c:url value="/my-account/loans/open"/>" role="button">Weż pożyczkę</a>
		
		<a class="btn btn-primary" href="<c:url value="/my-account"/>" role="button">Moje konto</a>
		
		<br>
		
		<hr>
		
		<br>
		
		<h2>Moje pożyczki</h2>
		
		<br>
		
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-8">
				<div class="my-loans-details text-left">				
					<table>
						<tr>
							<th>Okres (miesiące)</th>
							<th>Kwota</th>
							<th>Data otwarcia</th>
							<th>Data zakończenia</th>
							
						</tr>
						<c:forEach items="${loans}" var="loan">
						   	<tr>
								<td class="text-center">${loan.period}</td>
								<td>${loan.amount} zł</td>
								
								<fmt:parseDate value="${loan.openDate}" pattern="yyyy-MM-dd" var="openDate"/>
								<td><fmt:formatDate value="${openDate}" pattern="dd.MM.yyyy"/></td>
								
								<fmt:parseDate value="${loan.closeDate}" pattern="yyyy-MM-dd" var="closeDate"/>
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