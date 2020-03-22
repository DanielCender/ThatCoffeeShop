package business;

import java.util.ArrayList;
import java.util.List;

import beans.User;

public interface UserInterface {
	public boolean testCredentials(User u);
	public User loadUser(int id);
	public List<User> loadUsers();
	public void addUser(User user);
	public ArrayList<User> searchFor(String name);
}
