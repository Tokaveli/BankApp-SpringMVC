<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<footer>
	<div class="container">
		<div class="footer-inner">
			<div class="back-to-top pull-right">
				<h4 class="back-to-top-text"><i class="material-icons">expand_less</i> Przewiń na początek</h4>
			</div>
		
			<div class="row">
				<div class="col-md-6">
				  	<div class="phone-number">
				  		<h3><i class="material-icons">call</i> 810 361 361</h3>
				  	</div>
			  	</div>
			  
				<div class="col-md-6 text-right">
				  	<div class="help">
						<h3>
							<i class="material-icons">help</i>
							<a href="<c:url value="/contact"/>">Uzyskaj pomoc</a>
						</h3>
					</div>
			  	</div>
			</div>
			
			<hr>
			
			<div class="row">
				<div class="col-md-6">
				  	<div class="footer-links">
					  	<a href="<c:url value="/privacy-policy"/>">Polityka prywatności</a>
					  	<a href="<c:url value="/sitemap"/>">Mapa strony</a>
					 </div>
			  	</div>
				  
				<div class="col-md-6 text-right">
					<p class="copyright"><i class="material-icons">account_balance</i> Bank &copy; 2017</p>
				</div>
			</div>
		</div>
	</div>
</footer>