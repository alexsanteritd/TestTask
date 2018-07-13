package try1.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import try1.client.loginservice.LoginService;
import try1.client.model.ClientUser;
import try1.server.dao.ClientUserFactory;
import try1.server.services.AuthorizationServise;
import try1.server.singletoncontext.SingletonSpringContext;

public class LoginServiceImp extends RemoteServiceServlet implements LoginService {
	/**
	 * 
	 */
	AuthorizationServise authorizationServise;
	private static final long serialVersionUID = -1933338621121835068L;

	@Override
	public ClientUser login(String login, String password) {
		return ClientUserFactory.to(getAutService().loginUser(login, password));
	}

	private AuthorizationServise getAutService() {
		if (authorizationServise == null) {
			authorizationServise=SingletonSpringContext.getBean(AuthorizationServise.class);
		}
		return authorizationServise;
	}

}
