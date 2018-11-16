package com.bank.controllers;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Kontroler kontaktu
 */
@Controller
public class ContactController {

	private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

	/**
	 * Kontakt
	 */
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String requestGet(Locale locale, Model model) {
		logger.info("Welcome contact! The client locale is {}.", locale);
		
		model.addAttribute("pagename", "Kontakt");
		
		return "contact";
	}
}
