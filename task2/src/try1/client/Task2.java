package try1.client;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

import try1.client.controller.Controller;
import try1.client.controller.ControllerI;
import try1.client.pagescontrollers.AdminPageController;
import try1.client.pagescontrollers.LoginPageController;
import try1.client.pagescontrollers.PagesController;
import try1.client.pagescontrollers.SignUpPageController;
import try1.client.pagescontrollers.UserPageController;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Task2 implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private final String contentContainerId = "body";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		Map<String, PagesController> pagesMap=new HashMap<String, PagesController>();
		ControllerI cont=new Controller(RootPanel.get(contentContainerId),pagesMap);
		pagesMap.put("LOGIN",new LoginPageController(cont,"SIGN_UP"));
		pagesMap.put("ADMIN", new AdminPageController(cont));
		pagesMap.put("USER", new UserPageController(cont,"LOGIN"));
		pagesMap.put("SIGN_UP", new SignUpPageController(cont,"LOGIN"));
		cont.swapTo("LOGIN");
		
		/**/

	}

}
