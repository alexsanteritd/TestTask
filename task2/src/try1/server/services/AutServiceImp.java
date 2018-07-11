package try1.server.services;

import java.util.List;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import try1.client.model.ClientUser;
import try1.server.model.Bill;
import try1.server.model.User;
import try1.server.model.UserDao;

@Service
public class AutServiceImp implements AutService {

	@Autowired
	UserDao userDao;

	@Override
	public ClientUser createUser(String login, String password) {
		String responce = "";
		User user = null;
		ClientUser cu;
		if (!EmailValidator.getInstance().isValid(login)) {
			responce = "INVALID_EMAIL";
		} else if (userDao.findByParam("email", login) == null) {
			user = new User(login, BCrypt.hashpw(password, BCrypt.gensalt()), "USER");
			userDao.update(user);
			Bill bill = new Bill(0f, user.getId());
			user.setBill(bill);
			userDao.update(user);
			responce = "USER";
		} else {
			responce = "ПОЛЬЗОВАТЕЛЬ С ТАКИМ ЛОГИНОМ СУЩЕСТВУЕТ";
		}
		return user == null ? (new ClientUser(responce, 0)) : (new ClientUser(responce, user.getBill().getBillScore()));
	}

	@Override
	public ClientUser loginUser(String login, String password) {
		String acsessLevel;
		ClientUser clientUser;
		float billScore = 0f;
		User u = userDao.findByParam("email", login);
		if (u != null) {
			if (BCrypt.checkpw(password, u.getPass())) {
				acsessLevel = u.getRole();
				if (u.getBill() != null) {
					billScore = u.getBill().getBillScore();
				}
			} else {
				acsessLevel = "WRONG PASS";
			}
		} else {
			acsessLevel = "LOGIN DONT EXIST";
		}
		clientUser = new ClientUser(acsessLevel, billScore);
		return clientUser;
	}

}
