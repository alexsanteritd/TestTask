package try1.server.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import try1.server.model.Bill;
import try1.server.model.BillDao;
public class HibernateBillDao implements BillDao {
	private final SessionFactory sessionFactory;

	public HibernateBillDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addBill(Bill bill) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			session.saveOrUpdate(bill);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
		
	}

}
