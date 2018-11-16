package com.bank.validate;

import java.math.BigDecimal;

import com.bank.models.Loan;

public class LoanValidate {
	private Loan loan;
	
	private boolean error;

	private String periodError;
	private String amountError;

	public LoanValidate(Loan loan) {
		super();
		
		this.loan = loan;
		
		this.error = false;

		this.periodError = "";
		this.amountError = "";
	}
	
	public Loan getDeposit() {
		return loan;
	}

	public void setLoan(Loan deposit) {
		this.loan = deposit;
	}

	public boolean isError(BigDecimal balance) {
		if (loan.getPeriod() <= 0) {
			periodError = "Pole okres nie mo¿e posiadaæ 0 lub mniej miesiêcy";
			
			error = true;
		}
		
		if (loan.getAmount() == null) {
			amountError = "Pole kwota nie mo¿e byæ puste";
			
			error = true;
		}
		else if (loan.getAmount().multiply(new BigDecimal(100)).compareTo(balance) == 1) {
			amountError = "Pole kwota nie mo¿e byæ wiêksza ni¿ Twoje saldo";
			
			error = true;
		}
		else if (loan.getAmount().compareTo(new BigDecimal(1)) == -1) {
			amountError = "Pole kwota nie mo¿e byæ mniejsza ni¿ 1 z³";
			
			error = true;
		}
		
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getPeriodError() {
		return periodError;
	}

	public void setPeriodError(String periodError) {
		this.periodError = periodError;
	}

	public String getAmountError() {
		return amountError;
	}

	public void setAmountError(String amountError) {
		this.amountError = amountError;
	}
}