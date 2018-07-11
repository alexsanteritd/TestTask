package try1.client.serverdata;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;

import try1.client.model.CUser;

public class DataProvider extends AsyncDataProvider<CUser> {
	DataServiceAsync dataService;
	long userCount;

	public void setUserCount(long userCount) {
		this.userCount = userCount;
	}

	public DataProvider() {
		dataService = GWT.create(DataService.class);

	}

	protected void onRangeChanged(HasData<CUser> display) {
		final Range range = display.getVisibleRange();
		final HasData<CUser> disp = display;
		int start = range.getStart();
		dataService.getData(range, new AsyncCallback<List<CUser>>() {

			@Override
			public void onSuccess(List<CUser> result) {
				disp.setRowData(range.getStart(), result);

			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.toString());
			}
		});

	}

}
