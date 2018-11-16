package com.bank.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bank.validate.Validate;

@Entity
@Table(name = "addresses")
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static Validate validate = Validate.getInstance();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "street", nullable = false)
	private String street;
	
	@Column(name = "house_number", nullable = false)
	private String houseNumber;
	
	@Column(name = "city", nullable = false)
	private String city;
	
	@Column(name = "zip_code", nullable = false)
	private String zipCode;

	public Address() {
		super();
	}

	public Address(String street, String houseNumber, String city, String zipCode) {
		super();

		this.street = street;
		this.houseNumber = houseNumber;
		this.city = city;
		this.zipCode = zipCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = validate.capitalizeFirst(street);
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = validate.capitalizeFirst(houseNumber);
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = validate.capitalizeFirst(city);
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
}