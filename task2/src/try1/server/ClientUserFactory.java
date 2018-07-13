package try1.server;

import try1.client.model.ClientUser;
import try1.server.model.User;

public class ClientUserFactory {
	public static ClientUser to(User user) {
		long id = -1;
		long account = 0;
		if (user.getRole().equals("ADMIN")) {
			id = user.getId();
		}
		if (user.getAccountHistory() != null) {
			account = user.getAccountHistory().getAmount();
		}
		return new ClientUser(id, user.getRole(), account);
	}
}
