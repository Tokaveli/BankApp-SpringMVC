package com.bank.dao.interfaces;

import java.util.List;

import com.bank.models.Errand;

public interface ErrandDaoInterface {

	public List<Errand> getErrands();
	
	public List<Errand> getErrandsByAccountId(int id);
	
	public Errand getErrand(int id);
	
	public void saveOrUpdateErrand(Errand errand);
	
	public void deleteErrand(Errand errand);
}