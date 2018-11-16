package com.bank.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.bank.dao.interfaces.UserDaoInterface;
import com.bank.models.User;

public class UserDao implements UserDaoInterface {

	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<User> getUsers() {
		try(Session session = sessionFactory.openSession()) {
			Query<User> query = session.createQuery("FROM " + User.class.getName());
			
			return query.getResultList();
		}
	}
	
	public User getUser(int id) {
		try(Session session = sessionFactory.openSession()) {
			return (User)session.get(User.class.getName(), id);
		}
	}
	
	public User getUserByAccountId(int id) {
		List<User> users = getUsers();
		
		for (int i = 0; i < users.size(); ++i) {
			User user = users.get(i);
			
			if (user.getAccount().getId() == id) {
				return user;
			}
		}

		return null;
	}
	
	public void saveOrUpdateUser(User user) {
		try(Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.saveOrUpdate(user);
			session.getTransaction().commit();
		}
	}
	
	public void deleteUser(User user) {
		try(Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.delete(user);
			session.getTransaction().commit();
		}
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}