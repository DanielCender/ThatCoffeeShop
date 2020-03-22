package database;
import java.sql.*;
import java.util.ArrayList;
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
	
	/*
	 * 			CRUD FOR USER TABLE
	 */
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
	public User loadUser(String username) throws SQLException {
		User loaded_user = new User();
		//connect to the SQL
		c = DriverManager.getConnection(dbURL, user, password);
		System.out.println("Connection Successful!    " + dbURL + " User: " + user + " PW: " + password);

		//create a SQL statement
		pstmt = c.prepareStatement("select * from thatcoffeeshop.user where username = ?");
		pstmt.setString(1, username);
		System.out.println("SQL Statement prepared..." + pstmt);
				
		//execute the statement into a result
		rs = pstmt.executeQuery();
		if(!rs.next()) {
			System.out.println("Username not found");
		} else {
			System.out.println("Credentials found!");
			loaded_user.setFirstName(rs.getString("firstname"));
			loaded_user.setLastName(rs.getString("lastname"));
			loaded_user.setEmail(rs.getString("email"));
			loaded_user.setAddress(rs.getString("address"));
			loaded_user.setPhone(rs.getString("phone"));
			loaded_user.setDOB(rs.getString("dob"));
			loaded_user.setCreditCardInfo(rs.getString("ccinfo"));
			loaded_user.setAccountBalance(rs.getFloat("balance"));
			//loaded_user.setOrders((Orders) rs.getObject("orders"));
			//loaded_user.setCart((Cart) rs.getObject("cart"));
		}
		
		pstmt.close();
		c.close();
		
		return loaded_user;
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
	
	public boolean checkCredentials(User u) throws SQLException {
		numberOfRowsImpacted = 0;
		String tv = u.getPassword();
		String un = u.getUsername();
		boolean result;
		
		//connect to the SQL
		c = DriverManager.getConnection(dbURL, user, password);
		System.out.println("Connection Successful!    " + dbURL + " User: " + user + " PW: " + password);

		//create a SQL statement
		pstmt = c.prepareStatement("select * from thatcoffeeshop.user where username = ? and password = ?");
		pstmt.setString(1, un);
		pstmt.setString(2, tv);
		System.out.println("SQL Statement prepared..." + pstmt);
		//stmt = c.createStatement();
				
		//execute the statement into a result
		rs = pstmt.executeQuery();
		if(!rs.next()) {
			System.out.println("Username and Password not found");
			result = false;
		} else {
			System.out.println("Credentials found!");
			result = true;
		}
		pstmt.close();
		c.close();
		return result;
	}
	
	/*
	 * 			CRUD FOR PRODUCT TABLE
	 */
	@Override
	public int deleteProduct(String productName) throws SQLException {
		numberOfRowsImpacted = 0;
		
		c = DriverManager.getConnection(dbURL, user, password);
		System.out.println("Connection Successful!    " + dbURL + " User: " + user + " PW: " + password);

		//create a SQL statement
		pstmt = c.prepareStatement("delete from thatcoffeeshop.products where name = ?");
		pstmt.setString(1, productName);
		
		//execute the statement
		numberOfRowsImpacted = pstmt.executeUpdate();
		
		//success msg
		System.out.println("Rows affected " + numberOfRowsImpacted);

		pstmt.close();
		c.close();
		
		return numberOfRowsImpacted;
	}

	@Override
	public int addProduct(Product p) throws SQLException {
		numberOfRowsImpacted = 0;
		
		Connection c = null;
		c = DriverManager.getConnection(dbURL, user, password);
		System.out.println("Connection Successful!    " + dbURL + " User: " + user + " PW: " + password);

		//create a SQL statement
		pstmt = c.prepareStatement("insert into thatcoffeeshop.products (id, name, price, product_desc) values (null, ?, ?, ?)");
		pstmt.setString(1, p.getName());
		pstmt.setFloat(2, p.getPrice());
		pstmt.setString(3, p.getDescription());
		
		//execute the statement
		numberOfRowsImpacted = pstmt.executeUpdate();
		
		//success msg
		System.out.println("Rows inserted " + numberOfRowsImpacted);

		pstmt.close();
		c.close();
		
		return numberOfRowsImpacted;
	}

	@Override
	public List<Product> loadProducts() throws SQLException {
		List<Product> products = new ArrayList<>();
		
		//create link to SQL
		c = DriverManager.getConnection(dbURL, user, password);
		System.out.println("Connection Successful!    " + dbURL + " User: " + user + " PW: " + password);

		//create a SQL statement
		stmt = c.createStatement();
		
		//execute the statement into a result
		rs = stmt.executeQuery("select * from thatcoffeeshop.products");
		
		//process the rows in the result set into the list
		while(rs.next()) {
			Product p = new Product();
			p.setId(rs.getInt("id"));
			p.setName(rs.getString("name"));
			p.setPrice(rs.getFloat("price"));
			p.setDescription(rs.getString("product_desc"));
			products.add(p);
		}
		
		rs.close();
		stmt.close();
		c.close();
		
		return products;
	}

	@Override
	public int updateProduct(int id, Product p) throws SQLException {
		numberOfRowsImpacted = 0;
		
		//connect to the SQL
		c = DriverManager.getConnection(dbURL, user, password);
		System.out.println("Connection Successful!    " + dbURL + " User: " + user + " PW: " + password);

		//create a SQL statement
		pstmt = c.prepareStatement("update thatcoffeeshop.products set name = ?, price = ?, product_desc = ? where id = ?");
		pstmt.setString(1, p.getName());
		pstmt.setFloat(2, p.getPrice());
		pstmt.setString(3, p.getDescription());
		pstmt.setInt(4, id);

		
		//execute the statement
		numberOfRowsImpacted = pstmt.executeUpdate();
		
		//success msg
		System.out.println("Rows affected " + numberOfRowsImpacted);

		pstmt.close();
		c.close();
		
		return numberOfRowsImpacted;
	}
}
