package try1.server.services;

import try1.server.model.User;

public interface AutService {
	User loginUser(String login, String password);
	User createUser(String login, String password);
}
