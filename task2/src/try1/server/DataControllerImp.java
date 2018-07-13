package try1.server;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.gwt.view.client.Range;

import try1.client.model.CUser;
import try1.client.serverdata.DataService;
import try1.server.dao.CUserFactory;
import try1.server.model.User;
import try1.server.services.DataServerService;
import try1.server.singletoncontext.SingletonSpringContext;

public class DataControllerImp extends RemoteServiceServlet implements DataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6414105922524292637L;

	DataServerService dataServerService;

	@Override
	public List<CUser> getData(Range range) {
		List<User> usersList = getDataServerService().getData(range.getStart(), range.getLength());
		return CUserFactory.to(usersList);
	}

	@Override
	public long getUsersCount() {
		return getDataServerService().getUsersCount();
	}

	@Override
	public Long replenishAccount(long replenishAmount, long userID, long adminID) throws Exception {
		return getDataServerService().replenishAccount(replenishAmount, userID, adminID);
	}

	@Override
	public Long debitAnAccount(long debitAmount, long userID, long adminID) throws Exception {
		return getDataServerService().debitAnAccount(debitAmount, userID, adminID);
	}

	private DataServerService getDataServerService() {
		if (dataServerService == null) {
			dataServerService = SingletonSpringContext.getBean(DataServerService.class);
		}
		return dataServerService;
	}

}
