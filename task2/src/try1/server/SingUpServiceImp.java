package try1.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import try1.client.model.ClientUser;
import try1.client.registerservice.SingUpService;
import try1.server.services.AutService;
import try1.server.singletoncontext.SingletonSpringContext;

public class SingUpServiceImp extends RemoteServiceServlet implements SingUpService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8869341934848610684L;
	private AutService autService;

	@Override
	public ClientUser singUp(String login, String password) {
		return ClientUserFactory.to(getAutService().createUser(login, password));
	}
	private AutService getAutService() {
		if (autService == null) {
			autService=SingletonSpringContext.getInstance().getContext().getBean(AutService.class);
		}
		return autService;
	}

}
