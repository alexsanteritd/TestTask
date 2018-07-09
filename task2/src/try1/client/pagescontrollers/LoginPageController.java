package try1.client.pagescontrollers;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

import try1.client.allpages.LoginPage;
import try1.client.controller.ControllerI;
import try1.client.loginservice.LoginService;
import try1.client.loginservice.LoginServiceAsync;
import try1.client.model.ClientUser;

public class LoginPageController implements PagesController {
	ControllerI cont;
	LoginPage loginPage;
	String signUpPageName;

	public LoginPageController(ControllerI cont, String signUpPageName) {
		this.cont = cont;
		loginPage = new LoginPage();
		this.signUpPageName = signUpPageName;

		addHandlers();	
	}
	
	private void addHandlers() {
		loginPage.getSignUp().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				cont.swapTo(signUpPageName);
			}
		});
		
		loginPage.getLoginbutton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				LoginServiceAsync loginService = GWT.create(LoginService.class);
				loginService.login(loginPage.getUsernameText(), loginPage.getPasswordText(), new AsyncCallback<ClientUser>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.toString());
					}

					@Override
					public void onSuccess(ClientUser clientUser) {
						cont.setClientUser(clientUser);
						cont.swapTo(clientUser.getAccsessLevel());
					}

				});
			}
		});
	}


	@Override
	public Widget getPageContent() {
		return loginPage.getMainPanel();
	}

	@Override
	public void clear() {
		loginPage.setPasswordText("");
		loginPage.setUsernameText("");
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEnd() {
		clear();
		
	}

}
