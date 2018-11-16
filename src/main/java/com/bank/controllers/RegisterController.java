package com.bank.controllers;

import java.util.Locale;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bank.dao.AccountDao;
import com.bank.dao.AddressDao;
import com.bank.dao.UserDao;
import com.bank.models.Account;
import com.bank.models.Address;
import com.bank.models.User;
import com.bank.validate.AddressValidate;
import com.bank.validate.UserValidate;

/**
 * Kontroler rejestracji
 */
@Controller
public class RegisterController {

	private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private AddressDao addressDao;

	/**
	 * Formularz rejestracji
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String requestGet(Locale locale, Model model) {
		return renderForm(locale, model);
	}
	
	/**
	 * Próba rejestracji
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String requestPost(
		Locale locale, Model model,
		@ModelAttribute("user") User user,
		@ModelAttribute("address") Address address
	) {
		boolean error = false;

		UserValidate userValidate = new UserValidate(user);
		AddressValidate addressValidate = new AddressValidate(address);
		
		error = userValidate.isError() | addressValidate.isError();
		
		if (error) {			
			return onError(locale, model, user, userValidate, address, addressValidate);
		}
		
		return onSuccess(locale, model, user, address);
	}
	
	private String renderForm(Locale locale, Model model) {
		logger.info("Welcome register! The client locale is {}.", locale);
		
		model.addAttribute("pagename", "Zak³adanie rachunku");
		
		return "register";
	}
	
	private String onError(
		Locale locale, Model model,
		User user, UserValidate userValidate,
		Address address, AddressValidate addressValidate
	) {
		model.addAttribute("user", user);
		model.addAttribute("userValidate", userValidate);
		model.addAttribute("address", address);
		model.addAttribute("addressValidate", addressValidate);
		
		return renderForm(locale, model);
	}
	
	private String onSuccess(Locale locale, Model model, User user, Address address) {
		logger.info("Welcome register-success! The client locale is {}.", locale);
		
		Account account = new Account();
		
		accountDao.saveOrUpdateAccount(account);
		
		user.setAccount(account);
		
		addressDao.saveOrUpdateAddress(address);
		
		user.setAddress(address);
		
		try {
			userDao.saveOrUpdateUser(user);
		}
		catch(HibernateException e) {
			accountDao.deleteAccount(account);
			addressDao.deleteAddress(address);
			
			throw new HibernateException(e.toString());
		}
		
		model.addAttribute("pagename", "Za³o¿y³eœ nowy rachunek");
		model.addAttribute("login", account.getLogin());
		model.addAttribute("password", account.getPassword());
		model.addAttribute("accountNumber", account.getAccountNumber());
		
		return "register-success";
	}
}
