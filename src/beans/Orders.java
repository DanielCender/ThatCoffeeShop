package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean @ViewScoped 
public class Orders {
	List<Order> orders = new ArrayList<Order>();
	
	public Orders() {
		orders.add(new Order("000","This is product 1",(float)1.00,1));
		orders.add(new Order("000","This is product 2",(float)1.00,1));
		orders.add(new Order("000","This is product 3",(float)1.00,1));
		orders.add(new Order("000","This is product 4",(float)1.00,1));
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
}
