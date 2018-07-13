package try1.client.serverdata;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.view.client.Range;

import try1.client.model.CUser;

@RemoteServiceRelativePath("dataService")
public interface DataService  extends RemoteService {
	List<CUser> getData(Range range);
	long getUsersCount();
	Long debitAnAccount(long debitAmount, long userID, long adminID) throws Exception;
	Long replenishAccount(long replenishAmount, long userID, long adminID) throws Exception;
}
