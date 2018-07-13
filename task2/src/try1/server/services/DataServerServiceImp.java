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
	public Long replenishAccount(long repelishAmount, long userId, long adminId) throws Exception {
		if (repelishAmount < 0) {
			throw new Exception("Пополнение счета отрицательным значением суммы");
		}
		if (repelishAmount == 0)
			return 0l;
		if (userId <= 0)
			throw new Exception("Неккоректный id пользователя");
		if (adminId <= 0)
			throw new Exception("Неккоректный id администратора");
		AccountHistory accountHistory = acountHistoryDao.getAccountByUserIdWithBlock(userId);
		long account = 0;
		if (accountHistory == null) {
			acountHistoryDao.unlock();
			throw new Exception("Истории пользователя не существует");
		}
		AccountHistory newAccountHistory;
		account = accountHistory.getAmount();
		account += repelishAmount;
		newAccountHistory = new AccountHistory(repelishAmount, 0, account);
		newAccountHistory.setUserId(userId);
		newAccountHistory.setAdmiId(adminId);
		acountHistoryDao.create(newAccountHistory);
		return account;
	}

	@Override
	public Long debitAnAccount(long debitAmount, long userId, long adminId) throws Exception {
		if (debitAmount < 0) {
			throw new Exception("Списание со счета отрицательным значением суммы");
		}
		if (debitAmount == 0)
			return 0l;
		if (userId <= 0)
			throw new Exception("Неккоректный id пользователя");
		if (adminId <= 0)
			throw new Exception("Неккоректный id администратора");
		AccountHistory accountHistory = acountHistoryDao.getAccountByUserIdWithBlock(userId);
		long account = 0;
		if (accountHistory == null) {
			acountHistoryDao.unlock();
			throw new Exception("Истории пользователя не существует");
		}
		AccountHistory newAccountHistory;
		account = accountHistory.getAmount();
		account -= debitAmount;
		newAccountHistory = new AccountHistory(0, debitAmount, account);
		newAccountHistory.setUserId(userId);
		newAccountHistory.setAdmiId(adminId);
		acountHistoryDao.create(newAccountHistory);
		return account;
	}
}