package try1.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import try1.client.model.ClientUser;
import try1.client.registerservice.SingUpService;
import try1.server.config.UserConfiguration;
import try1.server.services.AutService;

public class SingUpServiceImp extends RemoteServiceServlet implements SingUpService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8869341934848610684L;

	@Override
	public ClientUser singUp(String login, String password) {
		ApplicationContext context = new AnnotationConfigApplicationContext(UserConfiguration.class);
		AutService aut=context.getBean(AutService.class);
		return aut.createUser(login, password);
	}

}
