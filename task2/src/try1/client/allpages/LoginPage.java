package try1.client.allpages;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class LoginPage implements Pages {
	private VerticalPanel mainpanel = new VerticalPanel();
	/** Decorator panel for the login form */
	private DecoratorPanel decPanel = new DecoratorPanel();
	/** Grid for login form elements */
	private FlexTable loginLayout = new FlexTable();
	private String headline = "<h1>Добро пожаловать</h1><br>Для начала работы войдите в систему под своим логином:";
	private String usernameLabel = "Логин: ";
	private String passwordLabel = "Пароль: ";
	private TextBox username = new TextBox();
	private PasswordTextBox password = new PasswordTextBox();
	private Button loginbutton = new Button("Войти");
	private Anchor signUp = new Anchor("Зарегистрироваться");

	public String getUsernameText() {
		return username.getText();
	}

	public String getPasswordText() {
		return password.getText();
	}

	public LoginPage() {

		int windowHeight = Window.getClientHeight();
		int windowWidth = Window.getClientWidth();
		loginLayout.setCellSpacing(6);
		FlexCellFormatter cellFormatter = loginLayout.getFlexCellFormatter();

		// Add a title to the form
		loginLayout.setHTML(0, 0, this.headline);
		cellFormatter.setColSpan(0, 0, 2);
		cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

		// Add username and password fields
		username.setWidth("150px");
		password.setWidth("150px");
		loginLayout.setHTML(1, 0, this.usernameLabel);
		loginLayout.setWidget(1, 1, username);
		loginLayout.setHTML(2, 0, passwordLabel);
		loginLayout.setWidget(2, 1, password);

		// Add the loginbutton to the form
		loginLayout.setWidget(3, 0, signUp);
		loginLayout.setWidget(3, 1, loginbutton);

		Grid advancedOptions = new Grid(3, 2);
		advancedOptions.setCellSpacing(6);

		// Add advanced options to form in a disclosure panel

		// Wrap the content in a DecoratorPanel
		decPanel.setWidget(loginLayout);

		mainpanel.setWidth(windowWidth / 2 + "px");
		mainpanel.setHeight(windowHeight * 0.6 + "px");
		mainpanel.add(loginLayout);
	}

	@Override
	public Widget getMainPanel() {
		return mainpanel;
	}

	public Anchor getSignUp() {
		return signUp;

	}

	public Button getLoginbutton() {
		return loginbutton;
	}


	public void setUsernameText(String text) {
		username.setText(text);
	}
	public void setPasswordText(String text) {
		password.setText(text);
	}


}
