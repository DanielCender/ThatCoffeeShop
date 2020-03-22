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

	@Override
	public List<Product> loadProducts() {
		try {
			return dbi.loadProducts();
		} catch (SQLException e) {
			System.out.println(e.getMessage() + " | Failed to load products...");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void addProduct(Product p) {
		try {
			dbi.addProduct(p);
		} catch (SQLException e) {
			System.out.println(e.getMessage() + " | Failed to add product...");
			e.printStackTrace();
		}
	}

	@Override
	public void updateProduct(int id, Product p) {
		try {
			dbi.updateProduct(id, p);
		} catch (SQLException e) {
			System.out.println(e.getMessage() + " | Failed to update product...");
			e.printStackTrace();
		}
	}

	@Override
	public void deleteProduct(String productName) {
		try {
			dbi.deleteProduct(productName);
		} catch (SQLException e) {
			System.out.println(e.getMessage() + " | Failed to delete product...");
			e.printStackTrace();
		}
	}

	@Override
	public Product findByID(int id) {
		return dbi.findByID(id);
	}

	@Override
	public ArrayList<Product> searchFor(String name) {
		return dbi.searchForProduct(name);
	}
	
}
