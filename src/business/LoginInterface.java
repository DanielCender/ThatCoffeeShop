package business;

import javax.ejb.Local;

import beans.User;

@Local
public interface LoginInterface {
	public boolean testCredentials(User u);
}
