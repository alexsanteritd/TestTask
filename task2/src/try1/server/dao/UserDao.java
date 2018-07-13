package try1.server.dao;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import try1.server.model.AccountHistory;
import try1.server.model.User;

@Transactional
public class UserDao extends AbstractBaseDao<User> {
	public UserDao(SessionFactory sessionFactory) {
		super(User.class, sessionFactory);
	}

	
	@SuppressWarnings("unchecked")
	public User findByEmail(String email) {
		List<User> list = (List<User>) getSession().createQuery("FROM User u WHERE u.email=:email")
				.setParameter("email", email).list();
		return list.size() > 0 ? list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		List<Object[]> list = getSession().createQuery(
				"SELECT u, ah FROM User u, AccountHistory ah WHERE u.role=:user AND ah.userId = u.id AND ah.date=(SELECT max(ah1.date) FROM AccountHistory ah1 WHERE ah1.userId=u.id) ORDER BY u.email")
				.setParameter("user", "USER").getResultList();
		List<User> usersList = new ArrayList<User>();
		for (Object ob[] : list) {
			User user = (User) ob[0];
			AccountHistory ah = (AccountHistory) ob[1];
			user.setAccountHistory(ah);
			usersList.add(user);
		}
		return usersList;
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllUsers(int start, int limit) {
		Query selectUsersQuery = getSession().createQuery(
				"SELECT u, ah FROM User u, AccountHistory ah WHERE u.role=:user AND ah.userId = u.id AND ah.date=(SELECT max(ah1.date) FROM AccountHistory ah1 WHERE ah1.userId=u.id) ORDER BY u.email")
				.setParameter("user", "USER");
		selectUsersQuery.setFirstResult(start);
		selectUsersQuery.setMaxResults(limit);
		List<Object[]> list = selectUsersQuery.getResultList();
		List<User> usersList = new ArrayList<User>();
		for (Object ob[] : list) {
			User user = (User) ob[0];
			AccountHistory ah = (AccountHistory) ob[1];
			user.setAccountHistory(ah);
			usersList.add(user);
		}
		return usersList;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<User> findAllUsersSql() {
		Query selectUsersQuery = getSession().createNativeQuery(
				"SELECT u.*, a.id as account_id, a.user_id, a.admin_id, a.date, a.debet, a.credit, a.account FROM users u left outer join "
						+ "(Select a.* FROM accounthistory a LEFT OUTER JOIN accounthistory b ON a.user_id = b.user_id AND a.date < b.date WHERE b.id IS NULL) a "
						+ "ON a.user_id=u.id WHERE u.role=:role ORDER BY u.email")
				.setParameter("role", "USER");
		List<Object[]> list = selectUsersQuery.list();
		List<User> usersList = new ArrayList<User>();
		for (Object ob[] : list) {
			User user = new User();
			AccountHistory ah = new AccountHistory();
			user.setId(((BigInteger) ob[0]).longValue());
			user.setEmail((String) ob[1]);
			user.setPass((String) ob[2]);
			user.setRegistationsDate((Timestamp) ob[3]);
			user.setRole((String) ob[4]);
			ah.setId(((BigInteger) ob[5]).longValue());
			ah.setUserId(((BigInteger) ob[6]).longValue());
			ah.setAdmiId(((BigInteger) ob[7]).longValue());
			ah.setDate((Timestamp) ob[8]);
			ah.setDebet(((BigInteger) ob[9]).longValue());
			ah.setCredit(((BigInteger) ob[10]).longValue());
			ah.setAmount(((BigInteger) ob[11]).longValue());
			user.setAccountHistory(ah);
			usersList.add(user);
		}
		return usersList;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<User> findAllUsersSql(int start, int limit) {
		Query selectUsersQuery = getSession().createNativeQuery(
				"SELECT u.*, a.id as account_id, a.user_id, a.admin_id, a.date, a.debet, a.credit, a.account FROM users u left outer join "
						+ "(Select a.* FROM accounthistory a LEFT OUTER JOIN accounthistory b ON a.user_id = b.user_id AND a.date < b.date WHERE b.id IS NULL) a "
						+ "ON a.user_id=u.id WHERE u.role=:role ORDER BY u.email")
				.setParameter("role", "USER");
		selectUsersQuery.setFirstResult(start);
		selectUsersQuery.setMaxResults(limit);
		List<Object[]> list = selectUsersQuery.list();
		
		List<User> usersList = new ArrayList<User>();
		for (Object ob[] : list) {
			User user = new User();
			AccountHistory ah = new AccountHistory();
			user.setId(((BigInteger) ob[0]).longValue());
			user.setEmail((String) ob[1]);
			user.setPass((String) ob[2]);
			user.setRegistationsDate((Timestamp) ob[3]);
			user.setRole((String) ob[4]);
			ah.setId(((BigInteger) ob[5]).longValue());
			ah.setUserId(((BigInteger) ob[6]).longValue());
			ah.setAdmiId(((BigInteger) ob[7]).longValue());
			ah.setDate((Timestamp) ob[8]);
			ah.setDebet(((BigInteger) ob[9]).longValue());
			ah.setCredit(((BigInteger) ob[10]).longValue());
			ah.setAmount(((BigInteger) ob[11]).longValue());
			user.setAccountHistory(ah);
			usersList.add(user);
		}
		return usersList;
	}

	
	@SuppressWarnings("unchecked")
	public long countAllUsers() {
		Query<Long> countUsersQuery = getSession().createQuery("SELECT count(*) FROM User u WHERE u.role=:role")
				.setParameter("role", "USER");
		return countUsersQuery.getSingleResult();
	}

}
