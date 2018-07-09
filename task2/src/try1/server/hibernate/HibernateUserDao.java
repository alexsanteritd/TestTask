package try1.server.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import try1.server.model.User;
import try1.server.model.UserDao;

public class HibernateUserDao implements UserDao {
	private final SessionFactory sessionFactory;

	public HibernateUserDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<User> getAll() {
		Session session = sessionFactory.openSession();
		try {
			return session.createQuery("Select u FROM User u", User.class).list();
		} finally {
			session.close();
		}
	}

	public User getByEmail(String email) {
		Session session = sessionFactory.openSession();
		try {
			return session.get(User.class, email);
		} finally {
			session.close();
		}
	}

	public void addUser(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			session.saveOrUpdate(user);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}

	}

}
