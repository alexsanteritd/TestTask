package try1.client.pagescontrollers;

import java.sql.Timestamp;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

import try1.client.allpages.AdminPage;
import try1.client.allpages.MyDialog;
import try1.client.controller.ControllerI;
import try1.client.model.CUser;
import try1.client.model.ClientUser;
import try1.client.serverdata.DataService;
import try1.client.serverdata.DataServiceAsync;

public class AdminPageController implements PagesController {
	private AdminPage ap = new AdminPage();
	private MyDialog md = new MyDialog();
	DataServiceAsync dataService;
	ControllerI cont;

	@Override
	public Widget getPageContent() {
		return ap.getMainPanel();
	}

	public AdminPageController(ControllerI cont) {
		this();
		this.cont=cont;
		cont.setClientUser(new ClientUser(0,"admin",new Timestamp(System.currentTimeMillis())));
	}

	public AdminPageController() {
		dataService = GWT.create(DataService.class);

		ap.getLogAnchor().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Window.alert("Переключение");

			}
		});
		ap.getEmail().setFieldUpdater(new FieldUpdater<CUser, String>() {
			@Override
			public void update(int index, CUser object, String value) {
				md.setEmailLabel(object.getEmail());
				md.setCUser(object);
				md.show();
				md.getScoreBox().setFocus(true);
			}
		});

		ap.getSearchButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Window.alert("Поиск");

			}
		});

		md.getOk().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				md.hide();
				try {
				dataService.updateScore(Float.parseFloat(md.getScoreBox().getText()), md.getCUser().getEmail(),cont.getClientUser().getEmail(), new AsyncCallback<Float>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.toString());
					}

					@Override
					public void onSuccess(Float result) {
						md.getCUser().setBillScore(result);
						ap.getDataTable().redraw();
					}
				});
				}catch(Exception e) {
					Window.alert(e.toString());
				}
				md.getScoreBox().setText("");
			}
		});

		md.getNo().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				md.hide();
				md.getScoreBox().setText("");
			}
		});

	}

	@Override
	public void onStart() {
		ap.getDataTable().redraw();
		ap.getDataTable().setVisible(true);

	}

	@Override
	public void onEnd() {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

}