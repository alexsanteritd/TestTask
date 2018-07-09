package try1.client.registerservice;

import com.google.gwt.user.client.rpc.AsyncCallback;

import try1.client.model.ClientUser;

public interface SingUpServiceAsync{
	public void singUp(String login, String password, AsyncCallback<ClientUser> asyncCallback);
}
