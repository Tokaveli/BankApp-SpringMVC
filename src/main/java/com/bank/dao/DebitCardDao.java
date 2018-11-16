package com.bank.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.bank.dao.interfaces.DebitCardDaoInterface;
import com.bank.models.DebitCard;

public class DebitCardDao implements DebitCardDaoInterface {

	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<DebitCard> getDebitCards() {
		try(Session session = sessionFactory.openSession()) {
			Query<DebitCard> query = session.createQuery("FROM " + DebitCard.class.getName());
			
			return query.getResultList();
		}
	}

	public DebitCard getDebitCard(int id) {
		try(Session session = sessionFactory.openSession()) {
			return (DebitCard)session.get(DebitCard.class.getName(), id);
		}
	}
	
	public DebitCard getDebitCardByAccountId(int id) {
		List<DebitCard> debitCards = getDebitCards();
		
		for (int i = 0; i < debitCards.size(); ++i) {
			DebitCard debitCard = debitCards.get(i);
			
			if (debitCard.getAccount().getId() == id) {
				return debitCard;
			}
		}

		return null;
	}
	
	public void saveOrUpdateDebitCard(DebitCard debitCard) {
		try(Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.saveOrUpdate(debitCard);
			session.getTransaction().commit();
		}
	}
	
	public void deleteDebitCard(DebitCard debitCard) {
		try(Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.delete(debitCard);
			session.getTransaction().commit();
		}
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}