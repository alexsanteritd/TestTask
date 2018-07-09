package try1.client.pagescontrollers;

import com.google.gwt.user.client.ui.Widget;

public interface PagesController {
	public Widget getPageContent();
	public void onStart();
	public void onEnd();
	public void clear();
}
