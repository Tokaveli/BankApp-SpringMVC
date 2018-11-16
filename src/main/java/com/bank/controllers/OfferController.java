package com.bank.controllers;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Kontroler oferty
 */
@Controller
public class OfferController {

	private static final Logger logger = LoggerFactory.getLogger(OfferController.class);

	/**
	 * Oferta
	 */
	@RequestMapping(value = "/offer", method = RequestMethod.GET)
	public String requestGet(Locale locale, Model model) {
		logger.info("Welcome offer! The client locale is {}.", locale);
		
		model.addAttribute("pagename", "Oferta");
		
		return "offer";
	}
}
