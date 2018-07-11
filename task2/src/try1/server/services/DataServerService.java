package try1.server.services;

import java.util.List;

import try1.client.model.CUser;

public interface DataServerService {
	List<CUser> getData(int start, int end);
	Long getUsersCount();
	Float updateScore(float plusValue, String email, String adminEmail);
}
