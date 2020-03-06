package business;

import java.util.HashMap;

import javax.ejb.Local;

import beans.User;

@Local
public interface LoginInterface {

	public void test();
	public boolean testCredentials(String u, String p);
	public HashMap<String, String> getCredentials();
	public void addCredentials(User user);
}
