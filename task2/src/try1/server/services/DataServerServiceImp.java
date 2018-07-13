package try1.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import try1.server.dao.AccountHistoryDao;
import try1.server.dao.UserDao;
import try1.server.model.AccountHistory;
import try1.server.model.User;

@Service
public class DataServerServiceImp implements DataServerService {
	@Autowired
	UserDao userDao;
	@Autowired
	AccountHistoryDao acountHistoryDao;

	@Override
	public List<User> getData(int start, int end) {
		return userDao.findAllUsers(start, end);
	}

	@Override
	public Long getUsersCount() {
		return userDao.countAllUsers();
	}

	@Override
	public Long changeAccount(long difValue, long userId, long adminId) {
		AccountHistory accountHistory = acountHistoryDao.getAccountByUserId(userId);
		long account = 0;
		if (difValue != 0 && userId > 0 && adminId > 0) {
			if (accountHistory != null) {
				AccountHistory newAccountHistory;
				account = accountHistory.getAccount();
				if (difValue > 0) {
					account += difValue;
					newAccountHistory = new AccountHistory(difValue, 0, account);
				} else {
					account += difValue;
					newAccountHistory = new AccountHistory(0, difValue, account);
				}
				newAccountHistory.setUserId(userId);
				newAccountHistory.setAdmiId(adminId);
				acountHistoryDao.create(newAccountHistory);
			}
		}
		return account;
	}
}
