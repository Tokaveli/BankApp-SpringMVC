package com.bank.validate;

import com.bank.models.User;

public class UserValidate {
	private User user;
	
	private Validate validate;
	
	private boolean error;

	private String firstnameError;
	private String lastnameError;
	private String pinError;
	private String seriesNumberError;
	private String expiryDateError;
	private String birthDateError;
	private String birthplaceError;
	private String mothersMaidenNameError;
	private String phoneNumberError;
	private String emailError;

	public UserValidate(User user) {
		super();
		
		this.user = user;
		
		this.validate = Validate.getInstance();
		
		this.error = false;

		this.firstnameError = "";
		this.lastnameError = "";
		this.pinError = "";
		this.seriesNumberError = "";
		this.expiryDateError = "";
		this.birthDateError = "";
		this.birthplaceError = "";
		this.mothersMaidenNameError = "";
		this.phoneNumberError = "";
		this.emailError = "";
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isError() {
		if (!validate.isText(user.getFirstname())) {
			firstnameError = validate.errorText("imiê");

			error = true;
		}
		else if (validate.isNumber(user.getFirstname())) {
			firstnameError = validate.errorNotNumber("imiê");

			error = true;
		}
		
		if (!validate.isText(user.getLastname())) {
			lastnameError = validate.errorText("nazwisko");
			
			error = true;
		}
		else if (validate.isNumber(user.getLastname())) {
			lastnameError = validate.errorNotNumber("nazwisko");

			error = true;
		}
		
		if (user.getPin().length() != 11) {
			pinError = "Pole pesel musi siê sk³adaæ z 11 cyfr";
			
			error = true;
		}
		else if (!validate.isNumber(user.getPin())) {
			pinError = validate.errorNumber("pesel");
			
			error = true;
		}
		else if (!validate.isText(user.getPin())) {
			pinError = validate.errorText("pesel");
			
			error = true;
		}
		
		if (user.getSeriesNumber().length() != 9) {
			seriesNumberError = "Pole seria i numer dowodu osobistego musi siê sk³adaæ z 9 znaków";
			
			error = true;
		}
		else if (!validate.isText(user.getSeriesNumber())) {
			seriesNumberError = validate.errorText("seria i numer dowodu osobistego");
			
			error = true;
		}
		
		if (!validate.isText(user.getExpiryDate())) {
			expiryDateError = validate.errorText("data wa¿noœci");
			
			error = true;
		}
		else if (!validate.isDate(user.getExpiryDate())) {
			expiryDateError = validate.errorDate("data wa¿noœci");
			
			error = true;
		}
		
		if (!validate.isText(user.getBirthDate())) {
			birthDateError = validate.errorText("data urodzenia");
			
			error = true;
		}
		else if (!validate.isDate(user.getBirthDate())) {
			birthDateError = validate.errorDate("data urodzenia");
			
			error = true;
		}
		
		if (!validate.isText(user.getBirthplace())) {
			birthplaceError = validate.errorText("miejsce urodzenia");
			
			error = true;
		}
		else if (validate.isNumber(user.getBirthplace())) {
			birthplaceError = validate.errorNotNumber("miejsce urodzenia");

			error = true;
		}
		
		if (!validate.isText(user.getMothersMaidenName())) {
			mothersMaidenNameError = validate.errorText("nazwisko rodowe matki");
			
			error = true;
		}
		else if (validate.isNumber(user.getMothersMaidenName())) {
			mothersMaidenNameError = validate.errorNotNumber("nazwisko rodowe matki");

			error = true;
		}
		
		if (user.getPhoneNumber().length() != 9) {
			phoneNumberError = "Pole telefon komórkowy musi zawieraæ 9 cyfr";
			
			error = true;
		}
		else if (!validate.isText(user.getPhoneNumber())) {
			phoneNumberError = validate.errorText("telefon komórkowy");
			
			error = true;
		}
		else if (!validate.isNumber(user.getPhoneNumber())) {
			phoneNumberError = validate.errorNumber("telefon komórkowy");
			
			error = true;
		}
		
		if (!user.getEmail().matches("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")) {
			emailError = "Pole adres email zawiera niepoprawny format";
			
			error = true;
		}
		else if (!validate.isText(user.getEmail())) {
			emailError = validate.errorText("adres email");
			
			error = true;
		}
		
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getFirstnameError() {
		return firstnameError;
	}

	public void setFirstnameError(String firstnameError) {
		this.firstnameError = firstnameError;
	}

	public String getLastnameError() {
		return lastnameError;
	}

	public void setLastnameError(String lastnameError) {
		this.lastnameError = lastnameError;
	}

	public String getPinError() {
		return pinError;
	}

	public void setPinError(String pinError) {
		this.pinError = pinError;
	}

	public String getSeriesNumberError() {
		return seriesNumberError;
	}

	public void setSeriesNumberError(String seriesNumberError) {
		this.seriesNumberError = seriesNumberError;
	}

	public String getExpiryDateError() {
		return expiryDateError;
	}

	public void setExpiryDateError(String expiryDateError) {
		this.expiryDateError = expiryDateError;
	}
	
	public String getBirthDateError() {
		return birthDateError;
	}

	public void setBirthDateError(String birthDateError) {
		this.birthDateError = birthDateError;
	}

	public String getBirthplaceError() {
		return birthplaceError;
	}

	public void setBirthplaceError(String birthplaceError) {
		this.birthplaceError = birthplaceError;
	}

	public String getMothersMaidenNameError() {
		return mothersMaidenNameError;
	}

	public void setMothersMaidenNameError(String mothersMaidenNameError) {
		this.mothersMaidenNameError = mothersMaidenNameError;
	}

	public String getPhoneNumberError() {
		return phoneNumberError;
	}

	public void setPhoneNumberError(String phoneNumberError) {
		this.phoneNumberError = phoneNumberError;
	}

	public String getEmailError() {
		return emailError;
	}

	public void setEmailError(String emailError) {
		this.emailError = emailError;
	}
}