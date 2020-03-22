package business;

import java.sql.SQLException;
import java.util.List;

import beans.User;

public interface UserInterface {
	public boolean testCredentials(User u);
	public User loadUser(String username) throws SQLException;
	public List<User> loadUsers();
	public void addUser(User user);
}
