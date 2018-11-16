package com.bank.validate;

import java.math.BigDecimal;

import com.bank.models.Transfer;

public class TransferValidate {
	private Transfer transfer;
	
	private Validate validate;
	
	private boolean error;

	private String receiverNameError;
	private String receiverAddressError;
	private String accountNumberError;
	private String amountError;
	private String titleError;

	public TransferValidate(Transfer transfer) {
		super();
		
		this.transfer = transfer;
		
		this.validate = Validate.getInstance();
		
		this.error = false;

		this.receiverNameError = "";
		this.receiverAddressError = "";
		this.accountNumberError = "";
		this.amountError = "";
		this.titleError = "";
	}
	
	public Transfer getTransfer() {
		return transfer;
	}

	public void setTransfer(Transfer transfer) {
		this.transfer = transfer;
	}

	public boolean isError(BigDecimal balance) {
		if (!validate.isText(transfer.getReceiverName())) {
			receiverNameError = validate.errorText("nazwa odbiorcy");

			error = true;
		}
		else if (validate.isNumber(transfer.getReceiverName())) {
			receiverNameError = validate.errorNotNumber("nazwa odbiorcy");

			error = true;
		}
		
		if (!validate.isText(transfer.getReceiverAddress())) {
			receiverAddressError = validate.errorText("adres odbiorcy");

			error = true;
		}
		else if (validate.isNumber(transfer.getReceiverAddress())) {
			receiverAddressError = validate.errorNotNumber("adres odbiorcy");

			error = true;
		}
		
		if (transfer.getAccountNumber().length() != 26) {
			accountNumberError = "Pole numer rachunku musi siê sk³adaæ z 26 cyfr";
			
			error = true;
		}
		else if (!validate.isNumber(transfer.getAccountNumber())) {
			accountNumberError = validate.errorNumber("numer rachunku");
			
			error = true;
		}
		else if (!validate.isText(transfer.getAccountNumber())) {
			accountNumberError = validate.errorText("numer rachunku");
			
			error = true;
		}
		
		if (transfer.getAmount() == null) {
			amountError = "Pole kwota nie mo¿e byæ puste";
			
			error = true;
		}
		else if (transfer.getAmount().multiply(new BigDecimal(100)).compareTo(balance) == 1) {
			amountError = "Pole kwota nie mo¿e byæ wiêksza ni¿ Twoje saldo";
			
			error = true;
		}
		else if (transfer.getAmount().compareTo(new BigDecimal(1)) == -1) {
			amountError = "Pole kwota nie mo¿e byæ mniejsza ni¿ 1 z³";
			
			error = true;
		}
		
		if (!validate.isText(transfer.getTitle())) {
			titleError = validate.errorText("tytu³");

			error = true;
		}
		else if (validate.isNumber(transfer.getTitle())) {
			titleError = validate.errorNotNumber("tytu³");

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
}