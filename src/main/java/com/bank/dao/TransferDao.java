package com.bank.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.bank.dao.interfaces.TransferDaoInterface;
import com.bank.models.Transfer;

public class TransferDao implements TransferDaoInterface {

	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Transfer> getTransfers() {
		try(Session session = sessionFactory.openSession()) {
			Query<Transfer> query = session.createQuery("FROM " + Transfer.class.getName());
			
			return query.getResultList();
		}
	}
	
	public List<Transfer> getTransfersByAccountId(int id) {
		List<Transfer> transfers = new ArrayList<Transfer>();
		List<Transfer> allTransfers = getTransfers();
		
		for (int i = 0; i < allTransfers.size(); ++i) {
			Transfer transfer = allTransfers.get(i);

			if (transfer.getAccount().getId() == id) {
				transfer.setAmount(transfer.getAmount().divide(new BigDecimal(100)));
				
				transfers.add(transfer);
			}
		}

		return transfers;
	}
	
	public Transfer getTransfer(int id) {
		try(Session session = sessionFactory.openSession()) {
			return (Transfer)session.get(Transfer.class.getName(), id);
		}
	}
	
	public void saveOrUpdateTransfer(Transfer transfer) {
		try(Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.saveOrUpdate(transfer);
			session.getTransaction().commit();
		}
	}
	
	public void deleteTransfer(Transfer transfer) {
		try(Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.delete(transfer);
			session.getTransaction().commit();
		}
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}