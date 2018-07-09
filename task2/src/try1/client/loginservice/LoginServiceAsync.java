package try1.client.loginservice;

import com.google.gwt.user.client.rpc.AsyncCallback;

import try1.client.model.ClientUser;

public interface LoginServiceAsync {
	public void login(String login, String password, AsyncCallback<ClientUser> asyncCallback);
}
