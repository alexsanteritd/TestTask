package try1.client.allpages;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import try1.client.model.ClientUser;

public class AdminPage implements Pages {
	private VerticalPanel mainpanel = new VerticalPanel();
	/** Decorator panel for the login form */
	private DecoratorPanel decPanel = new DecoratorPanel();
	/** Grid for login form elements */
	private FlexTable dataLayout = new FlexTable();
	private String headline = "<h1>Управление балансами пользователей:</h1>";
	private String emailLabel = "Логин: ";
	private TextBox emailBox = new TextBox();
	private Button searchButton = new Button("Поиск");
	private Anchor logAnchor = new Anchor("Журнал пополнений");
	private CellTable<ClientUser> usersData;

	public AdminPage() {
		int windowHeight = Window.getClientHeight();
		int windowWidth = Window.getClientWidth();
		usersData = new CellTable<ClientUser>();
		TextColumn<ClientUser> emailColumn = new TextColumn<ClientUser>() {
			@Override
			public String getValue(ClientUser clientUser) {
				return clientUser.getEmail();
			}

		};
		TextColumn<ClientUser> billColumn = new TextColumn<ClientUser>() {
			@Override
			public String getValue(ClientUser clientUser) {
				return NumberFormat.getFormat("#0.00").format(clientUser.getBillScore()) + " $";
			}

		};
		TextColumn<ClientUser> dateColumn = new TextColumn<ClientUser>() {
			@Override
			public String getValue(ClientUser clientUser) {
				return clientUser.getRegDate().toString();
			}

		};

		dataLayout.setCellSpacing(6);
		FlexCellFormatter cellFormatter = dataLayout.getFlexCellFormatter();
		dataLayout.setHTML(0, 0, this.headline);
		cellFormatter.setColSpan(0, 0, 2);
		cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		decPanel.setWidget(dataLayout);

		emailBox.setWidth("150px");
		dataLayout.setWidget(1, 1, logAnchor);
		dataLayout.setHTML(2, 0, emailLabel);
		dataLayout.setWidget(2, 1, emailBox);
		dataLayout.setWidget(2, 2, searchButton);

		mainpanel.setWidth(windowWidth / 2 + "px");
		mainpanel.setHeight(windowHeight * 0.6 + "px");
		mainpanel.add(dataLayout);
	}

	public String getEmailBoxText() {
		return emailBox.getText();
	}

	public void setEmailBoxText(String text) {
		emailBox.setText(text);
	}

	public Anchor getLogAnchor() {
		return logAnchor;
	}

	@Override
	public Widget getMainPanel() {
		return mainpanel;
	}

}
