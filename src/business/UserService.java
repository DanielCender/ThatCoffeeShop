package business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import beans.User;
import database.DatabaseInterface;


@Stateless
@Local(UserInterface.class)
@Alternative
public class UserService implements UserInterface {

	@Inject 
	DatabaseInterface dbi;

	List<User> users = new ArrayList<User>();
	
	public UserService() {}
	
	@Override
	public boolean testCredentials(User u) {
		boolean auth = false;
		System.out.println("testCredentials called in CustomerLoginService");
		
		//testing users input to user database
		try {
			auth = dbi.checkCredentials(u);
		} catch (SQLException e) {
			System.out.println(e.getMessage() + " Failed to check credentials...");
			e.printStackTrace();
		}
		System.out.println("boolean auth reads = " + auth);
		if (auth == true) {
			System.out.println("User authenticated");
			return true;
		} else {
			System.out.println("User Credentials WRONG");
			return false;
		}
	}

	@Override
	public User loadUser(String username) throws SQLException {
		return dbi.loadUser(username);
	}

	@Override
	public List<User> loadUsers() {
		try {
			users = dbi.loadUsers();
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "Failed to load users...");
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public void addUser(User user) {
		try {
			dbi.addUser(user);
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "Failed to add user...");
			e.printStackTrace();
		}
	}
	
	
}
