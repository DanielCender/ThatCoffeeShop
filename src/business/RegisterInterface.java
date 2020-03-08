package business;

import javax.ejb.Local;

import beans.User;

@Local
public interface RegisterInterface {

	public void test();
	public void addUser(User user);
	public boolean checkAvailable(String username);
}
