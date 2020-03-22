package business;

import java.util.List;
import javax.ejb.Local;
import beans.Product;

@Local
public interface OrderServiceInterface {
	public void test();	
	public void deleteProduct(String productName);
	public void addProduct(Product p);
	public List<Product> loadProducts();
	public void updateProduct(int id, Product p);
}
