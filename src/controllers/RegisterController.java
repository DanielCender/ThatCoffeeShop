package controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import beans.User;
import business.UserInterface;

@ManagedBean @ViewScoped 
public class RegisterController {
	
	@Inject
	UserInterface services;
	
	public String onSubmit() {
		//get the user value from the input form.
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		
		//Put user object into POST
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		services.addUser(user);
		
		//show next page
		return "index.xhtml";
	}
}
