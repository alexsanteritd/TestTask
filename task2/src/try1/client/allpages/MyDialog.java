package try1.client.allpages;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.VerticalPanel;

import try1.client.model.CUser;

import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.TextBox;

public class MyDialog extends DialogBox {
	private FlexTable dataLayout = new FlexTable();
	private TextBox scoreBox = new TextBox();
	Button ok = new Button("Пополнить");
	public TextBox getScoreBox() {
		return scoreBox;
	}


	public Button getOk() {
		return ok;
	}


	public Button getNo() {
		return no;
	}


	Button no = new Button("Отменить");
	private CUser cuser;
	
	public void setEmailLabel(String emailLabel) {
		dataLayout.setHTML(0, 0, emailLabel);
	}
	
	
	public MyDialog() {
		// Set the dialog box's caption.
		setText("Пополнение счета");
		setPopupPosition(50, 50);
		// Enable animation.
		scoreBox.setWidth("100px");
		// Enable glass background.
		setGlassEnabled(true);

		// DialogBox is a SimplePanel, so you have to set its widget property to
		// whatever you want its contents to be.
		
		
		
		dataLayout.setCellSpacing(6);
		FlexCellFormatter cellFormatter = dataLayout.getFlexCellFormatter();
		dataLayout.setWidget(0, 1, scoreBox);
		dataLayout.setWidget(1,0,no);
		dataLayout.setWidget(1,1,ok);
		
		

		
		setWidget(dataLayout);
	}


	public void setCUser(CUser object) {
		this.cuser=object;
	}
	public CUser getCUser() {
		return cuser;
	}
}
