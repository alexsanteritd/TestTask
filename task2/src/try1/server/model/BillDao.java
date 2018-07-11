package try1.server.model;

import org.hibernate.SessionFactory;

public class BillDao extends AbstractBaseDao<Bill>{

	public BillDao(SessionFactory sessionFactory) {
		super(Bill.class, sessionFactory);
	}

}
