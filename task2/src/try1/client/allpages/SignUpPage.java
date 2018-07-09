package try1.client.allpages;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

public class SignUpPage implements Pages {
	private VerticalPanel mainpanel = new VerticalPanel();
	/** The headline below the logo */

	/** Decorator panel for the login form */
	private DecoratorPanel decPanel = new DecoratorPanel();
	private FlexTable loginLayout = new FlexTable();
	private String headline = "<h1>Регистрация</h1><br>";
	private String usernameLabel = "Логин: ";
	private String passwordLabel = "Пароль: ";
	private String passwordRepeatLabel = "Повтор пароля: ";
	private TextBox username = new TextBox();
	private PasswordTextBox password = new PasswordTextBox();
	private PasswordTextBox passwordRepeat = new PasswordTextBox();
	private Button registerButton = new Button("Зарегистроваться");
	private Anchor signIn = new Anchor("Войти");

	public Button getRegisterButton() {
		return registerButton;
	}

	public Anchor getSignIn() {
		return signIn;
	}

	@Override
	public VerticalPanel getMainPanel() {
		return mainpanel;
	}
	public String getUsernameText() {
		return username.getText();
	}

	public String getPasswordText() {
		return password.getText();
	}
	public String getPasswordRepeatText() {
		return passwordRepeat.getText();
	}
	
	public void setUsernameText(String text) {
		username.setText(text);
	}

	public void setPasswordText(String text) {
		password.setText(text);
	}
	public void setPasswordRepeatText(String text) {
		passwordRepeat.setText(text);
	}
	
	
	public SignUpPage() {
		int windowHeight = Window.getClientHeight();
		int windowWidth = Window.getClientWidth();
		loginLayout.setCellSpacing(6);
		FlexCellFormatter cellFormatter = loginLayout.getFlexCellFormatter();
		loginLayout.setHTML(0, 0, this.headline);
		cellFormatter.setColSpan(0, 0, 2);
		cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		username.setWidth("150px");
		password.setWidth("150px");
		passwordRepeat.setWidth("150px");
		loginLayout.setHTML(1, 0, usernameLabel);
		loginLayout.setWidget(1, 1, username);
		loginLayout.setHTML(2, 0, passwordLabel);
		loginLayout.setWidget(2, 1, password);
		loginLayout.setHTML(3, 0, passwordRepeatLabel);
		loginLayout.setWidget(3, 1, passwordRepeat);
		loginLayout.setWidget(4, 0, signIn);
		loginLayout.setWidget(4, 1, registerButton);
		decPanel.setWidget(loginLayout);

		mainpanel.setWidth(windowWidth / 2 + "px");
		mainpanel.setHeight(windowHeight * 0.6 + "px");
		mainpanel.add(loginLayout);
	}


}
