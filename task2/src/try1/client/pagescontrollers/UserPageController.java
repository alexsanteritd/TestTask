package try1.client.pagescontrollers;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;

import try1.client.allpages.UserPage;
import try1.client.controller.ControllerI;

public class UserPageController implements PagesController {
	UserPage userPage;
	String loginPageName;
	ControllerI cont;

	public UserPageController(ControllerI cont, String loginPageName) {
		this.cont = cont;
		this.loginPageName = loginPageName;
		userPage=new UserPage();
		addHandlers();
	}

	public void onStart() {
		userPage.setBill(cont.getClientUser().getBillScore());
	}
	private void addHandlers() {
		userPage.getLogOut().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				cont.swapTo(loginPageName);
			}
			
		});
	}

	@Override
	public Widget getPageContent() {
		return userPage.getMainPanel();
	}

	@Override
	public void clear() {
		userPage.setBill(0f);
	}

	@Override
	public void onEnd() {
		clear();
		cont.setClientUser(null);
	}

}
