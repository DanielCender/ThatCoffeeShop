package business;

import java.util.HashMap;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import beans.User;

@Stateless
@Local(LoginInterface.class)
@Alternative
public class AdminLoginService implements LoginInterface {

	@Override
	public void test() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean testCredentials(String u, String p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public HashMap<String, String> getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCredentials(User user) {
		// TODO Auto-generated method stub
		
	}

}
