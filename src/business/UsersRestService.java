package business;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import beans.User;

@RequestScoped
@Path("/users")
@Produces("application/json")
@Consumes("application/json")
public class UsersRestService {
	
	@Inject
	UserInterface ui;
	
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllUsers() {
		System.out.println("test");
		return ui.loadUsers();		
	}
}