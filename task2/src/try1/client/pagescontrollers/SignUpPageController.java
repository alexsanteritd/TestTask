package try1.client.pagescontrollers;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

import try1.client.allpages.SignUpPage;
import try1.client.controller.ControllerI;
import try1.client.model.ClientUser;
import try1.client.registerservice.SingUpService;
import try1.client.registerservice.SingUpServiceAsync;

public class SignUpPageController implements PagesController {
	SignUpPage signUpPage;
	String loginPageName;
	ControllerI cont;

	public SignUpPageController(ControllerI controller, String loginPageName) {
		super();
		this.signUpPage = new SignUpPage();
		this.loginPageName = loginPageName;
		this.cont = controller;
		addHandlers();
	}

	private void addHandlers() {
		signUpPage.getSignIn().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				cont.swapTo(loginPageName);
			}
		});

		signUpPage.getRegisterButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				SingUpServiceAsync regService = GWT.create(SingUpService.class);
				signUpPage.getRegisterButton().setEnabled(false);

				if (signUpPage.getPasswordText().equals(signUpPage.getPasswordRepeatText())
						&& signUpPage.getPasswordText().length() > 3) {

					regService.singUp(signUpPage.getUsernameText(), signUpPage.getPasswordText(),
							new AsyncCallback<ClientUser>() {

								@Override
								public void onFailure(Throwable caught) {
									Window.alert(caught.toString());
									signUpPage.getRegisterButton().setEnabled(true);
								}

								@Override
								public void onSuccess(ClientUser clientUser) {
									cont.setClientUser(clientUser);
									cont.swapTo(clientUser.getAccsessLevel());
									signUpPage.getRegisterButton().setEnabled(true);
								}

							});

				} else if (signUpPage.getPasswordText().length() <= 3) {
					signUpPage.getRegisterButton().setEnabled(true);
					Window.alert("Введите более длинный пароль!");
				} else {
					signUpPage.getRegisterButton().setEnabled(true);
					Window.alert("Пароли не совпадают!");
				}

			}

		});

	}

	@Override
	public Widget getPageContent() {
		return signUpPage.getMainPanel();
	}

	@Override
	public void clear() {
		signUpPage.setPasswordText("");
		signUpPage.setPasswordRepeatText("");
		signUpPage.setUsernameText("");
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
