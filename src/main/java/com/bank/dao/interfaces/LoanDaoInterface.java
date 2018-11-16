package com.bank.dao.interfaces;

import java.util.List;

import com.bank.models.Loan;

public interface LoanDaoInterface {

	public List<Loan> getLoans();
	
	public List<Loan> getLoansByAccountId(int id);
	
	public Loan getLoan(int id);
	
	public void saveOrUpdateLoan(Loan loan);
	
	public void deleteLoan(Loan loan);
}