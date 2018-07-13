package try1.server;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.gwt.view.client.Range;

import try1.client.model.CUser;
import try1.client.serverdata.DataService;
import try1.server.model.User;
import try1.server.services.DataServerService;
import try1.server.singletoncontext.SingletonSpringContext;

public class DataControllerImp extends RemoteServiceServlet implements DataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6414105922524292637L;

	DataServerService dataSer;
	
	@Override
	public List<CUser> getData(Range range) {
		List<User> usersList=getDataSer().getData(range.getStart(), range.getLength());
		return CUserFactory.to(usersList);
	}

	@Override
	public long getUsersCount() {
		return getDataSer().getUsersCount();
	}

	@Override
	public Long updateScore(long difValue, long userID,long adminID) {
		return getDataSer().changeAccount(difValue, userID,adminID);
		
	}
	private DataServerService getDataSer() {
		if(dataSer==null) {
			dataSer=SingletonSpringContext.getInstance().getContext().getBean(DataServerService.class);
		}
		return dataSer;
	}

}
