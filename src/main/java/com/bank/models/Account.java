package com.bank.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "accounts")
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static int loginLength = 9;
	private static String loginCharacters = "0123456789";
	
	private static int passwordLength = 13;
	private static String passwordCharacters = "0123456789abcdefghijklmnoprstuwxyz!@#$%^&*()";
	
	private static int accountNumberLength = 26;
	private static String accountNumberCharacters = "0123456789";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "login", nullable = false)
	private String login;
	
	@Transient
	private String password;
	
	@Column(name = "password_hash", nullable = false)
	private String passwordHash;
	
	@Column(name = "account_number", nullable = false)
	private String accountNumber;
	
	@Column(name = "balance", nullable = false)
	private BigDecimal balance;

	public Account() {
		super();
		
		this.login = generateLogin();
		this.password = generatePassword();
		this.passwordHash = SHA256(this.password);
		this.accountNumber = generateAccountNumber();
		this.balance = new BigDecimal("0");
	}

	public Account(String login, String password, String passwordHash, String accountNumber, BigDecimal balance) {
		super();

		this.login = login;
		this.password = password;
		this.passwordHash = passwordHash;
		this.accountNumber = accountNumber;
		this.balance = balance;
	}
	
	public String generateLogin() {
		Random random = new Random();
		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0; i < loginLength; ++i) {
			int randomInteger = random.nextInt(loginCharacters.length());
			
			stringBuilder.append(loginCharacters.charAt(randomInteger));
		}

		return stringBuilder.toString();
	}
	
	public String generatePassword() {
		Random random = new Random();
		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0; i < passwordLength; ++i) {
			int randomInteger = random.nextInt(passwordCharacters.length());
			
			stringBuilder.append(passwordCharacters.charAt(randomInteger));
		}

		return stringBuilder.toString();
	}
	
	public String generateAccountNumber() {
		Random random = new Random();
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("680000");
		
		for (int i = 0; i < accountNumberLength - 6; ++i) {
			int randomInteger = random.nextInt(accountNumberCharacters.length());
			
			stringBuilder.append(accountNumberCharacters.charAt(randomInteger));
		}

		return stringBuilder.toString();
	}
	
	/*
	 * Funkcja hashuj¹ca has³o metod¹ SHA-256
	 */
	public static String SHA256(String password) {
	    try {
	        MessageDigest md = MessageDigest.getInstance("SHA-256");

	        byte[] hash = md.digest(password.getBytes("UTF-8"));

	        StringBuffer hexString = new StringBuffer();

	        for (int i = 0; i < hash.length; ++i) {
	            String hex = Integer.toHexString(0xff & hash[i]);
	            
	            if(hex.length() == 1) hexString.append('0');
	            
	            hexString.append(hex);
	        }

	        return hexString.toString();
	    } catch(Exception e) {
    		throw new RuntimeException(e);
	    }
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
}