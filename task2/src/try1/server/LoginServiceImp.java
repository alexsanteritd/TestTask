package try1.server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import try1.client.loginservice.LoginService;
import try1.client.model.ClientUser;
import try1.server.config.UserConfiguration;
import try1.server.model.User;
import try1.server.model.UserDao;
import try1.server.services.AutService;

public class LoginServiceImp extends RemoteServiceServlet implements LoginService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1933338621121835068L;	
	@Override
	public ClientUser login(String login, String password) {
		ApplicationContext context = new AnnotationConfigApplicationContext(UserConfiguration.class);
		AutService aut=context.getBean(AutService.class);
		return aut.loginUser(login, password); 
	}

	
	
}
