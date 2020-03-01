package controllers;

import java.util.HashMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class AuthenticationController {

	static HashMap<String, String> credentials = new HashMap<>();
	
	public AuthenticationController() {
		credentials.put("testuser", "testpass");
		credentials.put("testuser1", "testpass1");
	}
	
	static boolean authenticate(String username, String password) {
		String pw = "";
		
		System.out.println("Testing inside AuthenticationController | U: " + username + " | P: " + password);
		if (credentials.containsKey(username)) {
			pw = credentials.get(username);
			if (password.equals(pw)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	static void addUser(String username, String password) {
		credentials.put(username, password);
	}
	
	public void showUsers() {
		System.out.print(credentials);
	}
}
