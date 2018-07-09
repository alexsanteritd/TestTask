package try1.server;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import try1.client.loginservice.LoginService;
import try1.client.model.ClientUser;
import try1.server.config.UserConfiguration;
import try1.server.model.User;
import try1.server.model.UserDao;

public class LoginServiceImp extends RemoteServiceServlet implements LoginService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1933338621121835068L;
	static ApplicationContext context = new AnnotationConfigApplicationContext(UserConfiguration.class);
	static UserDao userDao=context.getBean(UserDao.class);
	@Override
	public ClientUser login(String login, String password) {
		String acsessLevel;
		ClientUser clientUser;
		float billScore=0f;
		User u=userDao.getByEmail(login);
		if(u!=null) {
			if(BCrypt.checkpw(password,u.getPass())) {
				acsessLevel=u.getRole();
				if(u.getBill()!=null) {
					billScore = u.getBill().getBillScore();
				}
			}
			else {
				acsessLevel="WRONG PASS";
			}
		}
		else {
		acsessLevel="LOGIN DONT EXIST";
		}
		clientUser = new ClientUser(acsessLevel, billScore);
		return clientUser; 
	}

}
