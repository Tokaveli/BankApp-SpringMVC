package com.bank.authentication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.dao.AccountDao;
import com.bank.models.Account;

public class AccountAuthentication {
	private Account account;
	
	private boolean authenticated;

	private String loginError;

	public AccountAuthentication() {
		super();
	}
	
	public AccountAuthentication(Account account) {
		super();
		
		this.account = account;

		this.authenticated = false;

		this.loginError = "Nie uda³o siê zalogowaæ. Login lub has³o s¹ nieprawid³owe";
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public boolean isAuthenticated(AccountDao accountDao) {
		List<Account> accounts = new ArrayList<Account>();

		accounts = accountDao.getAccounts();
		
		for (int i = 0; i < accounts.size(); ++i) {
			if (
				account.getLogin().equals(accounts.get(i).getLogin()) &&
				Account.SHA256(account.getPassword()).equals(accounts.get(i).getPasswordHash())
			) {				
				account.setId(accounts.get(i).getId());
				
				authenticated = true;

				break;
			}
		}
		
		return authenticated;
	}
	
	public boolean isAfterAuthentication(HttpServletRequest req, HttpServletResponse res) {
		Boolean authenticated = (Boolean)req.getSession().getAttribute("authenticated");
		
		if (authenticated != null && authenticated) {
			try {
				res.sendRedirect(req.getContextPath() + "/");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return true;
		}
		
		return false;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	public String getLoginError() {
		return loginError;
	}

	public void setLoginError(String loginError) {
		this.loginError = loginError;
	}
}