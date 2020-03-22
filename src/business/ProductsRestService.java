package business;

import java.sql.SQLException;
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
import beans.Product;

@RequestScoped
@Path("/products")
@Produces("application/json")
@Consumes("application/json")
public class ProductsRestService {
	
	@Inject
	OrderServiceInterface osi;
	
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getAllProducts() throws SQLException {
		System.out.println("test");
		return osi.loadProducts();		
	}
	
	@GET
	@Path("/getbyid/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Product getProduct(@PathParam("id") int id) {
		return osi.findByID(id);	
	}
	
	@GET
	@Path("/getbyname/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Product> getProductByName(@PathParam("name") String name) {
		return osi.searchFor(name);	
	}
	
}
