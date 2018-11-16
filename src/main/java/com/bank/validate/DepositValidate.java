package com.bank.validate;

import java.math.BigDecimal;

import com.bank.models.Deposit;

public class DepositValidate {
	private Deposit deposit;
	
	private boolean error;

	private String periodError;
	private String amountError;

	public DepositValidate(Deposit deposit) {
		super();
		
		this.deposit = deposit;
		
		this.error = false;

		this.periodError = "";
		this.amountError = "";
	}
	
	public Deposit getDeposit() {
		return deposit;
	}

	public void setDeposit(Deposit deposit) {
		this.deposit = deposit;
	}

	public boolean isError(BigDecimal balance) {
		if (deposit.getPeriod() <= 0) {
			periodError = "Pole okres nie mo�e posiada� 0 lub mniej miesi�cy";
			
			error = true;
		}
		
		if (deposit.getAmount() == null) {
			amountError = "Pole kwota nie mo�e by� puste";
			
			error = true;
		}
		else if (deposit.getAmount().multiply(new BigDecimal(100)).compareTo(balance) == 1) {
			amountError = "Pole kwota nie mo�e by� wi�ksza ni� Twoje saldo";
			
			error = true;
		}
		else if (deposit.getAmount().compareTo(new BigDecimal(1)) == -1) {
			amountError = "Pole kwota nie mo�e by� mniejsza ni� 1 z�";
			
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