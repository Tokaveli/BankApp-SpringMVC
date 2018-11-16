package com.bank.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.bank.dao.interfaces.DepositDaoInterface;
import com.bank.models.Deposit;

public class DepositDao implements DepositDaoInterface {

	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Deposit> getDeposits() {
		try(Session session = sessionFactory.openSession()) {
			Query<Deposit> query = session.createQuery("FROM " + Deposit.class.getName());
			
			return query.getResultList();
		}
	}
	
	public List<Deposit> getDepositsByAccountId(int id) {
		List<Deposit> deposits = new ArrayList<Deposit>();
		List<Deposit> allDeposits = getDeposits();
		
		for (int i = 0; i < allDeposits.size(); ++i) {
			Deposit deposit = allDeposits.get(i);

			if (deposit.getAccount().getId() == id) {
				deposit.setAmount(deposit.getAmount().divide(new BigDecimal(100)));
				
				deposits.add(deposit);
			}
		}

		return deposits;
	}
	
	public Deposit getDeposit(int id) {
		try(Session session = sessionFactory.openSession()) {
			return (Deposit)session.get(Deposit.class.getName(), id);
		}
	}
	
	public void saveOrUpdateDeposit(Deposit deposit) {
		try(Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.saveOrUpdate(deposit);
			session.getTransaction().commit();
		}
	}
	
	public void deleteDeposit(Deposit deposit) {
		try(Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.delete(deposit);
			session.getTransaction().commit();
		}
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}