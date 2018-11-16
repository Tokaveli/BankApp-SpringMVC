package com.bank.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.bank.dao.interfaces.ErrandDaoInterface;
import com.bank.models.Errand;

public class ErrandDao implements ErrandDaoInterface {

	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Errand> getErrands() {
		try(Session session = sessionFactory.openSession()) {
			Query<Errand> query = session.createQuery("FROM " + Errand.class.getName());
			
			return query.getResultList();
		}
	}
	
	public List<Errand> getErrandsByAccountId(int id) {
		List<Errand> errands = new ArrayList<Errand>();
		List<Errand> allErrands = getErrands();
		
		for (int i = 0; i < allErrands.size(); ++i) {
			Errand errand = allErrands.get(i);

			if (errand.getAccount().getId() == id) {
				errand.setAmount(errand.getAmount().divide(new BigDecimal(100)));
				
				errands.add(errand);
			}
		}

		return errands;
	}
	
	public Errand getErrand(int id) {
		try(Session session = sessionFactory.openSession()) {
			return (Errand)session.get(Errand.class.getName(), id);
		}
	}
	
	public void saveOrUpdateErrand(Errand errand) {
		try(Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.saveOrUpdate(errand);
			session.getTransaction().commit();
		}
	}
	
	public void deleteErrand(Errand errand) {
		try(Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.delete(errand);
			session.getTransaction().commit();
		}
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}