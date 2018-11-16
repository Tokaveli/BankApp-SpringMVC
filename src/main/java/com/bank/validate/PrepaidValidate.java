package com.bank.validate;

import java.math.BigDecimal;

import com.bank.models.Prepaid;

public class PrepaidValidate {
	private Prepaid prepaid;
	
	private Validate validate;
	
	private boolean error;

	private String phoneNumberError;
	
	
	private String amountError;
	

	public PrepaidValidate(Prepaid prepaid) {
		super();
		
		this.prepaid = prepaid;
		
		this.validate = Validate.getInstance();
		
		this.error = false;
		this.phoneNumberError = "";
		this.amountError = "";
	}
	
	public Prepaid getPrepaid() {
		return prepaid;
	}

	public void setPrepaid(Prepaid prepaid) {
		this.prepaid = prepaid;
	}

	public boolean isError(BigDecimal balance) {
		
		
			
		if (prepaid.getPhoneNumber().length() != 9) {
			phoneNumberError = "Pole numer telefonu musi siê sk³adaæ z 9 cyfr";
			
			error = true;
		}
		else if (!validate.isNumber(prepaid.getPhoneNumber())) {
			phoneNumberError = validate.errorNumber("numer telefonu");
			
			error = true;
		}
		else if (!validate.isText(prepaid.getPhoneNumber())) {
			phoneNumberError = validate.errorText("numer telefonu");
			
			error = true;
		}
		
		if (prepaid.getAmount() == null) {
			amountError = "Pole kwota nie mo¿e byæ puste";
			
			error = true;
		}
		else if (prepaid.getAmount().multiply(new BigDecimal(100)).compareTo(balance) == 1) {
			amountError = "Pole kwota nie mo¿e byæ wiêksza ni¿ Twoje saldo";
			
			error = true;
		}
		else if (prepaid.getAmount().compareTo(new BigDecimal(1)) == -1) {
			amountError = "Pole kwota nie mo¿e byæ mniejsza ni¿ 1 z³";
			
			error = true;
		}
		
		
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}


	public String getPhoneNumberError() {
		return phoneNumberError;
	}

	public void setPhoneNumberError(String phoneNumberError) {
		this.phoneNumberError = phoneNumberError;
	}

	public String getAmountError() {
		return amountError;
	}

	public void setAmountError(String amountError) {
		this.amountError = amountError;
	}


}