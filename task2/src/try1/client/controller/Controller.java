package try1.client.controller;

import java.util.Map;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;

import try1.client.model.ClientUser;
import try1.client.pagescontrollers.PagesController;

public class Controller implements ControllerI {
	private PagesController activePageController;
	private RootPanel rootPanel;
	Map<String, PagesController> pagesMap;
	ClientUser clientUser= new ClientUser();

	public Controller(RootPanel rootPanel, Map<String, PagesController> pagesMap) {
		this.rootPanel = rootPanel;
		this.pagesMap = pagesMap;
	}

	public void swapTo(PagesController pageController) {
		if (activePageController != null) {
			activePageController.onEnd();
		}
		rootPanel.clear();
		pageController.onStart();
		rootPanel.add(pageController.getPageContent());
		activePageController = pageController;
	}

	public void swapTo(String pageName) {
		if (pagesMap.get(pageName) != null) {
			swapTo((PagesController) pagesMap.get(pageName));
		}
		else {
			Window.alert(pageName);
		}
	}

	@Override
	public ClientUser getClientUser() {
		return clientUser;
	}

	@Override
	public void setClientUser(ClientUser clientUser) {
		this.clientUser=clientUser;
		
	}

	
}
