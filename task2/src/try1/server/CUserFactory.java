package try1.server;

import java.util.ArrayList;
import java.util.List;

import try1.client.model.CUser;
import try1.server.model.AccountHistory;
import try1.server.model.User;

public class CUserFactory {
	CUserFactory() {

	}

	public static List<CUser> to(List<User> users) {
		List<CUser> cUserList = new ArrayList<CUser>();
		for (User u : users) {
			AccountHistory ah = u.getAccountHistory();
			long account = 0;
			if (ah != null) {
				account = ah.getAccount();
			}
			cUserList.add(new CUser(u.getId(),account, u.getRegistationsDate(), u.getEmail()));
		}
		return cUserList;

	}

	public static CUser to(User user) {
		AccountHistory ah = user.getAccountHistory();
		long account = 0;
		if (ah != null) {
			account = ah.getAccount();
		};
		return new CUser(user.getId(),account, user.getRegistationsDate(), user.getEmail());

	}
}
