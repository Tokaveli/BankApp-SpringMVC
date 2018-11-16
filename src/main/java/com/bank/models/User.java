package com.bank.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bank.validate.Validate;

@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static Validate validate = Validate.getInstance();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="id_account", nullable = false)
	private Account account;
	
	@ManyToOne
	@JoinColumn(name="id_address", nullable = false)
	private Address address;
	
	@Column(name = "firstname", nullable = false)
	private String firstname;
	
	@Column(name = "lastname", nullable = false)
	private String lastname;
	
	/*
	 * pin oznacza pesel (ang. Personal Identification Number)
	 */
	@Column(name = "pin", nullable = false)
	private String pin;
	
	/*
	 * seriesNumber oznacza seriê i numer dowodu osobistego
	 */
	@Column(name = "series_number", nullable = false)
	private String seriesNumber;
	
	/*
	 * expiryDate oznacza datê wa¿noœci
	 */
	@Column(name = "expiry_date", nullable = false)
	private String expiryDate;
	
	@Column(name = "birth_date", nullable = false)
	private String birthDate;
	
	@Column(name = "birthplace", nullable = false)
	private String birthplace;
	
	@Column(name = "mothers_maiden_name", nullable = false)
	private String mothersMaidenName;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;

	public User() {
		super();
	}

	public User(
		Account account, Address address, String firstname, String lastname, String pin, String seriesNumber,
		String expiryDate, String birthDate, String birthplace, String mothersMaidenName, String email, String phoneNumber
	) {
		super();

		this.account = account;
		this.address = address;
		this.firstname = firstname;
		this.lastname = lastname;
		this.pin = pin;
		this.seriesNumber = seriesNumber;
		this.expiryDate = expiryDate;
		this.birthDate = birthDate;
		this.birthplace = birthplace;
		this.mothersMaidenName = mothersMaidenName;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = validate.capitalizeFirst(firstname);
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = validate.capitalizeFirst(lastname);
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getSeriesNumber() {
		return seriesNumber;
	}

	public void setSeriesNumber(String seriesNumber) {
		this.seriesNumber = seriesNumber.toUpperCase();
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = validate.capitalizeFirst(birthplace);
	}

	public String getMothersMaidenName() {
		return mothersMaidenName;
	}

	public void setMothersMaidenName(String mothersMaidenName) {
		this.mothersMaidenName = validate.capitalizeFirst(mothersMaidenName);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email.toLowerCase();
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}