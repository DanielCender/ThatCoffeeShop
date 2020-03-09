package database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import beans.Product;
import beans.User;

@Stateless
@Local(DatabaseInterface.class)
@Alternative
public class Database implements DatabaseInterface {

	HashMap<String, String> credentials = new HashMap<>();
	List<User> users = new ArrayList<User>();
	List<Product> products = new ArrayList<Product>();

	@Override
	public List<User> getUsers() {
		return users;
	}

	@Override
	public void addUsers(User u) {
		System.out.println("Adding user inside database");
		users.add(u);
	}

	@Override
	public void addCredentials(User u) {
		System.out.println("Adding credentials inside database");
		String un = u.getUsername();
		String pw = u.getPassword();
		credentials.put(un, pw);
	}

	@Override
	public boolean testCredentials(User u) {
		boolean v = false;
		String un = u.getUsername();
		String testValue = u.getPassword();
		String pw = "";
		
		System.out.println("Testing inside Database | U: " + un + " | P: " + testValue);
		if (credentials.containsKey(un)) {
			pw = credentials.get(un);
			if (pw.equals(testValue)) {
				v = true;
			} else {
				v = false;
			}
		} else {
			v = false;
		}
		return v;
	}

	@Override
	public boolean checkAvailability(String username) {
		System.out.println("Testing inside database for " + username + "| Size is " + users.size());
		boolean r = false;
		
		if (users.size() == 0) {
			return r;
		} else {
			System.out.println("Printing all usernames: " + users.toString());
			for (int x = 0; x <= users.size(); x++) {
				System.out.println("Testing pos " + x + ".. |U: " + users.get(x).getUsername() + " |I: " + username);
				if (users.get(x).getUsername().equals(username)) {
					r = true;
					break;
				}
			}
		}
		return r;
	}
	
	
}
