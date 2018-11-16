package com.bank.controllers;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Kontroler polityki prywatno�ci
 */
@Controller
public class PrivacyPolicyController {

	private static final Logger logger = LoggerFactory.getLogger(PrivacyPolicyController.class);

	/**
	 * Polityka prywatno�ci
	 */
	@RequestMapping(value = "/privacy-policy", method = RequestMethod.GET)
	public String requestGet(Locale locale, Model model) {
		logger.info("Welcome privacy-policy! The client locale is {}.", locale);
		
		model.addAttribute("pagename", "Polityka prywatno�ci");
		
		return "privacy-policy";
	}
}
