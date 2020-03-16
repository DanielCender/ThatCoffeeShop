package business;

import java.util.ArrayList;
import javax.ejb.Local;
import beans.Product;

@Local
public interface OrderServiceInterface {
	public void test();	
	public void deleteProduct(String productName);
	public void addProduct(Product p);
	public ArrayList<Product> loadProducts();
	public void updateProduct(int id, Product p);
}
