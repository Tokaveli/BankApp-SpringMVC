package com.bank.dao.interfaces;

import java.util.List;

import com.bank.models.Deposit;

public interface DepositDaoInterface {

	public List<Deposit> getDeposits();
	
	public List<Deposit> getDepositsByAccountId(int id);
	
	public Deposit getDeposit(int id);
	
	public void saveOrUpdateDeposit(Deposit deposit);
	
	public void deleteDeposit(Deposit deposit);
}