package try1.server.model;

import java.util.List;

public interface UserDao {
	public List<User> getAll();
	public User getByEmail(String email);
	public void addUser(User user);
}
