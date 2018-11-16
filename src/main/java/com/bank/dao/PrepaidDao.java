package com.bank.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.bank.dao.interfaces.PrepaidDaoInterface;
import com.bank.models.Prepaid;

public class PrepaidDao implements PrepaidDaoInterface {

	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Prepaid> getPrepaids() {
		try(Session session = sessionFactory.openSession()) {
			Query<Prepaid> query = session.createQuery("FROM " + Prepaid.class.getName());
			
			return query.getResultList();
		}
	}
	
	public List<Prepaid> getPrepaidsByAccountId(int id) {
		List<Prepaid> prepaids = new ArrayList<Prepaid>();
		List<Prepaid> allPrepaids = getPrepaids();
		
		for (int i = 0; i < allPrepaids.size(); ++i) {
			Prepaid prepaid = allPrepaids.get(i);

			if (prepaid.getAccount().getId() == id) {
				prepaid.setAmount(prepaid.getAmount().divide(new BigDecimal(100)));
				
				prepaids.add(prepaid);
			}
		}

		return prepaids;
	}
	
	public Prepaid getPrepaid(int id) {
		try(Session session = sessionFactory.openSession()) {
			return (Prepaid)session.get(Prepaid.class.getName(), id);
		}
	}
	
	public void saveOrUpdatePrepaid(Prepaid prepaid) {
		try(Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.saveOrUpdate(prepaid);
			session.getTransaction().commit();
		}
	}
	
	public void deletePrepaid(Prepaid prepaid) {
		try(Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.delete(prepaid);
			session.getTransaction().commit();
		}
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}