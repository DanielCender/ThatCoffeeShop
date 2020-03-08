package business;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import beans.User;

@Stateless
@Local(RegisterInterface.class)
@Alternative
public class CustomerRegisterService implements RegisterInterface {
	
	
	@Override
	public void test() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addUser(User user) {
		
		// Checks if username is registered
		boolean available = checkAvailable(user.getUsername());
		
		System.out.println("Test for Username results... " + available);
		
		// Adds a user
		User.users.add(user);
		System.out.println("User created | " + user.toString());
		
		// Adds users credentials
		AuthenticationService.addUser(user.getUsername(), user.getPassword());
		System.out.println("Credentials added.");
	}

	@Override
	public boolean checkAvailable(String username) {
		System.out.println("Testing inside checkAvailable() for " + username + "| Size is " + User.users.size());
		boolean r = false;
		
		if (User.users.size() == 0) {
			return r;
		} else {
			System.out.println("Printing all usernames: " + User.users.toString());
			for (int x = 0; x <= User.users.size(); x++) {
				System.out.println("Testing pos " + x + ".. |U: " + User.users.get(x).getUsername() + " |I: " + username);
				if (User.users.get(x).getUsername().equals(username)) {
					r = true;
					break;
				}
			}
		}
		return r;
	}
}
