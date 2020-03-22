package business;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
	
	@GET
	@Path("/getbyid/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserByID(@PathParam("id") int id) {
		return ui.loadUser(id);	
	}
	
	@GET
	@Path("/getbyname/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<User> getUsersByName(@PathParam("name") String name) {
		return ui.searchFor(name);	
	}
}