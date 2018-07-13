package try1.server.services;

import try1.server.model.User;

public interface AuthorizationServise {
	User loginUser(String login, String password);
	User createUser(String login, String password);
}
