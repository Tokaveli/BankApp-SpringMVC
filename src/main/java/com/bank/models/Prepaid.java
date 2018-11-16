package com.bank.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "prepaids")
public class Prepaid implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="id_account", nullable = false)
	private Account account;
	
	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;
	
	@Column(name = "phone_operator", nullable = false)
	private String phoneOperator;
	
	@Column(name = "amount", nullable = false)
	private BigDecimal amount;
	
	@Column(name = "send_date", nullable = false)
	private String sendDate;

	public Prepaid() {
		super();
	}

	public Prepaid(
		Account account, String phoneNumber, String phoneOperator,
		BigDecimal amount, String title, String sendDate
	) {
		super();

		this.account = account;
		this.phoneNumber = phoneNumber;
		this.phoneOperator = phoneOperator;
		this.amount = amount;
		this.sendDate = sendDate;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneOperator() {
		return phoneOperator;
	}

	public void setPhoneOperator(String phoneOperator) {
		this.phoneOperator = phoneOperator;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
}