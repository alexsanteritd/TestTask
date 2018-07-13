package try1.server.services;

import java.util.List;

import try1.server.model.User;

public interface DataServerService {
	List<User> getData(int start, int end);
	Long getUsersCount();
	Long changeAccount(long difValue, long userId, long adminId);
}
