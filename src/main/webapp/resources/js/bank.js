(function() {
	/**
	 * Płynny powrót do początku strony
	 */
	$('.back-to-top-text').click(function() {
		$('html, body').animate({ scrollTop: 0 }, 'medium');
	});
})();