package com.bank.validate;

import com.bank.models.Address;

public class AddressValidate {
	private Address address;
	
	private Validate validate;
	
	private boolean error;
	
	private String streetError;
	private String houseNumberError;
	private String cityError;
	private String zipCodeError;

	public AddressValidate(Address address) {
		super();
		
		this.address = address;
		
		this.validate = Validate.getInstance();
		
		this.error = false;

		this.streetError = "";
		this.houseNumberError = "";
		this.cityError = "";
		this.zipCodeError = "";
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public boolean isError() {
		if (!validate.isText(address.getStreet())) {
			streetError = validate.errorText("ulica");
			
			error = true;
		}
		else if (validate.isNumber(address.getStreet())) {
			streetError = validate.errorNotNumber("ulica");

			error = true;
		}
		
		if (!validate.isText(address.getHouseNumber())) {
			houseNumberError = validate.errorText("numer domu, lokalu");
			
			error = true;
		}
		else if (!validate.isNumber(address.getHouseNumber())) {
			houseNumberError = validate.errorNumber("numer domu, lokalu");
			
			error = true;
		}
		
		if (!validate.isText(address.getCity())) {
			cityError = validate.errorText("miejscowoœæ");
			
			error = true;
		}
		else if (validate.isNumber(address.getCity())) {
			cityError = validate.errorNotNumber("miejscowoœæ");

			error = true;
		}
		
		
		if (!address.getZipCode().matches("[0-9]{2}-[0-9]{3}")) {
			zipCodeError = "Pole kod pocztowy zawiera niepoprawny format (00-000)";
			
			error = true;
		}
		else if (!validate.isText(address.getZipCode())) {
			zipCodeError = validate.errorText("kod pocztowy");
			
			error = true;
		}
		
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getStreetError() {
		return streetError;
	}

	public void setStreetError(String streetError) {
		this.streetError = streetError;
	}

	public String getHouseNumberError() {
		return houseNumberError;
	}

	public void setHouseNumberError(String houseNumberError) {
		this.houseNumberError = houseNumberError;
	}

	public String getCityError() {
		return cityError;
	}

	public void setCityError(String cityError) {
		this.cityError = cityError;
	}

	public String getZipCodeError() {
		return zipCodeError;
	}

	public void setZipCodeError(String zipCodeError) {
		this.zipCodeError = zipCodeError;
	}
}