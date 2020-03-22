package controllers;


import java.sql.SQLException;
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
			try {
				user = login.loadUser(user.getUsername());
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
	
/*	public String login() {
		System.out.println("Login called from LoginController");
		//get the user value from the input form.
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		
		//Put user object into POST
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		
		//True of False for user inputed values for user/pass
		boolean auth = credentials.testCredentials(user.getUsername(), user.getPassword());
		
		//Depending on 'auth' user is directed to 'order.xhtml' or 'register.xhtml'
		System.out.println("auth in login = " + auth);
		if (auth == true) {
			return "order.xhtml";
		} else {
			return "register.xhtml";
		}
	}  */
}
