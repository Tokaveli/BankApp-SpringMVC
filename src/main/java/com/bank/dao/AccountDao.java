package com.bank.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.bank.dao.interfaces.AccountDaoInterface;
import com.bank.models.Account;

public class AccountDao implements AccountDaoInterface {

	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Account> getAccounts() {
		try(Session session = sessionFactory.openSession()) {
			Query<Account> query = session.createQuery("FROM " + Account.class.getName());
			
			return query.getResultList();
		}
	}
	
	public Account getAccount(int id) {
		try(Session session = sessionFactory.openSession()) {
			return (Account)session.get(Account.class.getName(), id);
		}
	}
	
	public void saveOrUpdateAccount(Account account) {
		try(Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.saveOrUpdate(account);
			session.getTransaction().commit();
		}
	}
	
	public void deleteAccount(Account account) {
		try(Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.delete(account);
			session.getTransaction().commit();
		}
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}