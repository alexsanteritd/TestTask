package try1.server.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import try1.client.model.CUser;
import try1.server.model.Logs;
import try1.server.model.LogsDao;
import try1.server.model.User;
import try1.server.model.UserDao;

@Service
public class DataServerServiceImp implements DataServerService {
	@Autowired
	UserDao userDao;
	@Autowired
	LogsDao logsDao;

	@Override
	public List<CUser> getData(int start, int end) {
		List<User> userList = (List<User>) userDao.findAllWhere(start, end, "role", "User");
		List<CUser> cuserList = new ArrayList<CUser>();
		for (User u : userList) {
			cuserList.add(new CUser(u.getBill().getBillScore(), u.getRegistationsDate(), u.getEmail()));
		}
		return cuserList;
	}

	@Override
	public Long getUsersCount() {
		return userDao.countAllWhere("role", "USER");
	}

	@Override
	public Float updateScore(float plusValue, String email,String adminEmail) {
		User user = userDao.findByParam("email", email);
		float billScore=user.getBill().getBillScore()+plusValue;
		user.getBill().setBillScore(billScore);
		userDao.update(user);
		logsDao.create(new Logs(email,adminEmail,plusValue));
		return user.getBill().getBillScore();
	}

}
