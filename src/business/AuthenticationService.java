package business;

import java.util.HashMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class AuthenticationService {

	static HashMap<String, String> credentials = new HashMap<>();
	
	public AuthenticationService() {
		credentials.put("testuser", "testpass");
		credentials.put("testuser1", "testpass1");
	}
	
	public static boolean authenticate(String username, String password) {
		String pw = "";
		
		System.out.println("Testing inside AuthenticationService | U: " + username + " | P: " + password);
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
	
	public static void addUser(String username, String password) {
		credentials.put(username, password);
		System.out.println("Inside AuthenticationService, added user");
	}
	
	public void showUsers() {
		System.out.print(credentials);
	}
}
