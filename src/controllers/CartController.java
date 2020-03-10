package controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import beans.User;

@ManagedBean @ViewScoped 
public class CartController {
	
	public void onEmpty() {
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		
		System.out.println("Cart BEFORE Clear - " + user.getCart().toString());

		user.getCart().getCart().clear();
		
		System.out.println("Cart AFTER Clear - " + user.getCart().toString());
	}
	
	public void onCheckout() {
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		
		System.out.println("Cart BEFORE checkout - " + user.getCart().toString());
		
		user.checkout(user.getCart().getCart());
		user.getCart().getCart().clear();
		
		System.out.println("Cart AFTER checkout - " + user.getCart().toString());
	}
}
