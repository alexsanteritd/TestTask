package try1.server.services;

import java.util.List;

import try1.client.model.ClientUser;
import try1.server.model.User;

public interface AutService {
	ClientUser loginUser(String login, String password);
	ClientUser createUser(String login, String password);
}
