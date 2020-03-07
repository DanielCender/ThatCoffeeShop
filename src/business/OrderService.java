package business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import beans.Product;

@Stateless @Local(OrderServiceInterface.class)
public class OrderService implements OrderServiceInterface {


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
		return products;
	}

	@Override
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public void addProduct(String name, float price) {
		products.add(new Product(name, (float)price));
	}

}
