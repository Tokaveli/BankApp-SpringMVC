package com.bank.controllers;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Kontroler mapy strony
 */
@Controller
public class SitemapController {

	private static final Logger logger = LoggerFactory.getLogger(SitemapController.class);

	/**
	 * Mapa strony
	 */
	@RequestMapping(value = "/sitemap", method = RequestMethod.GET)
	public String requestGet(Locale locale, Model model) {
		logger.info("Welcome sitemap! The client locale is {}.", locale);
		
		model.addAttribute("pagename", "Mapa strony");
		
		return "sitemap";
	}
}
