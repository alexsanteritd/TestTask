package try1.server.model;

import org.hibernate.SessionFactory;

public class LogsDao extends AbstractBaseDao<Logs>{

	public LogsDao(SessionFactory sessionFactory) {
		super(Logs.class, sessionFactory);
	}

}
