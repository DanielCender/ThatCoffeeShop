package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean @ViewScoped 
public class ProductPage {
	List<Product> products = new ArrayList<Product>();

	public ProductPage() {
		products.add(new Product("Coffee", (float)5.00));
		products.add(new Product("Tea", (float)2.00));
		products.add(new Product("Espresso", (float)3.00));
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	
}
