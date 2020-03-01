package controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import beans.User;

@ManagedBean @ViewScoped 
public class RegisterController {
	public String onSubmit() {
		//get the user value from the input form.
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		
		//Put user object into POST
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		
		//Adds credentials to Hash Map in AuthenticationController
		//String username = user.
		AuthenticationController.addUser(user.getUsername(), user.getPassword());
		//show next page
		return "index.xhtml";
	}
}
