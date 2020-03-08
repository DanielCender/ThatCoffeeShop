package business;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import beans.User;

@Stateless
@Local(RegisterInterface.class)
@Alternative
public class AdminRegisterService implements RegisterInterface {

	@Override
	public void test() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkAvailable(String username) {
		// TODO Auto-generated method stub
		return false;
	}

}
