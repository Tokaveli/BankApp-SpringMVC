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

@Entity
@Table(name = "debit_cards")
public class DebitCard implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="id_account", nullable = false)
	private Account account;
	
	@Column(name = "card_number", nullable = false)
	private String cardNumber;
	
	@Column(name = "pin", nullable = false)
	private String pin;
	
	@Column(name = "cvv", nullable = false)
	private String cvv;
	
	@Column(name = "expiry_date", nullable = false)
	private String expiryDate;

	public DebitCard() {
		super();
	}

	public DebitCard(Account account, String cardNumber, String pin, String cvv, String expiryDate) {
		super();

		this.account = account;
		this.cardNumber = cardNumber;
		this.pin = pin;
		this.cvv = cvv;
		this.expiryDate = expiryDate;
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

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
}