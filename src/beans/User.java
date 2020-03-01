package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ManagedBean @SessionScoped 
public class User {
	@NotNull() @Size(min=5, max=15)
	private String firstName, lastName, email, address, phone, username, password;
	List<Order> orders = new ArrayList<Order>();
	List<Product> cart = new ArrayList<Product>();

	public List<Product> getCart() {
		return cart;
	}

	public void setCart(List<Product> cart) {
		this.cart = cart;
	}

	public User() {
		this.firstName = "";
		this.lastName = "";
		this.email = "";
		this.address = "";
		this.phone = "";
		this.username = "";
		this.password = "";
		orders.add(new Order("000","This is product 1",(float)1.00,1));
		orders.add(new Order("000","This is product 2",(float)1.00,1));
		orders.add(new Order("000","This is product 3",(float)1.00,1));
		orders.add(new Order("000","This is product 4",(float)1.00,1));
		cart.add(new Product("Test",(float)5.00));
	}
	
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
}
