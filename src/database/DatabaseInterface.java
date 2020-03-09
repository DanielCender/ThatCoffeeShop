package database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Local;

import beans.Product;
import beans.User;

@Local
public interface DatabaseInterface {
	
	/*
	 *database holds 
	 *	products 
	 * 	users
	 * 	credentials
	 */
	HashMap<String, String> credentials = new HashMap<>();
	List<User> users = new ArrayList<User>();
	List<Product> products = new ArrayList<Product>();
	
	public List<User> getUsers();
	public void addUsers(User u);
	public void addCredentials(User u);
	public boolean testCredentials(User u);
	public boolean checkAvailability(String username);
}
