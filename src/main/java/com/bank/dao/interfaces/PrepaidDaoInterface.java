package com.bank.dao.interfaces;

import java.util.List;

import com.bank.models.Prepaid;

public interface PrepaidDaoInterface {

	public List<Prepaid> getPrepaids();
	
	public List<Prepaid> getPrepaidsByAccountId(int id);
	
	public Prepaid getPrepaid(int id);
	
	public void saveOrUpdatePrepaid(Prepaid prepaid);
	
	public void deletePrepaid(Prepaid prepaid);
}