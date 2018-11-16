<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="parts/top.jsp"/>

<div class="page about-us">
	<div class="container">
		<h1 class="text-center">Twórcy oprogramowania</h1>
		
		<br>
		<br>
		
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<div class="programmers">
					<h3>Kacper Świderski</h3>
					<ul>
						<li>Historia przelewu</li>
						<li>Zamykanie rachunku</li>
						<li>Pożyczki</li>
					</ul>
					
					<br>
					
					<h3>Piotr Świnarski</h3>
					<ul>
						<li>Uwierzytelnienie oraz tworzenie nowego rachunku dla klienta</li>
						<li>Otwieranie lokaty na rachunku klienta</li>
						<li>Zmiana kodu PIN do karty klienta</li>
						<li>Dodatkowo wykorzystanie wzorca projektowego typu Singleton oraz przeprowadzenie testów jednostkowych</li>
					</ul>
					
					<br>
					
					<h3>Przemysław Tokarski</h3>
					<ul>
						<li>Realizacja przelewów</li>
						<li>Zlecenia stałe</li>
						<li>Doładowania</li>
					</ul>
				</div>
			</div>
			<div class="col-md-3"></div>
		</div>
	</div>
</div>

<jsp:include page="parts/bottom.jsp"/>