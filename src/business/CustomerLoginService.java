package business;

import java.util.HashMap;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import beans.User;


@Stateless
@Local(LoginInterface.class)
@Alternative
public class CustomerLoginService implements LoginInterface {

	
	public CustomerLoginService() {
		AuthenticationService.credentials.put("testuser", "testpass");
	}
	
	@Override
	public boolean testCredentials(String u, String p) {
		System.out.println("testCredentials called in CustomerLoginService");
		
		//testing users input to hash table containing login information
		boolean auth = AuthenticationService.authenticate(u, p);
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
	public void test() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HashMap<String, String> getCredentials() {
		return AuthenticationService.credentials;
	}

	@Override
	public void addCredentials(User user) {
		AuthenticationService.credentials.put(user.getUsername(), user.getPassword());
	}

	
}
