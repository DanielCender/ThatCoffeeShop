package business;

import java.util.List;
import javax.ejb.Local;

import beans.Product;

@Local
public interface OrderServiceInterface {
	public void test();
	public List<Product> getProducts();
	public void setProducts(List<Product> products);
	public void addProduct(String name, float price);
}
