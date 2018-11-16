package com.bank.validate;

import java.math.BigDecimal;

import com.bank.models.Errand;

public class ErrandValidate {
	private Errand errand;
	
	private Validate validate;
	
	private boolean error;

	private String receiverNameError;
	private String receiverAddressError;
	private String accountNumberError;
	private String amountError;
	private String titleError;
	private String periodError;

	public ErrandValidate(Errand errand) {
		super();
		
		this.errand = errand;
		
		this.validate = Validate.getInstance();
		
		this.error = false;

		this.receiverNameError = "";
		this.receiverAddressError = "";
		this.accountNumberError = "";
		this.amountError = "";
		this.titleError = "";
		this.periodError = "";
	}
	
	public Errand getErrand() {
		return errand;
	}

	public void setErrand(Errand errand) {
		this.errand = errand;
	}

	public boolean isError(BigDecimal balance) {
		if (!validate.isText(errand.getReceiverName())) {
			receiverNameError = validate.errorText("nazwa odbiorcy");

			error = true;
		}
		else if (validate.isNumber(errand.getReceiverName())) {
			receiverNameError = validate.errorNotNumber("nazwa odbiorcy");

			error = true;
		}
		
		if (!validate.isText(errand.getReceiverAddress())) {
			receiverAddressError = validate.errorText("adres odbiorcy");

			error = true;
		}
		else if (validate.isNumber(errand.getReceiverAddress())) {
			receiverAddressError = validate.errorNotNumber("adres odbiorcy");

			error = true;
		}
		
		if (errand.getAccountNumber().length() != 26) {
			accountNumberError = "Pole numer rachunku musi siê sk³adaæ z 26 cyfr";
			
			error = true;
		}
		else if (!validate.isNumber(errand.getAccountNumber())) {
			accountNumberError = validate.errorNumber("numer rachunku");
			
			error = true;
		}
		else if (!validate.isText(errand.getAccountNumber())) {
			accountNumberError = validate.errorText("numer rachunku");
			
			error = true;
		}
		
		if (errand.getAmount() == null) {
			amountError = "Pole kwota nie mo¿e byæ puste";
			
			error = true;
		}
		else if (errand.getAmount().multiply(new BigDecimal(100)).compareTo(balance) == 1) {
			amountError = "Pole kwota nie mo¿e byæ wiêksza ni¿ Twoje saldo";
			
			error = true;
		}
		else if (errand.getAmount().compareTo(new BigDecimal(1)) == -1) {
			amountError = "Pole kwota nie mo¿e byæ mniejsza ni¿ 1 z³";
			
			error = true;
		}
		
		if (!validate.isText(errand.getTitle())) {
			titleError = validate.errorText("tytu³");

			error = true;
		}
		else if (validate.isNumber(errand.getTitle())) {
			titleError = validate.errorNotNumber("tytu³");

			error = true;
		}
		
		if (errand.getPeriod() <= 0) {
			periodError = "Pole okres nie mo¿e posiadaæ 0 lub mniej miesiêcy/tygodni";
			
			error = true;
		}
		
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getReceiverNameError() {
		return receiverNameError;
	}

	public void setReceiverNameError(String receiverNameError) {
		this.receiverNameError = receiverNameError;
	}

	public String getReceiverAddressError() {
		return receiverAddressError;
	}

	public void setReceiverAddressError(String receiverAddressError) {
		this.receiverAddressError = receiverAddressError;
	}

	public String getAccountNumberError() {
		return accountNumberError;
	}

	public void setAccountNumberError(String accountNumberError) {
		this.accountNumberError = accountNumberError;
	}

	public String getAmountError() {
		return amountError;
	}

	public void setAmountError(String amountError) {
		this.amountError = amountError;
	}

	public String getTitleError() {
		return titleError;
	}

	public void setTitleError(String titleError) {
		this.titleError = titleError;
	}
	
	public String getPeriodError() {
		return periodError;
	}

	public void setPeriodError(String periodError) {
		this.periodError = periodError;
	}
}