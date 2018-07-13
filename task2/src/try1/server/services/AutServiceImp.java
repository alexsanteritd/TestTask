package try1.server.services;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import try1.server.dao.AccountHistoryDao;
import try1.server.dao.UserDao;
import try1.server.model.AccountHistory;
import try1.server.model.User;

@Service
public class AutServiceImp implements AutService {

	@Autowired
	UserDao userDao;

	@Autowired
	AccountHistoryDao acountHistoryDao;
	
	@Override
	@Transactional
	public User createUser(String login, String password) {
		String responce = "";
		User user = null;
		if (!EmailValidator.getInstance().isValid(login)) {
			responce = "INVALID_EMAIL";
		} else if (userDao.findByEmail(login) == null) {
			user = new User(login, BCrypt.hashpw(password, BCrypt.gensalt()), "USER");
			userDao.create(user);
			AccountHistory ah=new AccountHistory();
			ah.setAdmiId(1);
			ah.setUserId(user.getId());
			acountHistoryDao.create(ah);
		} else {
			responce = "ПОЛЬЗОВАТЕЛЬ С ТАКИМ ЛОГИНОМ СУЩЕСТВУЕТ";
		}
		return responce.equals("") ? user : new User("", "", responce);
	}

	@Override
	public User loginUser(String login, String password) {
		String acsessLevel = "";
		User user = userDao.findByEmail(login);
		if (user != null) {
			if (!BCrypt.checkpw(password, user.getPass())) {
				acsessLevel = "WRONG PASS";
			}
		} else {
			acsessLevel = "LOGIN DONT EXIST";
		}
		return acsessLevel.equals("") ? user : new User("", "", acsessLevel);
	}

}
