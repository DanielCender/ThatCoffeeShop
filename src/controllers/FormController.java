package controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import beans.User;

@ManagedBean
@ViewScoped
public class FormController {

	public String onSubmit() {
		// get the user values from the login form
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		
		// print values received
		System.out.println("Submit button is working.");
		System.out.println("The first name is " + user.getFirstName());
		System.out.println("The last name is " + user.getLastName());
		
		// send values to response page
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		return "responsePage.xhtml";
	}
	public String onFlash(User user) {
		Flash fl = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		fl.put("firstname", firstName);
		fl.put("lastname", lastName);
		System.out.println(fl.toString());
		return "TestResponse.xhtml?faces-redirect=true";
	}
}
