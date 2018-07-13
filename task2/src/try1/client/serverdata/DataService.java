package try1.client.serverdata;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.view.client.Range;

import try1.client.model.CUser;

@RemoteServiceRelativePath("dataService")
public interface DataService extends RemoteService {
	List<CUser> getData(Range range);
	long getUsersCount();
	Long updateScore(long difValue, long userID, long adminID);
}
