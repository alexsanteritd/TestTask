package try1.server.services;

import java.util.List;

import try1.server.model.User;

public interface DataServerService {
	List<User> getData(int start, int end);
	Long getUsersCount();
	Long replenishAccount(long value, long userId, long adminId) throws Exception;
	Long debitAnAccount(long debitAmount, long userId, long adminId) throws Exception;
}
