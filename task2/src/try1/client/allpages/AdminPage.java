package try1.client.allpages;

import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.text.shared.SafeHtmlRenderer;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
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
import com.google.gwt.view.client.NoSelectionModel;

import try1.client.model.CUser;
import try1.client.serverdata.DataProvider;

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
	private CellTable<CUser> dataTable;
	DataProvider dataProvider = new DataProvider();
	Column<CUser, String> email;

	public Button getSearchButton() {
		return searchButton;
	}

	public Column<CUser, String> getEmail() {
		return email;
	}

	public DataProvider getDataProvider() {
		return dataProvider;
	}

	public CellTable<CUser> getDataTable() {
		return dataTable;
	}

	public AdminPage() {

		int windowHeight = Window.getClientHeight();
		int windowWidth = Window.getClientWidth();
		dataTable = new CellTable<CUser>();
		dataTable.setPageSize(10);

		email = new Column<CUser, String>(new ClickableTextCell(new SafeHtmlRenderer<String>() {

			@Override
			public SafeHtml render(String object) {
				SafeHtmlBuilder builder = new SafeHtmlBuilder();
				builder.appendHtmlConstant("<font color=\"blue\"><u>").appendEscaped(object)
						.appendHtmlConstant("</u></font>");
				return builder.toSafeHtml();
			}

			@Override
			public void render(String object, SafeHtmlBuilder builder) {
				// TODO Auto-generated method stub

			}

		})) {

			@Override
			public String getValue(CUser object) {
				// TODO Auto-generated method stub
				return object.getEmail();
			}

		};

		TextColumn<CUser> Account = new TextColumn<CUser>() {
			@Override
			public String getValue(CUser object) {
				long account = object.getAccount();
				String decimal = "";
				String sign = "";
				if (account < 0) {
					account=-account;
					sign="-";
				}
				int fraction = (int) (account % 100);
				if (fraction == 0) {
					decimal = "00";
				} else if (fraction < 10) {
					decimal = "0" + fraction;
				} else {
					decimal = ""+fraction;
				}
				return sign+account / 100 + "." + decimal + " $";
			}
		};

		TextColumn<CUser> regData = new TextColumn<CUser>() {
			@Override
			public String getValue(CUser object) {
				return object.getRegDate().toString();
			}
		};

		dataTable.setWidth("500px");
		dataTable.setHeight("500px");
		dataTable.addColumn(email, "email");
		dataTable.addColumn(Account, "Счет");
		dataTable.addColumn(regData, "Дата регистрации");
		dataTable.setVisible(true);

		dataProvider.addDataDisplay(dataTable);
		;

		final NoSelectionModel<CUser> selectionModel = new NoSelectionModel<CUser>();
		dataTable.setSelectionModel(selectionModel);

		dataLayout.setCellSpacing(6);
		FlexCellFormatter cellFormatter = dataLayout.getFlexCellFormatter();
		dataLayout.setHTML(0, 0, this.headline);
		cellFormatter.setColSpan(0, 0, 3);
		cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		decPanel.setWidget(dataLayout);

		emailBox.setWidth("150px");
		dataLayout.setWidget(1, 1, logAnchor);

		dataLayout.setHTML(2, 0, emailLabel);
		dataLayout.setWidget(2, 1, emailBox);
		dataLayout.setWidget(2, 2, searchButton);

		dataLayout.setWidget(3, 0, dataTable.asWidget());
		cellFormatter.setColSpan(3, 0, 3);
		mainpanel.setWidth(windowWidth / 2 + "px");
		mainpanel.setHeight(windowHeight * 0.6 + "px");
		mainpanel.add(dataLayout);

		SimplePager pager;
		SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);

		pager = new SimplePager(TextLocation.CENTER, pagerResources, true, 0, true);
		pager.setDisplay(dataTable);

		pager.setPageSize(10);
		dataLayout.setWidget(4, 0, pager);
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
