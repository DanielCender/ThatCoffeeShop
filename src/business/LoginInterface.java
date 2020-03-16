package business;

import java.sql.SQLException;

import javax.ejb.Local;

import beans.User;

@Local
public interface LoginInterface {
	public boolean testCredentials(User u);
	public User loadUser(String username) throws SQLException;
}
