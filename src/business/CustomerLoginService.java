package business;

import java.sql.SQLException;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import beans.User;
import database.DatabaseInterface;


@Stateless
@Local(LoginInterface.class)
@Alternative
public class CustomerLoginService implements LoginInterface {

	@Inject 
	DatabaseInterface dbi;
	
	public CustomerLoginService() {}
	
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
	
	
}
