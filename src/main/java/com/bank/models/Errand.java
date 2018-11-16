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
@Table(name = "errands")
public class Errand implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="id_account", nullable = false)
	private Account account;
	
	@Column(name = "receiver_name", nullable = false)
	private String receiverName;
	
	@Column(name = "receiver_address", nullable = false)
	private String receiverAddress;
	
	@Column(name = "account_number", nullable = false)
	private String accountNumber;
	
	@Column(name = "amount", nullable = false)
	private BigDecimal amount;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "send_date", nullable = false)
	private String sendDate;
	
	@Column(name = "period", nullable = false)
	private int period;

	public Errand() {
		super();
	}

	public Errand(
		Account account, String receiverName, String receiverAddress, String accountNumber,
		BigDecimal amount, String title, String sendDate,int period
	) {
		super();

		this.account = account;
		this.receiverName = receiverName;
		this.receiverAddress = receiverAddress;
		this.accountNumber = accountNumber;
		this.amount = amount;
		this.title = title;
		this.sendDate = sendDate;
		this.period = period;
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

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	
	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}
}