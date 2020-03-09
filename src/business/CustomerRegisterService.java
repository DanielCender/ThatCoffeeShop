package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import beans.User;
import database.DatabaseInterface;

@Stateless
@Local(RegisterInterface.class)
@Alternative
public class CustomerRegisterService implements RegisterInterface {
	
	HashMap<String, String> credentials = new HashMap<>();
	List<User> users = new ArrayList<User>();
	
	@Inject
	DatabaseInterface db;
	
	@Override
	public void test() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addUser(User user) {
		
		// Checks if username is registered
		boolean available = db.checkAvailability(user.getUsername());
		
		System.out.println("Test for Username results... " + available);
		
		// Adds a user
		db.addUsers(user);
		
		//User.users.add(user);
		System.out.println("User created | " + user.toString());
		
		// Adds users credentials
		db.addCredentials(user);
		
		//AuthenticationService.addUser(user.getUsername(), user.getPassword());
		System.out.println("Credentials added.");
	}

	@Override
	public boolean checkAvailable(String username) {
		// TODO Auto-generated method stub
		return false;
	}
}
