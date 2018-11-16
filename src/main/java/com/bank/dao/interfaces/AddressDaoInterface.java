package com.bank.dao.interfaces;

import java.util.List;

import com.bank.models.Address;

public interface AddressDaoInterface {

	public List<Address> getAddresses();
	
	public Address getAddress(int id);
	
	public void saveOrUpdateAddress(Address address);
	
	public void deleteAddress(Address address);
}