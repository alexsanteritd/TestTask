package try1.client.allpages;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class UserPage implements Pages {
	private VerticalPanel mainpanel = new VerticalPanel();
	/** The headline below the logo */

	/** Decorator panel for the login form */
	private DecoratorPanel decPanel = new DecoratorPanel();
	private FlexTable loginLayout = new FlexTable();
	private String headline = "<h1>Ваш текущий баланс</h1><br>";
	private Anchor logOut= new Anchor("Выйти из системы");

	@Override
	public Widget getMainPanel() {
		return mainpanel;
	}

	public UserPage() {
		int windowHeight = Window.getClientHeight();
		int windowWidth = Window.getClientWidth();
		loginLayout.setCellSpacing(6);
		FlexCellFormatter cellFormatter = loginLayout.getFlexCellFormatter();
		loginLayout.setHTML(0, 0, this.headline);
		cellFormatter.setColSpan(0, 0, 2);
		cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		decPanel.setWidget(loginLayout);

		loginLayout.setHTML(1, 1, "<h2> $<h2>");
		loginLayout.setWidget(2, 1, logOut);

		mainpanel.setWidth(windowWidth / 2 + "px");
		mainpanel.setHeight(windowHeight * 0.6 + "px");
		mainpanel.add(loginLayout);
	}

	public void setAccount(long account) {
		String decimal="";
		int fraction=(int)(account%100);
		if (fraction<=0) {
			decimal="00";
		}else if(fraction<10) {
			decimal="0"+fraction;
		}
		else {
			decimal=String.valueOf(fraction);
		}
		loginLayout.setHTML(1, 1, "<h2> " + account/100+"."+ decimal + " $<h2>");
	}
	public Anchor getLogOut(){
		return logOut;
	}
}
