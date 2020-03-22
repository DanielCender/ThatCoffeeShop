package controllers;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import beans.User;
import business.UserInterface;

@ManagedBean @ViewScoped 
public class LoginController {
	
	
	@Inject
	UserInterface login;
	
	public String onSubmit() {
		//get the user value from the input form.
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);

		//Checks username and password.
		System.out.println("Testing Credentials... username = " + user.getUsername() + " password = " + user.getPassword());
		boolean auth = login.testCredentials(user);
		System.out.println("boolean auth reads = " + auth);
		if (auth == true) {
			user = login.loadUser(user.getId());
			System.out.println("User authenticated. " + user.getUsername() + " " + user.getFirstName());
			//Put user object into POST
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
			//show next page
			return "order.xhtml";
		} else {
			System.out.println("User Credentials WRONG");
			//show next page
			return "index.xhtml";
		}
	}
}
