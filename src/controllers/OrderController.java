package controllers;

import java.sql.SQLException;
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
		
		//Put user object into POST
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		
		user.getCart().getCart().add(p);

		System.out.println(user.getCart().toString());
	}
	
	public OrderServiceInterface getService() {
		return services;
	}
	
	public void onAddProduct() {
		FacesContext context = FacesContext.getCurrentInstance();
		Product product = context.getApplication().evaluateExpressionGet(context, "#{product}", Product.class);
		
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("product", product);

		services.addProduct(product);
		
	}
	
	public void onUpdateProduct() {
		FacesContext context = FacesContext.getCurrentInstance();
		Product product = context.getApplication().evaluateExpressionGet(context, "#{product}", Product.class);
		
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("product", product);

		services.updateProduct(product.getId(), product);
	}
	
	public void onDelete(Product p) throws SQLException {
		services.deleteProduct(p.getName());
	}	
	
	public String onEdit(Product p) {
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("product", p);
		System.out.println(p.getId() + "  ID   ======");
		return "editproduct.xhtml";
	}
	
	public String onSubmitEdit() throws SQLException { 		//User to add new item to database
		FacesContext context = FacesContext.getCurrentInstance();
		Product p = context.getApplication().evaluateExpressionGet(context, "#{product}", Product.class);  // p holds the value of the submitted object
		System.out.println(p.getName() + " " + p.getId() + " is being held as an object.");
		services.updateProduct(p.getId(), p);
		return "order.xhtml";
	}
}
