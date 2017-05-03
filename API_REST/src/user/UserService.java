package user;




import java.text.SimpleDateFormat;


import javax.annotation.security.*;
import javax.interceptor.Interceptors;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.util.Base64;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.oracle.jrockit.jfr.RequestDelegate;

import command.Command;
import command.LineCommand;
import generic.Functions;
import product.Product;
import product.ProductDatabase;



@Path("/users-service")
public class UserService
{
	@RolesAllowed({"ADMIN", "USER"})
	@GET
	@Path("/user")
	public Response getUserById(@Context HttpServletRequest request)
	{
		User user = (User) request.getSession().getAttribute("USER");

		ResponseBuilder rb;
		if(user == null)
		{
			rb = Response.serverError().status(404);
		}
		else if(request.getAttribute("INTERCEPTOR-ID-USER")==null || user.getId() != (int)request.getAttribute("INTERCEPTOR-ID-USER"))
		{
			rb = Response.serverError().status(403);
		}
		else
		{
			rb = Response.ok(user.getProperties());
		}		
		return rb.build();
	}

	@PermitAll
	@GET
	@Path("/connect/{username}/{password}")
	public Response connectUser(@PathParam("username") String username, @PathParam("password") String password, @Context HttpServletRequest  request)
	{
		User user = UserDatabase.findByCriteria(username, password);
		ResponseBuilder rb;
		if(user == null)
		{
			rb = Response.serverError().status(403);
		}
		else
		{
			request.getSession().setAttribute("USER", user);
			rb = Response.ok(user.getProperties());
		}

		return rb.build();
	}
	
	
	@PermitAll
	@GET
	@Path("/disconnect")
	public Response disconnectUser(@Context HttpServletRequest  request)
	{
		User user = (User) request.getSession().getAttribute("USER");
		ResponseBuilder rb = null;
		if(user == null)
		{
			rb = Response.serverError().status(404);
		}
		else 
		{
			if(request.getAttribute("INTERCEPTOR-ID-USER")==null || user.getId() != (int)request.getAttribute("INTERCEPTOR-ID-USER"))
			{
				rb = Response.serverError().status(403);
			}
			else
			{
				request.getSession().removeAttribute("USER");
				request.getSession().invalidate();
			}
		}
		return rb.build();
	}

	@RolesAllowed({"ADMIN", "USER"})
	@GET
	@Path("/user/commands/{id_command}")
	public Response getCommandById(@PathParam("id_command") int id_command, @Context HttpServletRequest request)
	{
		User user = (User) request.getSession().getAttribute("USER");
		ResponseBuilder rb;
		if(user == null)
		{
			rb = Response.serverError().status(404);
		}
		else 
		{
			if(request.getAttribute("INTERCEPTOR-ID-USER")==null || user.getId() != (int)request.getAttribute("INTERCEPTOR-ID-USER"))
			{
				rb = Response.serverError().status(403);
			}
			else
			{
				Command command = UserDatabase.findCommand(id_command);
				if(command == null)
				{
					rb = Response.serverError().status(404);
				}
				else {
					rb = Response.ok(command.getProperties());
				}	
			}
		}
		return rb.build();
	}


	@PermitAll
	@GET
	@Path("/user/panier")
	public Response getPanier(@Context HttpServletRequest request)
	{
		ResponseBuilder rb;
		Command command = Functions.getPanier(request);
		if(command == null)
		{
			rb = Response.serverError().status(404);
		}
		else {
			rb = Response.ok(command.getProperties());
		}	
		return rb.build();
	}

	@RolesAllowed({"ADMIN", "USER"})
	@GET
	@Path("user/commands")
	public Response getCommands(@Context HttpServletRequest request) throws JSONException
	{
		User user = (User) request.getSession().getAttribute("USER");
		ResponseBuilder rb;
		if(user == null)
		{
			rb = Response.serverError().status(404);
		}
		else 
		{
			if(request.getAttribute("INTERCEPTOR-ID-USER")==null || user.getId() != (int)request.getAttribute("INTERCEPTOR-ID-USER"))
			{
				rb = Response.serverError().status(403);
			}
			else
			{
				ObjectMapper mapper = new ObjectMapper();
				mapper.enable(SerializationFeature.INDENT_OUTPUT);
				String json = "[]";
				try {
					json = mapper.writeValueAsString(user.getCommands());
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}

				rb = Response.ok(json);
			}	
		}
		return rb.build();
	}

	@RolesAllowed({"ADMIN", "USER"})
	@PUT
	@Path("user/panier/pay")
	public Response validateCommand(@Context HttpServletRequest request) throws JSONException
	{
		User user = (User) request.getSession().getAttribute("USER");

		ResponseBuilder rb;
		if(user == null)
		{
			rb = Response.serverError().status(404);
		}
		else 
		{
			if(request.getAttribute("INTERCEPTOR-ID-USER")==null || user.getId() != (int)request.getAttribute("INTERCEPTOR-ID-USER"))
			{
				rb = Response.serverError().status(403);
			}
			else
			{
				Command command = Functions.getPanier(request);
				command.setId_user(user.getId());
				if(command == null  || command.getLinecommands().isEmpty())
				{
					rb = Response.serverError().status(403);
				}
				else
				{
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					command.setDate_command(sdf.toString());
					UserDatabase.saveCommand(command);	
					
					Functions.newPanier(request);
					
					String json = command.getProperties();
					rb = Response.ok(json);
				}
			}	
		}
		return rb.build();
	}


	@RolesAllowed({"ADMIN", "USER"})
	@DELETE
	@Path("user/panier/{id_product}")
	public Response deleteProductToCommand(	@PathParam("id_product") int id_product,
			@Context HttpServletRequest request) throws JSONException
	{
		User user = (User) request.getSession().getAttribute("USER");
		ResponseBuilder rb;
		if(user == null)
		{
			rb = Response.serverError().status(404);
		}
		else 
		{
			if(request.getAttribute("INTERCEPTOR-ID-USER")==null || user.getId() != (int)request.getAttribute("INTERCEPTOR-ID-USER"))
			{
				rb = Response.serverError().status(403);
			}
			else
			{
				Command command = Functions.getPanier(request);
				Product product = ProductDatabase.findProductByID(id_product);
				if(command == null || product == null){
					rb = Response.serverError().status(403);
				}
				else
				{	
					LineCommand  linecmd = command.findLineCommand(product);
					if(linecmd == null)
					{
						rb = Response.serverError().status(403);
					}
					else
					{
						LineCommand line = command.removeLineCommand(linecmd);
						String json = line.getProperties();
						rb = Response.ok(json);
					}

				}
			}	
		}
		return rb.build();
	}

	@PermitAll
	@POST
	@Path("user/panier/add/{id_product}/{qt}")
	public Response addProductToCommand(@PathParam("id_product") int id_product, @PathParam("qt") int qt,
			@Context HttpServletRequest request) throws JSONException
	{

		ResponseBuilder rb;
		Command command = Functions.getPanier(request);
		if(command == null)
		{
			rb = Response.serverError().status(403);
		}
		else
		{	
			Product  product = ProductDatabase.findProductByID(id_product);
			if(product == null)
			{
				rb = Response.serverError().status(403);
			}
			else
			{
				LineCommand line = command.addLineCommand(product, qt);
				String json = line.getProperties();
				rb = Response.ok(json);
			}
		}

		return rb.build();
	}



	@PermitAll
	@PUT
	@Path("user/panier/{id_product}/{qt}")
	public Response updateProductCommand( @PathParam("id_product") int id_product, @PathParam("qt") int qt,
			@Context HttpServletRequest request) throws JSONException
	{


		ResponseBuilder rb;

		Command command = Functions.getPanier(request);
		Product product = ProductDatabase.findProductByID(id_product);
		if(product == null || command == null)
		{
			rb = Response.serverError().status(403);
		}
		else
		{	
			LineCommand line = command.findLineCommand(product);
			if(line == null || line.getId_command() !=command.getId())
			{
				rb = Response.serverError().status(403);
			}
			else
			{
				line.setQuantity(qt);
				String json = line.getProperties();
				rb = Response.ok(json);
			}			
		}
		return rb.build();
	}

	@RolesAllowed({"ADMIN", "USER"})
	@GET
	@Path("/user/reviews")
	public Response getReviews(@Context HttpServletRequest request)
	{
		User user = (User) request.getSession().getAttribute("USER");
		ResponseBuilder rb;
		if(user == null)
		{
			rb = Response.serverError().status(404);
		}
		else 
		{
			if(request.getAttribute("INTERCEPTOR-ID-USER")==null || user.getId() != (int)request.getAttribute("INTERCEPTOR-ID-USER"))
			{
				rb = Response.serverError().status(403);
			}
			else
			{
				ObjectMapper mapper = new ObjectMapper();
				mapper.enable(SerializationFeature.INDENT_OUTPUT);
				String json = "[]";
				try {
					json = mapper.writeValueAsString(user.getReviews());
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}

				rb = Response.ok(json);
			}	
		}
		return rb.build();
	}


	@PermitAll
	@POST
	@Path("/user/{username}/{password}/{firstname}/{surname}/{mail}")
	public Response createNewUser(@PathParam("username") String username, @PathParam("password") String password,
			@PathParam("firstname") String firstname, @PathParam("surname") String surname, 
			@PathParam("mail") String mail
			)
	{
		User user = UserDatabase.findByCriteria(username);
		if(user == null){
			user = new User();
			user.setFirstname(firstname);
			user.setMail(mail);
			user.setSurname(surname);
			user.setUsername(username);
			user.setPassword(password);
			UserDatabase.saveUser(user);

			return Response.ok(user.getProperties())
					.status(200)
					.build();
		}
		else
		{
			return Response.ok().status(500).build();
		}
	}

	@RolesAllowed({"ADMIN", "USER"})
	@PUT
	@Path("/user/{username}/{password}/{firstname}/{surname}/{mail}")
	public Response updateUser(@PathParam("username") String username, @PathParam("password") String password,
			@PathParam("firstname") String firstname, @PathParam("surname") String surname, 
			@PathParam("mail") String mail, @Context HttpServletRequest request
			)
	{
		
		User user = (User) request.getSession().getAttribute("USER");	

		ResponseBuilder rb;
		if(user == null)
		{
			rb = Response.serverError().status(404);
		}
		else 
		{
			if(	request.getAttribute("INTERCEPTOR-ID-USER")==null || user.getId() != (int)request.getAttribute("INTERCEPTOR-ID-USER"))
			{
				rb = Response.serverError().status(403);
			}
			else
			{
				String json ="";
				user.setUsername(username);
				user.setPassword(password);
				user.setFirstname(firstname);
				user.setSurname(surname);
				user.setMail(mail);

				UserDatabase.saveUser(user);
				json = user.getProperties();

				rb = Response.ok(json);
			}	
		}
		return rb.build();
	}


	@RolesAllowed({"ADMIN"})
	@PUT
	@Path("/user/role/{id}/{role}")
	public Response updateRoleUser(@PathParam("id") int id, @PathParam("role") String role, @Context HttpRequest request)
	{
		User user = UserDatabase.findUserByID(id);
		ResponseBuilder rb;
		if(user == null)
		{
			rb = Response.serverError().status(404);
		}
		else 
		{
			if(role.toUpperCase().equals("USER"))
			{
				user.setRole("USER");
			}
			else if(role.toUpperCase().equals("ADMIN"))
			{
				user.setRole("AMDIN");
			}

			String json ="";
			UserDatabase.saveUser(user);
			json = user.getProperties();

			rb = Response.ok(json);

		}
		return rb.build();
	}
}