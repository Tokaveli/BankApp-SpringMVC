package com.bank.controllers;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Kontroler o nas
 */
@Controller
public class AboutUsController {

	private static final Logger logger = LoggerFactory.getLogger(AboutUsController.class);

	/**
	 * O nas
	 */
	@RequestMapping(value = "/about-us", method = RequestMethod.GET)
	public String requestGet(Locale locale, Model model) {
		logger.info("Welcome about-us! The client locale is {}.", locale);
		
		model.addAttribute("pagename", "O nas");
		
		return "about-us";
	}
}
