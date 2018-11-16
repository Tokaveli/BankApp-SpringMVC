package com.bank.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.bank.dao.interfaces.LoanDaoInterface;
import com.bank.models.Loan;

public class LoanDao implements LoanDaoInterface {

	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Loan> getLoans() {
		try(Session session = sessionFactory.openSession()) {
			Query<Loan> query = session.createQuery("FROM " + Loan.class.getName());
			
			return query.getResultList();
		}
	}
	
	public List<Loan> getLoansByAccountId(int id) {
		List<Loan> loans = new ArrayList<Loan>();
		List<Loan> allLoans = getLoans();
		
		for (int i = 0; i < allLoans.size(); ++i) {
			Loan loan = allLoans.get(i);

			if (loan.getAccount().getId() == id) {
				loan.setAmount(loan.getAmount().divide(new BigDecimal(100)));
				
				loans.add(loan);
			}
		}

		return loans;
	}
	
	public Loan getLoan(int id) {
		try(Session session = sessionFactory.openSession()) {
			return (Loan)session.get(Loan.class.getName(), id);
		}
	}
	
	public void saveOrUpdateLoan(Loan loan) {
		try(Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.saveOrUpdate(loan);
			session.getTransaction().commit();
		}
	}
	
	public void deleteLoan(Loan loan) {
		try(Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.delete(loan);
			session.getTransaction().commit();
		}
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}