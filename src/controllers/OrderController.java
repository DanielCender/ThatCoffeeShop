package controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import beans.Product;
import beans.User;
import business.OrderServiceInterface;

@ManagedBean @ViewScoped 
public class OrderController {
	
	@Inject
	OrderServiceInterface services;
	
	public void onAddToCart(Product p) {
		//get the user value from the input form.
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		
		System.out.println(user.getCart().toString());
		
		//Put user object into POST
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		
		user.getCart().add(p);
	}
	
	public OrderServiceInterface getService() {
		return services;
	}
	
	public void onAddProduct() {
		FacesContext context = FacesContext.getCurrentInstance();
		Product product = context.getApplication().evaluateExpressionGet(context, "#{product}", Product.class);
		
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("product", product);

		services.getProducts().add(product);
		
		
	}
}
