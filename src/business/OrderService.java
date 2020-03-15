package business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import beans.Product;
import database.DatabaseInterface;

@Stateless @Local(OrderServiceInterface.class) @Alternative
public class OrderService implements OrderServiceInterface {
	
	@Inject 
	DatabaseInterface dbi;
	
	List<Product> products = new ArrayList<Product>();
	
	public OrderService() {
		//load product information to be listed on the product pages
		products.add(new Product("Coffee", (float)5.00));
		products.add(new Product("Tea", (float)2.00));
		products.add(new Product("Espresso", (float)3.00));
	}
	
	@Override
	public void test() {
		System.out.println("Order Serivce Initiated.");

	}

	@Override
	public List<Product> getProducts() {
		try {
			products = dbi.loadProducts();
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "Failed to load products...");
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public void addProduct(Product p) {
		try {
			dbi.addProduct(p);
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "Failed to add product...");
			e.printStackTrace();
		}
	}

	@Override
	public void updateProduct(String productName, Product p) {
		try {
			dbi.updateProduct(productName, p);
		} catch (SQLException e) {
			System.out.println(e.getMessage() + "Failed to update product...");
			e.printStackTrace();
		}
	}

}
