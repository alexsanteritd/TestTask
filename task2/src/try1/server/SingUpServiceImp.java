package try1.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.apache.commons.validator.routines.EmailValidator;

import try1.client.model.ClientUser;
import try1.client.registerservice.SingUpService;
import try1.server.config.UserConfiguration;
import try1.server.model.Bill;
import try1.server.model.User;
import try1.server.model.UserDao;

public class SingUpServiceImp extends RemoteServiceServlet implements SingUpService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8869341934848610684L;
	static ApplicationContext context = new AnnotationConfigApplicationContext(UserConfiguration.class);
	static UserDao userDao = context.getBean(UserDao.class);

	@Override
	public ClientUser singUp(String login, String password) {
		String responce = "";
		if (!EmailValidator.getInstance().isValid(login)) {
			responce="INVALID_EMAIL";
		} else if (userDao.getByEmail(login) == null) {
			User user=new User(login,BCrypt.hashpw(password, BCrypt.gensalt()),"USER",new Bill(login));
			userDao.addUser(user);
			responce =  "USER";
		} else {
			responce = "ПОЛЬЗОВАТЕЛЬ С ТАКИМ ЛОГИНОМ СУЩЕСТВУЕТ";
		}
		return new ClientUser(responce,0f);
	}

}
