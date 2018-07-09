package try1.client.registerservice;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import try1.client.model.ClientUser;

@RemoteServiceRelativePath("SingUpService")
public interface SingUpService extends RemoteService{
	ClientUser singUp(String login, String password);
}
