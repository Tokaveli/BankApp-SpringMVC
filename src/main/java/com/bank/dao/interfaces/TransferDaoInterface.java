package com.bank.dao.interfaces;

import java.util.List;

import com.bank.models.Transfer;

public interface TransferDaoInterface {

	public List<Transfer> getTransfers();
	
	public List<Transfer> getTransfersByAccountId(int id);
	
	public Transfer getTransfer(int id);
	
	public void saveOrUpdateTransfer(Transfer transfer);
	
	public void deleteTransfer(Transfer transfer);
}