package com.bank.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.bank.dao.interfaces.AddressDaoInterface;
import com.bank.models.Address;

public class AddressDao implements AddressDaoInterface {

	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Address> getAddresses() {
		try(Session session = sessionFactory.openSession()) {
			Query<Address> query = session.createQuery("FROM " + Address.class.getName());
			
			return query.getResultList();
		}
	}
	
	public Address getAddress(int id) {
		try(Session session = sessionFactory.openSession()) {
			return (Address)session.get(Address.class.getName(), id);
		}
	}
	
	public void saveOrUpdateAddress(Address address) {
		try(Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.saveOrUpdate(address);
			session.getTransaction().commit();
		}
	}
	
	public void deleteAddress(Address address) {
		try(Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.delete(address);
			session.getTransaction().commit();
		}
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}