package business;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

@Stateless
@Local(RegisterInterface.class)
@Alternative
public class CustomerRegisterService implements RegisterInterface {

	@Override
	public void test() {
		// TODO Auto-generated method stub
		
	}

}
