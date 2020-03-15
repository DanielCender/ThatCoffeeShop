package business;

import java.sql.SQLException;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import beans.User;
import database.DatabaseInterface;

@Stateless
@Local(RegisterInterface.class)
@Alternative
public class CustomerRegisterService implements RegisterInterface {
	
	@Inject
	DatabaseInterface db;
	
	@Override
	public void addUser(User user) {
		try {
			db.addUser(user);
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "Failed to add user...");
			e.printStackTrace();
		}
	}
}
