package com.bank.dao.interfaces;

import java.util.List;

import com.bank.models.Account;

public interface AccountDaoInterface {

	public List<Account> getAccounts();
	
	public Account getAccount(int id);
	
	public void saveOrUpdateAccount(Account account);
	
	public void deleteAccount(Account account);
}