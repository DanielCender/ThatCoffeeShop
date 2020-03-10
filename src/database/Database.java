package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import beans.Product;
import beans.User;
import beans.User.Cart;
import beans.User.Orders;

@Stateless
@Local(DatabaseInterface.class)
@Alternative
public class Database implements DatabaseInterface {
	int numberOfRowsImpacted = 0;
	//connection data
	String dbURL = "jdbc:mysql://localhost:3306/thatcoffeeshop";
	String user = "root";
	String password = "root";
	
	Connection c = null;
	ResultSet rs = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	
	//four CRUD operations
	@Override
	public int deleteUser(String username) throws SQLException {
		numberOfRowsImpacted = 0;
		
		c = DriverManager.getConnection(dbURL, user, password);
		System.out.println("Connection Successful!    " + dbURL + " User: " + user + " PW: " + password);

		//create a SQL statement
		pstmt = c.prepareStatement("delete from thatcoffeeshop.user where username = ?");
		pstmt.setString(1, username);
		
		//execute the statement
		numberOfRowsImpacted = pstmt.executeUpdate();
		
		//success msg
		System.out.println("Rows affected " + numberOfRowsImpacted);

		pstmt.close();
		c.close();
		
		return numberOfRowsImpacted;
	}
	
	@Override
	public int addUser(User u) throws SQLException {
		numberOfRowsImpacted = 0;
		
		Connection c = null;
		c = DriverManager.getConnection(dbURL, user, password);
		System.out.println("Connection Successful!    " + dbURL + " User: " + user + " PW: " + password);

		//create a SQL statement
		pstmt = c.prepareStatement("insert into jsf_mysql.order_info (firstname, lastname, email, address, phone, username, password, dob, ccinfo, balance, orders, cart) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		pstmt.setString(1, u.getFirstName());
		pstmt.setString(2, u.getLastName());
		pstmt.setString(3, u.getEmail());
		pstmt.setString(4, u.getAddress());
		pstmt.setString(5, u.getPhone());
		pstmt.setString(6, u.getUsername());
		pstmt.setString(7, u.getPassword());
		pstmt.setString(8, u.getDOB());
		pstmt.setString(9, u.getCreditCardInfo());
		pstmt.setFloat(10, u.getAccountBalance());
		pstmt.setObject(11, u.getOrders());
		pstmt.setObject(12, u.getCart());
		
		
		
		//execute the statement
		numberOfRowsImpacted = pstmt.executeUpdate();
		
		//success msg
		System.out.println("Rows inserted " + numberOfRowsImpacted);

		pstmt.close();
		c.close();
		
		return numberOfRowsImpacted;
	}

	@Override
	public ArrayList<User> loadUsers() throws SQLException {
		ArrayList<User> users = new ArrayList<>();
		
		//create link to SQL
		c = DriverManager.getConnection(dbURL, user, password);
		System.out.println("Connection Successful!    " + dbURL + " User: " + user + " PW: " + password);

		//create a SQL statement
		stmt = c.createStatement();
		
		//execute the statement into a result
		rs = stmt.executeQuery("select * from thatcoffeeshop.user");
		
		//process the rows in the result set into the list
		while(rs.next()) {
			User user = new User();
			user.setFirstName(rs.getString("firstname"));
			user.setLastName(rs.getString("lastname"));
			user.setEmail(rs.getString("email"));
			user.setAddress(rs.getString("address"));
			user.setPhone(rs.getString("phone"));
			user.setDOB(rs.getString("dob"));
			user.setCreditCardInfo(rs.getString("ccinfo"));
			user.setAccountBalance(rs.getFloat("balance"));
			user.setOrders((Orders) rs.getObject("orders"));
			user.setCart((Cart) rs.getObject("cart"));
			users.add(user);
		}
		
		rs.close();
		stmt.close();
		c.close();
		
		return users;
	}
	
	@Override
	public int updateUser(String username, User u) throws SQLException {
		numberOfRowsImpacted = 0;
		
		//connect to the SQL
		c = DriverManager.getConnection(dbURL, user, password);
		System.out.println("Connection Successful!    " + dbURL + " User: " + user + " PW: " + password);

		//create a SQL statement
		pstmt = c.prepareStatement("update thatcoffeeshop.user set firstname = ?, lastname = ?, email = ?, address = ?, phone = ?, password = ?, dob = ?, ccinfo = ?, balance = ?, orders = ?, cart  = ? where username = ?");
		pstmt.setString(1, u.getFirstName());
		pstmt.setString(2, u.getLastName());
		pstmt.setString(3, u.getEmail());
		pstmt.setString(4, u.getAddress());
		pstmt.setString(5, u.getPhone());
		pstmt.setString(6, u.getPassword());
		pstmt.setString(7, u.getDOB());
		pstmt.setString(8, u.getCreditCardInfo());
		pstmt.setFloat(9, u.getAccountBalance());
		pstmt.setObject(10, u.getOrders());
		pstmt.setObject(11, u.getCart());
		pstmt.setString(12, u.getUsername());

		
		//execute the statement
		numberOfRowsImpacted = pstmt.executeUpdate();
		
		//success msg
		System.out.println("Rows affected " + numberOfRowsImpacted);

		pstmt.close();
		c.close();
		
		return numberOfRowsImpacted;
	}
	
	
	
	HashMap<String, String> credentials = new HashMap<>();
	List<User> users = new ArrayList<User>();
	List<Product> products = new ArrayList<Product>();

	@Override
	public List<User> getUsers() {
		return users;
	}

	@Override
	public void addUsers(User u) {
		System.out.println("Adding user inside database");
		users.add(u);
	}

	@Override
	public void addCredentials(User u) {
		System.out.println("Adding credentials inside database");
		String un = u.getUsername();
		String pw = u.getPassword();
		credentials.put(un, pw);
	}

	@Override
	public boolean testCredentials(User u) {
		boolean v = false;
		String un = u.getUsername();
		String testValue = u.getPassword();
		String pw = "";
		
		System.out.println("Testing inside Database | U: " + un + " | P: " + testValue);
		if (credentials.containsKey(un)) {
			pw = credentials.get(un);
			if (pw.equals(testValue)) {
				v = true;
			} else {
				v = false;
			}
		} else {
			v = false;
		}
		return v;
	}

	@Override
	public boolean checkAvailability(String username) {
		System.out.println("Testing inside database for " + username + "| Size is " + users.size());
		boolean r = false;
		
		if (users.size() == 0) {
			return r;
		} else {
			System.out.println("Printing all usernames: " + users.toString());
			for (int x = 0; x <= users.size(); x++) {
				System.out.println("Testing pos " + x + ".. |U: " + users.get(x).getUsername() + " |I: " + username);
				if (users.get(x).getUsername().equals(username)) {
					r = true;
					break;
				}
			}
		}
		return r;
	}
}
