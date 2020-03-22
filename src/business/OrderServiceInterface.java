package business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;

import beans.Product;

@Local
public interface OrderServiceInterface {
	
	public void deleteProduct(String productName);
	public void addProduct(Product p);
	public List<Product> loadProducts() throws SQLException;
	public void updateProduct(int id, Product p);
	public Product findByID(int id);
	public ArrayList<Product> searchFor(String name);
}
