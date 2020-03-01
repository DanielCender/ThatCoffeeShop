package controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import beans.User;

@ManagedBean @ViewScoped 
public class LoginController {
	
	public String onSubmit() {
		//get the user value from the input form.
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		
		//Put user object into POST
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
<<<<<<< HEAD

		//Checks username and password.
		System.out.println("Testing Credentials... username = " + user.getUsername() + " password = " + user.getPassword());
		boolean auth = AuthenticationController.authenticate(user.getUsername(), user.getPassword());
		System.out.println("boolean auth reads = " + auth);
		if (auth == true) {
			System.out.println("User authenticated");
		} else {
			System.out.println("User Credentials WRONG");
		}
=======
>>>>>>> parent of ac5a38f... Order buttons and Cart Buttons
		
		//show next page
		return "index.xhtml";
	}
}
