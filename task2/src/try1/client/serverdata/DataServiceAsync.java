package try1.client.serverdata;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.Range;

import try1.client.model.CUser;


public interface DataServiceAsync {
	void getData(Range range, AsyncCallback<List<CUser>> callback);

	void getUsersCount(AsyncCallback<Long> callback);


	void debitAnAccount(long debitAmount, long userID, long adminID, AsyncCallback<Long> callback);

	void replenishAccount(long replenishAmount, long userID, long adminID, AsyncCallback<Long> callback);
}
