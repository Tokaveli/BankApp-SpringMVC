package com.bank.controllers;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bank.authentication.AccountAuthentication;
import com.bank.dao.AccountDao;
import com.bank.models.Account;

/**
 * Kontroler logowania
 */
@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private AccountDao accountDao;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String requestGet(
		HttpServletRequest req, HttpServletResponse res,
		Locale locale, Model model
	) {
		AccountAuthentication accountAuthentication = new AccountAuthentication();
		
		/*
		 * Czy u¿ytkownik jest ju¿ zalogowany?
		 */		
		if (accountAuthentication.isAfterAuthentication(req, res)) {
			return "home";
		}
		
		return renderForm(locale, model);
	}
	
	/**
	 * Próba logowania
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String requestPost(
		HttpServletRequest req, HttpServletResponse res,
		Locale locale, Model model,
		@ModelAttribute("account") Account account
	) {
				AccountAuthentication accountAuthentication = new AccountAuthentication(account);

		if (accountAuthentication.isAfterAuthentication(req, res)) {
			return "home";
		}
				
		boolean authenticated = accountAuthentication.isAuthenticated(accountDao);
		
		if (!authenticated) {			
			return onError(locale, model, account, accountAuthentication);
		}
		
		return onSuccess(locale, model, account, req);
	}
	
	/**
	 * Formularz logowania
	 */
	private String renderForm(Locale locale, Model model) {
		logger.info("Welcome login! The client locale is {}.", locale);
		
		model.addAttribute("pagename", "Logowanie");
		
		return "login";
	}
	
	private String onError(
		Locale locale, Model model,
		Account account, AccountAuthentication accountAuthentication
	) {
		model.addAttribute("accountAuthentication", accountAuthentication);

		return renderForm(locale, model);
	}

	private String onSuccess(Locale locale, Model model, Account account, HttpServletRequest req) {
		logger.info("Welcome login-success! The client locale is {}.", locale);
		
		req.getSession().setAttribute("authenticated", true);
		req.getSession().setAttribute("accountId", account.getId());
		
		model.addAttribute("pagename", "Zalogowa³eœ siê");
		
		return "login-success";
	}
}