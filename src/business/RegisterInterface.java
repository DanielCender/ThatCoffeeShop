package business;

import javax.ejb.Local;

import beans.User;

@Local
public interface RegisterInterface {

	public void addUser(User user);
}
