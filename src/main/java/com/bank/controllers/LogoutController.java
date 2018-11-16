package com.bank.controllers;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Kontroler logowania
 */
@Controller
public class LogoutController {

	private static final Logger logger = LoggerFactory.getLogger(LogoutController.class);

	/**
	 * Wylogowanie
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String requestGet(
		HttpServletRequest req, HttpServletResponse res,
		Locale locale, Model model
	) {
		logger.info("Welcome logout! The client locale is {}.", locale);

		req.getSession().setAttribute("authenticated", null);
		
		try {
			res.sendRedirect(req.getContextPath() + "/");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "home";
	}
}