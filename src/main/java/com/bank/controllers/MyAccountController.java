package com.bank.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bank.dao.AccountDao;
import com.bank.dao.DebitCardDao;
import com.bank.dao.DepositDao;
import com.bank.dao.ErrandDao;
import com.bank.dao.LoanDao;
import com.bank.dao.PrepaidDao;
import com.bank.dao.TransferDao;
import com.bank.dao.UserDao;
import com.bank.models.Account;
import com.bank.models.DebitCard;
import com.bank.models.Deposit;
import com.bank.models.Errand;
import com.bank.models.Loan;
import com.bank.models.Prepaid;
import com.bank.models.Transfer;
import com.bank.models.User;
import com.bank.validate.DepositValidate;
import com.bank.validate.ErrandValidate;
import com.bank.validate.LoanValidate;
import com.bank.validate.PrepaidValidate;
import com.bank.validate.TransferValidate;
import com.bank.validate.Validate;

/**
 * Kontroler logowania
 */
@Controller
public class MyAccountController {

	private static final Logger logger = LoggerFactory.getLogger(MyAccountController.class);
	
	private static Validate validate = Validate.getInstance();

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private LoanDao loanDao;
	
	@Autowired
	private DepositDao depositDao;
	
	@Autowired
	private ErrandDao errandDao;
	
	@Autowired
	private TransferDao transferDao;
	
	@Autowired
	private PrepaidDao prepaidDao;
	
	@Autowired
	private DebitCardDao debitCardDao;

	/**
	 * Moje konto
	 */
	@RequestMapping(value = "/my-account", method = RequestMethod.GET)
	public String requestGet(
		HttpServletRequest req, HttpServletResponse res,
		Locale locale, Model model
	) {
		logger.info("Welcome my-account! The client locale is {}.", locale);

		if ((Boolean)req.getSession().getAttribute("authenticated") == null) {
			try {
				res.sendRedirect(req.getContextPath() + "/");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return "home";
		}
		
		Integer accountId = (Integer)req.getSession().getAttribute("accountId");
		
		User user = userDao.getUserByAccountId(accountId);
		
		model.addAttribute("user", user);
		
		DebitCard debitCard = debitCardDao.getDebitCardByAccountId(accountId);

		debitCard.setCardNumber(
			String.valueOf(debitCard.getCardNumber()).replaceFirst("(\\d{4})(\\d{4})(\\d{4})(\\d+)", "$1 $2 $3 $4")
		);
		
		debitCard.setPin(debitCard.getPin().replaceFirst("(^.+)\\d{3}", "$1***"));
		
		model.addAttribute("debitCard", debitCard);
		
		Account account = user.getAccount();
		
		BigDecimal balance = account.getBalance().divide(new BigDecimal("100"));
		
		String accountNumber = account.getAccountNumber();
		
		accountNumber = String.valueOf(accountNumber).replaceFirst("(\\d{2})(\\d{4})(\\d{4})(\\d{4})(\\d{4})(\\d{4})(\\d+)", "$1 $2 $3 $4 $5 $6 $7");
		
		model.addAttribute("balance", new DecimalFormat("0.00").format(balance));
		model.addAttribute("accountNumber", accountNumber);
		
		model.addAttribute("pagename", "Moje konto");
		
		return "my-account";
	}
	
	@RequestMapping(value = "/my-account/password", method = RequestMethod.GET)
	public String requestPasswordGet(
		HttpServletRequest req, HttpServletResponse res,
		Locale locale, Model model
	) {
		logger.info("Welcome my-account/password! The client locale is {}.", locale);
		
		if ((Boolean)req.getSession().getAttribute("authenticated") == null) {
			try {
				res.sendRedirect(req.getContextPath() + "/");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return "home";
		}
		
		model.addAttribute("pagename", "Moje konto - Zmiana has?a");
		
		return "my-account-password";
	}
	
	@RequestMapping(value = "/my-account/password", method = RequestMethod.POST)
	public String requestPasswordPost(
		HttpServletRequest req, HttpServletResponse res,
		Locale locale, Model model,
		@RequestParam(value="oldPassword", required = true) String oldPassword,
		@RequestParam(value="newPassword", required = true) String newPassword,
		@RequestParam(value="newPasswordRepeat", required = true) String newPasswordRepeat
	) {
		if ((Boolean)req.getSession().getAttribute("authenticated") == null) {
			try {
				res.sendRedirect(req.getContextPath() + "/");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return "home";
		}
		
		Integer accountId = (Integer)req.getSession().getAttribute("accountId");
		
		User user = userDao.getUserByAccountId(accountId);
		
		boolean success = true;
		
		if (!Account.SHA256(oldPassword).equals(user.getAccount().getPasswordHash())) {
			model.addAttribute("oldPasswordError", "Stare has³o nie jest poprawne");
			
			success = false;
		}
		
		if (!validate.isText(newPassword)) {
			model.addAttribute("newPasswordError", validate.errorText("nowe has³o"));
			
			success = false;
		}
		
		if (!validate.isText(newPasswordRepeat)) {
			model.addAttribute("newPasswordRepeatError", validate.errorText("powtórzone nowe has³o"));
			
			success = false;
		}
		
		if (!newPassword.equals(newPasswordRepeat)) {
			model.addAttribute("newPasswordError", "Nowe has³o i powtorzone nowe has³o ro¿ni¹ siê");

			success = false;
		}
		
		if (success) {
			String newPasswordHash = Account.SHA256(newPassword);
			
			Account account = user.getAccount();
			
			account.setPasswordHash(newPasswordHash);
			
			accountDao.saveOrUpdateAccount(account);
			
			req.getSession().setAttribute("authenticated", null);
			
			return "my-account-password-success";
		}
		
		return "my-account-password";
	}
	
	@RequestMapping(value = "/my-account/deposits", method = RequestMethod.GET)
	public String requestDepositsGet(
		HttpServletRequest req, HttpServletResponse res,
		Locale locale, Model model
	) {
		logger.info("Welcome my-account/deposits! The client locale is {}.", locale);
		
		if ((Boolean)req.getSession().getAttribute("authenticated") == null) {
			try {
				res.sendRedirect(req.getContextPath() + "/");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return "home";
		}
		
		Integer accountId = (Integer)req.getSession().getAttribute("accountId");

		model.addAttribute("pagename", "Moje konto - Moje lokaty");
		model.addAttribute("deposits", depositDao.getDepositsByAccountId(accountId));
		
		return "my-account-deposits";
	}
	
	@RequestMapping(value = "/my-account/deposits/open", method = RequestMethod.GET)
	public String requestDepositsOpenGet(
		HttpServletRequest req, HttpServletResponse res,
		Locale locale, Model model
	) {
		logger.info("Welcome my-account/deposits/open! The client locale is {}.", locale);
		
		if ((Boolean)req.getSession().getAttribute("authenticated") == null) {
			try {
				res.sendRedirect(req.getContextPath() + "/");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return "home";
		}
		
		model.addAttribute("pagename", "Moje konto - Otwieranie lokaty");
		
		return "my-account-deposits-open";
	}
	
	@RequestMapping(value = "/my-account/deposits/open", method = RequestMethod.POST)
	public String requestDepositsOpenPost(
		HttpServletRequest req, HttpServletResponse res,
		Locale locale, Model model,
		@ModelAttribute("deposit") Deposit deposit
	) {
		logger.info("Welcome my-account/deposits/open! The client locale is {}.", locale);
		
		if ((Boolean)req.getSession().getAttribute("authenticated") == null) {
			try {
				res.sendRedirect(req.getContextPath() + "/");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return "home";
		}
		
		boolean error = false;
		
		Integer accountId = (Integer)req.getSession().getAttribute("accountId");
		
		User user = userDao.getUserByAccountId(accountId);
		
		Account account = user.getAccount();

		DepositValidate depositValidate = new DepositValidate(deposit);
		
		BigDecimal balance = account.getBalance();
		
		error = depositValidate.isError(balance);
		
		if (error) {
			model.addAttribute("deposit", deposit);
			model.addAttribute("depositValidate", depositValidate);
			
			model.addAttribute("pagename", "Moje konto - Otwieranie lokaty");
			
			return "my-account-deposits-open";
		}
		
		deposit.setAmount(deposit.getAmount().multiply(new BigDecimal(100)));
		
		account.setBalance(balance.subtract(deposit.getAmount()));
		
		deposit.setAccount(account);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal = Calendar.getInstance();

		cal.setTime(new Date());		
		cal.add(Calendar.MONTH, deposit.getPeriod());
		
		String openDate = sdf.format(new Date());
		String closeDate = sdf.format(cal.getTime());
		
		deposit.setOpenDate(openDate);
		deposit.setCloseDate(closeDate);
		
		try {
			accountDao.saveOrUpdateAccount(account);
			depositDao.saveOrUpdateDeposit(deposit);
		}
		catch(HibernateException e) {
			throw new HibernateException(e.toString());
		}
		
		model.addAttribute("pagename", "Uda?o Ci si?otworzy?lokat?");
		
		return "my-account-deposits-open-success";
	}
	
@RequestMapping(value = "/my-account/transfers", method = RequestMethod.GET)
	public String requestTransfersGet(
		HttpServletRequest req, HttpServletResponse res,
		Locale locale, Model model
	) {
		logger.info("Welcome my-account/transfers! The client locale is {}.", locale);
		
		if ((Boolean)req.getSession().getAttribute("authenticated") == null) {
			try {
				res.sendRedirect(req.getContextPath() + "/");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return "home";
		}
		
		Integer accountId = (Integer)req.getSession().getAttribute("accountId");

		model.addAttribute("pagename", "Moje konto - Przelewy");
		model.addAttribute("transfers", transferDao.getTransfersByAccountId(accountId));
		
		return "my-account-transfers";
	}
	
	@RequestMapping(value = "/my-account/transfers/send", method = RequestMethod.GET)
	public String requestTransfersSendGet(
		HttpServletRequest req, HttpServletResponse res,
		Locale locale, Model model
	) {
		logger.info("Welcome my-account/transfers/send! The client locale is {}.", locale);
		
		if ((Boolean)req.getSession().getAttribute("authenticated") == null) {
			try {
				res.sendRedirect(req.getContextPath() + "/");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return "home";
		}
		
		model.addAttribute("pagename", "Moje konto - Wysy?anie przelewu");
		
		return "my-account-transfers-send";
	}
	
	@RequestMapping(value = "/my-account/transfers/send", method = RequestMethod.POST)
	public String requestTransfersSendPost(
		HttpServletRequest req, HttpServletResponse res,
		Locale locale, Model model,
		@ModelAttribute("transfer") Transfer transfer
	) {
		logger.info("Welcome my-account/transfers/send! The client locale is {}.", locale);
		
		if ((Boolean)req.getSession().getAttribute("authenticated") == null) {
			try {
				res.sendRedirect(req.getContextPath() + "/");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return "home";
		}
		
		boolean error = false;
		
		Integer accountId = (Integer)req.getSession().getAttribute("accountId");
		
		User user = userDao.getUserByAccountId(accountId);
		
		Account account = user.getAccount();

		TransferValidate transferValidate = new TransferValidate(transfer);
		
		BigDecimal balance = account.getBalance();
		
		error = transferValidate.isError(balance);
		
		if (error) {
			model.addAttribute("transfer", transfer);
			model.addAttribute("transferValidate", transferValidate);
			
			model.addAttribute("pagename", "Moje konto - Wysy?anie przelewu");
			
			return "my-account-transfers-send";
		}
		
		transfer.setAmount(transfer.getAmount().multiply(new BigDecimal(100)));
		
		account.setBalance(balance.subtract(transfer.getAmount()));
		
		transfer.setAccount(account);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String sendDate = sdf.format(new Date());
		
		transfer.setSendDate(sendDate);
		
		try {
			accountDao.saveOrUpdateAccount(account);
			transferDao.saveOrUpdateTransfer(transfer);
		}
		catch(HibernateException e) {
			throw new HibernateException(e.toString());
		}
		
		model.addAttribute("pagename", "Uda³o Ci siê wys³aæ przelew");
		model.addAttribute("accountNumber", transfer.getAccountNumber());
		model.addAttribute("amount", transfer.getAmount().divide(new BigDecimal(100)));
		model.addAttribute("title", transfer.getTitle());
		model.addAttribute("receiverName", transfer.getReceiverName());
		model.addAttribute("receiverAddress", transfer.getReceiverAddress());
		model.addAttribute("sendDate", transfer.getSendDate());
		
		return "my-account-transfers-send-success";
	}
	
	@RequestMapping(value = "/my-account/debit-cards/pin", method = RequestMethod.GET)
	public String requestDebitCardsPinGet(
		HttpServletRequest req, HttpServletResponse res,
		Locale locale, Model model
	) {
		logger.info("Welcome my-account/debit-cards/pin! The client locale is {}.", locale);
		
		if ((Boolean)req.getSession().getAttribute("authenticated") == null) {
			try {
				res.sendRedirect(req.getContextPath() + "/");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return "home";
		}
		
		model.addAttribute("pagename", "Moje konto - Zmiana kodu PIN");
		
		return "my-account-debit-cards-pin";
	}
	
	@RequestMapping(value = "/my-account/debit-cards/pin", method = RequestMethod.POST)
	public String requestDebitCardsPinPost(
		HttpServletRequest req, HttpServletResponse res,
		Locale locale, Model model,
		@RequestParam(value="oldPin", required = true) String oldPin,
		@RequestParam(value="newPin", required = true) String newPin,
		@RequestParam(value="newPinRepeat", required = true) String newPinRepeat
	) {
		if ((Boolean)req.getSession().getAttribute("authenticated") == null) {
			try {
				res.sendRedirect(req.getContextPath() + "/");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return "home";
		}
		
		Integer accountId = (Integer)req.getSession().getAttribute("accountId");
		
		DebitCard debitCard = debitCardDao.getDebitCardByAccountId(accountId);
		
		boolean success = true;
		
		if (!validate.isNumber(oldPin)) {
			model.addAttribute("oldPinError", "Stary kod PIN mo¿e zawieraæ tylko cyfry");
			
			success = false;
		}
		else if (!oldPin.equals(debitCard.getPin())) {
			model.addAttribute("oldPinError", "Stary kod PIN nie jest poprawny");
			
			success = false;
		}
		
		if (newPin.length() != 4) {
			model.addAttribute("newPinError", "Nowy kod PIN musi siê sk³adaæ z 4 cyfr");
			
			success = false;
		}
		else if (!validate.isNumber(newPin)) {
			model.addAttribute("newPinError", validate.errorText("nowy kod PIN"));
			
			success = false;
		}
		
		if (!validate.isNumber(newPinRepeat)) {
			model.addAttribute("newPinRepeatError", validate.errorText("powtórzony nowy kod PIN"));
			
			success = false;
		}
		
		if (!newPin.equals(newPinRepeat)) {
			model.addAttribute("newPinError", "Nowe kody PIN nie mog¹ siê ró¿niæ");

			success = false;
		}
		
		if (success) {			
			debitCard.setPin(newPin);
			
			debitCardDao.saveOrUpdateDebitCard(debitCard);
			
			return "my-account-debit-cards-pin-success";
		}
		
		return "my-account-debit-cards-pin";
	}
	
	
	
	//*********************************************************************************************************************************************//
	//*********************************************************************************************************************************************//
	//**************************************************Do?adowanie********************************************************************************//

	
	
	
	@RequestMapping(value = "/my-account/prepaids/send", method = RequestMethod.GET)
	public String requestPrepaidSendGet(
		HttpServletRequest req, HttpServletResponse res,
		Locale locale, Model model
	) {
		logger.info("Welcome my-account/prepaids/send! The client locale is {}.", locale);
		
		if ((Boolean)req.getSession().getAttribute("authenticated") == null) {
			try {
				res.sendRedirect(req.getContextPath() + "/");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return "home";
		}
		
		model.addAttribute("pagename", "Moje konto - Do?adowanie telefonu");
		
		return "my-account-prepaids-send";
	}
	
	@RequestMapping(value = "/my-account/prepaids/send", method = RequestMethod.POST)
	public String requestPrepaidsSendPost(
		HttpServletRequest req, HttpServletResponse res,
		Locale locale, Model model,
		@ModelAttribute("prepaid") Prepaid prepaid
	) {
		logger.info("Welcome my-account/prepaids/send! The client locale is {}.", locale);
		
		if ((Boolean)req.getSession().getAttribute("authenticated") == null) {
			try {
				res.sendRedirect(req.getContextPath() + "/");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return "home";
		}
		
		boolean error = false;
		
		Integer accountId = (Integer)req.getSession().getAttribute("accountId");
		
		User user = userDao.getUserByAccountId(accountId);
		
		Account account = user.getAccount();

		PrepaidValidate prepaidValidate = new PrepaidValidate(prepaid);
		
		BigDecimal balance = account.getBalance();
		
		error = prepaidValidate.isError(balance);
		
		if (error) {
			model.addAttribute("prepaid", prepaid);
			model.addAttribute("prepaidValidate", prepaidValidate);
			
			model.addAttribute("pagename", "Moje konto - Do?adowanie telefonu");
			
			return "my-account-prepaids-send";
		}
		
		prepaid.setAmount(prepaid.getAmount().multiply(new BigDecimal(100)));
		
		account.setBalance(balance.subtract(prepaid.getAmount()));
		
		prepaid.setAccount(account);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String sendDate = sdf.format(new Date());
		
		prepaid.setSendDate(sendDate);
		
		try {
			accountDao.saveOrUpdateAccount(account);
			prepaidDao.saveOrUpdatePrepaid(prepaid);
		}
		catch(HibernateException e) {
			throw new HibernateException(e.toString());
		}
		
		model.addAttribute("pagename", "Uda?o Ci si?do?adowa?konto telefonu");
		model.addAttribute("phoneNumber", prepaid.getPhoneNumber());
		model.addAttribute("amount", prepaid.getAmount().divide(new BigDecimal(100)));
		model.addAttribute("phoneOperator", prepaid.getPhoneOperator());
		model.addAttribute("sendDate", prepaid.getSendDate());
		
		return "my-account-prepaids-send-success";
	}
	
	//********************************************************************************************************************************************//
	//********************************************************************************************************************************************//
	//****************************************************Zlecenie sta?e**************************************************************************//
	
	@RequestMapping(value = "/my-account/errands", method = RequestMethod.GET)
	public String requestErrandsGet(
		HttpServletRequest req, HttpServletResponse res,
		Locale locale, Model model
	) {
		logger.info("Welcome my-account/errands! The client locale is {}.", locale);
		
		if ((Boolean)req.getSession().getAttribute("authenticated") == null) {
			try {
				res.sendRedirect(req.getContextPath() + "/");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return "home";
		}
		
		Integer accountId = (Integer)req.getSession().getAttribute("accountId");

		model.addAttribute("pagename", "Moje konto - Zlecenia sta?e");
		model.addAttribute("errands", errandDao.getErrandsByAccountId(accountId));
		
		return "my-account-errands";
	}
	
	@RequestMapping(value = "/my-account/errands/send", method = RequestMethod.GET)
	public String requestErrandsSendGet(
		HttpServletRequest req, HttpServletResponse res,
		Locale locale, Model model
	) {
		logger.info("Welcome my-account/errands/send! The client locale is {}.", locale);
		
		if ((Boolean)req.getSession().getAttribute("authenticated") == null) {
			try {
				res.sendRedirect(req.getContextPath() + "/");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return "home";
		}
		
		model.addAttribute("pagename", "Moje konto - Zlecenie sta?e");
		
		return "my-account-errands-send";
	}
	
	@RequestMapping(value = "/my-account/errands/send", method = RequestMethod.POST)
	public String requestErrandsSendPost(
		HttpServletRequest req, HttpServletResponse res,
		Locale locale, Model model,
		@ModelAttribute("errand") Errand errand
	) {
		logger.info("Welcome my-account/errands/send! The client locale is {}.", locale);
		
		if ((Boolean)req.getSession().getAttribute("authenticated") == null) {
			try {
				res.sendRedirect(req.getContextPath() + "/");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return "home";
		}
		
		boolean error = false;
		
		Integer accountId = (Integer)req.getSession().getAttribute("accountId");
		
		User user = userDao.getUserByAccountId(accountId);
		
		Account account = user.getAccount();

		ErrandValidate errandValidate = new ErrandValidate(errand);
		
		BigDecimal balance = account.getBalance();
		
		error = errandValidate.isError(balance);
		
		if (error) {
			model.addAttribute("errand", errand);
			model.addAttribute("errandValidate", errandValidate);
			
			model.addAttribute("pagename", "Moje konto - Zlecenie sta?e");
			
			return "my-account-errands-send";
		}
		
		errand.setAmount(errand.getAmount().multiply(new BigDecimal(100)));
		
		account.setBalance(balance.subtract(errand.getAmount()));
		
		errand.setAccount(account);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal = Calendar.getInstance();
		
		String sendDate = sdf.format(new Date());
		
		errand.setSendDate(sendDate);

		cal.setTime(new Date());		
		cal.add(Calendar.MONTH, errand.getPeriod());
		
		
		try {
			accountDao.saveOrUpdateAccount(account);
			errandDao.saveOrUpdateErrand(errand);
		}
		catch(HibernateException e) {
			throw new HibernateException(e.toString());
		}
		
		model.addAttribute("pagename", "Uda?o Ci si?wys?a?przelew");
		model.addAttribute("accountNumber", errand.getAccountNumber());
		model.addAttribute("amount", errand.getAmount().divide(new BigDecimal(100)));
		model.addAttribute("title", errand.getTitle());
		model.addAttribute("receiverName", errand.getReceiverName());
		model.addAttribute("receiverAddress", errand.getReceiverAddress());
		model.addAttribute("sendDate", errand.getSendDate());
		model.addAttribute("period", errand.getPeriod());
		
		return "my-account-errands-send-success";
	}
	
	@RequestMapping(value = "/my-account/delete", method = RequestMethod.GET)
	public String requestDeleteGet(
		HttpServletRequest req, HttpServletResponse res,
		Locale locale, Model model
	) {
		logger.info("Welcome my-account/delete! The client locale is {}.", locale);
		
		if ((Boolean)req.getSession().getAttribute("authenticated") == null) {
			try {
				res.sendRedirect(req.getContextPath() + "/");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return "home";
		}
		
		model.addAttribute("pagename", "Moje konto - Usuñ konto");
		
		return "my-account-delete";
	}
	
	@RequestMapping(value = "/my-account/delete", method = RequestMethod.POST)
	public String requestDeleteSendPost(
		HttpServletRequest req, HttpServletResponse res,
		Locale locale, Model model,
		@ModelAttribute("transfer") Transfer transfer
	) {
		logger.info("Welcome my-account/delete! The client locale is {}.", locale);
		
		if ((Boolean)req.getSession().getAttribute("authenticated") == null) {
			try {
				res.sendRedirect(req.getContextPath() + "/");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return "home";
		}
		
		boolean error = false;
		
		Integer accountId = (Integer)req.getSession().getAttribute("accountId");
		
		User user = userDao.getUserByAccountId(accountId);
		
		Account account = user.getAccount();
		BigDecimal balance = account.getBalance();
		transfer.setAmount(balance);
		transfer.setTitle("Przelew koncowy");
		transfer.setReceiverName("Konto do wyplaty");
		transfer.setReceiverAddress("Warszawska 100");
		TransferValidate transferValidate = new TransferValidate(transfer);
		
		
		
		error = transferValidate.isError(balance);
		
		if (error) {
			model.addAttribute("transfer", transfer);
			model.addAttribute("transferValidate", transferValidate);
			
			model.addAttribute("pagename", "Moje konto - Usuwanie konta ");
			
			return "my-account";
		}
		
		transfer.setAmount(transfer.getAmount().multiply(new BigDecimal(100)));
		
		account.setBalance(new BigDecimal(0));
		
		transfer.setAccount(account);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String sendDate = sdf.format(new Date());
		
		transfer.setSendDate(sendDate);
		
		try {
			accountDao.saveOrUpdateAccount(account);
			transferDao.saveOrUpdateTransfer(transfer);
			accountDao.deleteAccount(account);
		}
		catch(HibernateException e) {
			throw new HibernateException(e.toString());
		}

			req.getSession().setAttribute("authenticated", false);
		return "my-account-delete-success";
	}
	
	
	@RequestMapping(value = "/my-account/loans", method = RequestMethod.GET)
	public String requestLoansGet(
		HttpServletRequest req, HttpServletResponse res,
		Locale locale, Model model
	) {
		logger.info("Welcome my-account/loans! The client locale is {}.", locale);
		
		if ((Boolean)req.getSession().getAttribute("authenticated") == null) {
			try {
				res.sendRedirect(req.getContextPath() + "/");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return "home";
		}
		
		Integer accountId = (Integer)req.getSession().getAttribute("accountId");

		model.addAttribute("pagename", "Moje konto - Moje po¿yczki");
		
		model.addAttribute("loans", loanDao.getLoansByAccountId(accountId));
		
		return "my-account-deposits";
	}
	
	@RequestMapping(value = "/my-account/loans/open", method = RequestMethod.GET)
	public String requestLoansOpenGet(
		HttpServletRequest req, HttpServletResponse res,
		Locale locale, Model model
	) {
		logger.info("Welcome my-account/loans/open! The client locale is {}.", locale);
		
		if ((Boolean)req.getSession().getAttribute("authenticated") == null) {
			try {
				res.sendRedirect(req.getContextPath() + "/");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return "home";
		}
		
		model.addAttribute("pagename", "Moje konto - Branie po¿yczki");
		
		return "my-account-loans-open";
	}
	
	@RequestMapping(value = "/my-account/loans/open", method = RequestMethod.POST)
	public String requestLoansOpenPost(
		HttpServletRequest req, HttpServletResponse res,
		Locale locale, Model model,
		@ModelAttribute("loan") Loan loan
	) {
		logger.info("Welcome my-account/loans/open! The client locale is {}.", locale);
		
		if ((Boolean)req.getSession().getAttribute("authenticated") == null) {
			try {
				res.sendRedirect(req.getContextPath() + "/");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return "home";
		}
		
		boolean error = false;
		
		Integer accountId = (Integer)req.getSession().getAttribute("accountId");
		
		User user = userDao.getUserByAccountId(accountId);
		
		Account account = user.getAccount();

		LoanValidate loanValidate = new LoanValidate(loan);
		
		BigDecimal balance = account.getBalance();
		
		error = loanValidate.isError(balance);
		
		if (error) {
			model.addAttribute("loan", loan);
			model.addAttribute("loanValidate", loanValidate);
			
			model.addAttribute("pagename", "Moje konto - Branie po¿yczki");
			
			return "my-account-loans-open";
		}
		
		loan.setAmount(loan.getAmount().multiply(new BigDecimal(100)));
		
		account.setBalance(balance.add(loan.getAmount()));
		
		loan.setAccount(account);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal = Calendar.getInstance();

		cal.setTime(new Date());		
		cal.add(Calendar.MONTH, loan.getPeriod());
		
		String openDate = sdf.format(new Date());
		String closeDate = sdf.format(cal.getTime());
		
		loan.setOpenDate(openDate);
		loan.setCloseDate(closeDate);
		
		try {
			accountDao.saveOrUpdateAccount(account);
			loanDao.saveOrUpdateLoan(loan);
		}
		catch(HibernateException e) {
			throw new HibernateException(e.toString());
		}
		
		model.addAttribute("pagename", "Udalo ci sie wziac pozyczke!");
		
		return "my-account-loan-open-success";
	}
	
}