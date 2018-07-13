package try1.client.allpages;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.TextBox;

import try1.client.model.CUser;

public class MyDialog extends DialogBox {
	private FlexTable dataLayout = new FlexTable();
	private TextBox scoreBox = new TextBox();
	Button ok = new Button("Пополнить");

	public Long getScoreBoxValue() {
		Long value = 0L;
		String text = scoreBox.getText();
		RegExp patter1 = RegExp.compile("^([-+]?)(\\d+)$");
		MatchResult mr=patter1.exec(text);
		if (mr!=null) {
			value = Long.parseLong(mr.getGroup(2))*100;
			if(mr.getGroup(1).equals("-")) {
				value=-value;
			}
		} else {
			RegExp pattern2 = RegExp.compile("^([-+]?)(\\d+)[.,](\\d{1,2})$");
			mr=pattern2.exec(text);
			if (mr!=null) {
				Window.alert(mr.getGroup(2)+ "    "+ mr.getGroup(3));
				value=100*Long.valueOf(mr.getGroup(2));
				if(mr.getGroup(3).length()==1) {
					value+=10*Long.valueOf(mr.getGroup(3));
				}
				else {
					value+=Long.valueOf(mr.getGroup(3));
				}
				if(mr.getGroup(1).equals("-")) {
					value=-value;
				}
			} else {
				Window.alert("Некорректный ввод");
			}
		}
		return value;
	}

	public TextBox getScoreBox() {
		return scoreBox;
	}
	public String getScoreBoxText() {
		return scoreBox.getText();
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
		dataLayout.setWidget(0, 1, scoreBox);
		dataLayout.setWidget(1, 0, no);
		dataLayout.setWidget(1, 1, ok);

		setWidget(dataLayout);
	}

	public void setCUser(CUser object) {
		this.cuser = object;
	}

	public CUser getCUser() {
		return cuser;
	}
}
