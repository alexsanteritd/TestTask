package try1.server.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import try1.server.model.AccountHistory;

public class AccountHistoryDao extends AbstractBaseDao<AccountHistory> {

	public AccountHistoryDao(SessionFactory sessionFactory) {
		super(AccountHistory.class, sessionFactory);
	}

	@SuppressWarnings("unchecked")
	public AccountHistory getAccountByUserId(long userId) {
		Query<AccountHistory> getAccountHistoryQuery = getSession()
				.createQuery("FROM AccountHistory ah WHERE ah.userId=:userId").setParameter("userId", userId);
		List<AccountHistory> ahList= getAccountHistoryQuery.list();
		AccountHistory ah=null;
		if(ahList.size()>0) {
			ah=ahList.get(ahList.size()-1);
		}
		return ah;
	}

}
