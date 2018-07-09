package try1.client.loginservice;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import try1.client.model.ClientUser;

@RemoteServiceRelativePath("loginService")
public interface LoginService extends RemoteService {
	ClientUser login(String login, String password);
}
