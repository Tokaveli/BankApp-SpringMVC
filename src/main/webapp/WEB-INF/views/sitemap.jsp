<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="parts/top.jsp"/>

<div class="page sitemap">
	<div class="container">
		<h1 class="text-center">Mapa strony</h1>
		
		<br>
		<br>
		
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<ul class="sitemap-links">
					<li>
						<a href="<c:url value="/"/>">Strona główna</a>
					</li>
					<li>
						<a href="<c:url value="/offer"/>">Oferta</a>
					</li>
					<li>
						<a href="<c:url value="/login"/>">Logowanie</a>
					</li>
					<li>
						<a href="<c:url value="/register"/>">Rejestracja</a>
					</li>
					<li>
						<a href="<c:url value="/contact"/>">Kontakt</a>
					</li>
					<li>
						<a href="<c:url value="/about-us"/>">O nas</a>
					</li>
					<li>
						<a href="<c:url value="/privacy-policy"/>">Polityka prywatności</a>
					</li>
				</ul>
			</div>
			<div class="col-md-4"></div>
		</div>
	</div>
</div>

<jsp:include page="parts/bottom.jsp"/>