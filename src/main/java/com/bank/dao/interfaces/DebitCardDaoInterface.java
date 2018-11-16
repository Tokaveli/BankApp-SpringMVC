package com.bank.dao.interfaces;

import java.util.List;

import com.bank.models.DebitCard;

public interface DebitCardDaoInterface {

	public List<DebitCard> getDebitCards();
	
	public DebitCard getDebitCard(int id);
	
	public DebitCard getDebitCardByAccountId(int id);
	
	public void saveOrUpdateDebitCard(DebitCard debitCard);
	
	public void deleteDebitCard(DebitCard debitCard);
}