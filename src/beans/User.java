package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ManagedBean @SessionScoped 
public class User { //Will later be split into Customer and Admin classes that use User as a composition
	@NotNull() @Size(min=2, max=15)
	private String firstName, lastName, email, address, phone, username, password, DOB, creditCardInfo;
	int id;
	Cart cart;
	Orders orders;
	float accountBalance = 0;
	
	public class Cart {
		List<Product> cart = new ArrayList<Product>();
		
		public Cart() {}

		public List<Product> getCart() {
			return cart;
		}

		public void setCart(List<Product> cart) {
			this.cart = cart;
		}
	}

	public class Orders {

		List<OrderDetails> orders = new ArrayList<OrderDetails>();
		
		public Orders() {}

		public List<OrderDetails> getOrders() {
			return orders;
		}

		public void setOrders(List<OrderDetails> orders) {
			this.orders = orders;
		}
	}

	public User() {
		this.firstName = "";
		this.lastName = "";
		this.email = "";
		this.address = "";
		this.phone = "";
		this.username = "def";
		this.password = "pass";
		this.DOB = "";
		cart = new Cart();
		cart.getCart().add(new Product("Test",(float)5.00));
		orders = new Orders();
	}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Cart getCart() {
		return cart;
	}


	public void setCart(Cart cart) {
		this.cart = cart;
	}


	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	public String getCreditCardInfo() {
		return creditCardInfo;
	}

	public void setCreditCardInfo(String creditCardInfo) {
		this.creditCardInfo = creditCardInfo;
	}

	public float getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(float accountBalance) {
		this.accountBalance = accountBalance;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
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
	
	public void checkout(List<Product> cart) {
		//take the items from the cart and generate an OrderDetail and then add that OrderDetail to the User's orders list.
		System.out.println("You've checked out!");
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", address=" + address
				+ ", phone=" + phone + ", username=" + username + ", password=" + password + ", DOB=" + DOB + "]";
	}
}