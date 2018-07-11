package try1.server.model;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public class UserDao extends AbstractBaseDao<User>{
	public UserDao(SessionFactory sessionFactory) {
		super(User.class, sessionFactory);
	}

}
