package try1.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import try1.client.model.ClientUser;
import try1.client.registerservice.SingUpService;
import try1.server.dao.ClientUserFactory;
import try1.server.services.AuthorizationServise;
import try1.server.singletoncontext.SingletonSpringContext;

public class SingUpServiceImp extends RemoteServiceServlet implements SingUpService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8869341934848610684L;
	private AuthorizationServise authorizationServise;

	@Override
	public ClientUser singUp(String login, String password) {
		return ClientUserFactory.to(getAuthorizationServise().createUser(login, password));
	}
	private AuthorizationServise getAuthorizationServise() {
		if (authorizationServise == null) {
			authorizationServise=SingletonSpringContext.getBean(AuthorizationServise.class);
		}
		return authorizationServise;
	}

}
