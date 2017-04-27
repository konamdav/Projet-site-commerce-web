package user;


import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.security.*;
import javax.interceptor.Interceptors;
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
import product.Product;
import product.ProductDatabase;



@Path("/users-service")
public class UserService
{
	@RolesAllowed({"ADMIN", "USER"})
	@GET
	@Path("/users/{id}")
	public Response getUserById(@PathParam("id") int id, @Context HttpRequest request)
	{
		final HttpHeaders headers = request.getHttpHeaders();
		final String AUTHORIZATION_PROPERTY = "Authorization";
		final String AUTHENTICATION_SCHEME = "Basic";
		final List<String> authorization = headers.getRequestHeader(AUTHORIZATION_PROPERTY);
		final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");

		String usernameAndPassword="";
		try {
			usernameAndPassword = new String(Base64.decode(encodedUserPassword));
		} catch (IOException e) {
			e.printStackTrace();
		}

		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
		final String username = tokenizer.nextToken();
		final String password = tokenizer.nextToken();


		User user = UserDatabase.findUserByID(id);

		ResponseBuilder rb;
		if(user == null)
		{
			rb = Response.serverError().status(404);
		}
		else if(!user.getUsername().equals(username)||!user.getPassword().equals(password))
		{
			rb = Response.serverError().status(403);
		}
		else
		{
			System.out.println(user.getProperties());
			rb = Response.ok(user.getProperties());
		}		
		return rb.build();
	}

	@PermitAll
	@GET
	@Path("/users/connect/{username}/{password}")
	public Response connectUser(@PathParam("username") String username, @PathParam("password") String password, @Context HttpRequest request)
	{
		System.out.println("[REST] "+request.getUri().getPath());
		User user = UserDatabase.findByCriteria(username, password);
		ResponseBuilder rb;
		if(user == null)
		{
			rb = Response.serverError().status(403);
		}
		else
		{
			System.out.println(user.getProperties());
			rb = Response.ok(user.getProperties());
		}		
		return rb.build();
	}

	@RolesAllowed({"ADMIN", "USER"})
	@GET
	@Path("/users/{id_user}/commands/{id_command}")
	public Response getCommandById(@PathParam("id_user") int id_user, @PathParam("id_command") int id_command, @Context HttpRequest request)
	{
		final HttpHeaders headers = request.getHttpHeaders();
		final String AUTHORIZATION_PROPERTY = "Authorization";
		final String AUTHENTICATION_SCHEME = "Basic";
		final List<String> authorization = headers.getRequestHeader(AUTHORIZATION_PROPERTY);
		final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");

		String usernameAndPassword="";
		try {
			usernameAndPassword = new String(Base64.decode(encodedUserPassword));
		} catch (IOException e) {
			e.printStackTrace();
		}

		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
		final String username = tokenizer.nextToken();
		final String password = tokenizer.nextToken();

		User user = UserDatabase.findUserByID(id_user);

		ResponseBuilder rb;
		if(user == null)
		{
			rb = Response.serverError().status(404);
		}
		else 
		{
			if(!user.getUsername().equals(username)||!user.getPassword().equals(password))
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

	@RolesAllowed({"ADMIN", "USER"})
	@GET
	@Path("users/{id_user}/commands")
	public Response getCommands(@PathParam("id_user") int id_user, @Context HttpRequest request) throws JSONException
	{
		final HttpHeaders headers = request.getHttpHeaders();
		final String AUTHORIZATION_PROPERTY = "Authorization";
		final String AUTHENTICATION_SCHEME = "Basic";
		final List<String> authorization = headers.getRequestHeader(AUTHORIZATION_PROPERTY);
		final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");

		String usernameAndPassword="";
		try {
			usernameAndPassword = new String(Base64.decode(encodedUserPassword));
		} catch (IOException e) {
			e.printStackTrace();
		}

		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
		final String username = tokenizer.nextToken();
		final String password = tokenizer.nextToken();

		User user = UserDatabase.findUserByID(id_user);

		ResponseBuilder rb;
		if(user == null)
		{
			rb = Response.serverError().status(404);
		}
		else 
		{
			if(!user.getUsername().equals(username)||!user.getPassword().equals(password))
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
	@POST
	@Path("users/{id_user}/commands")
	public Response newCommand(@PathParam("id_user") int id_user, @Context HttpRequest request) throws JSONException
	{
		final HttpHeaders headers = request.getHttpHeaders();
		final String AUTHORIZATION_PROPERTY = "Authorization";
		final String AUTHENTICATION_SCHEME = "Basic";
		final List<String> authorization = headers.getRequestHeader(AUTHORIZATION_PROPERTY);
		final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");

		String usernameAndPassword="";
		try {
			usernameAndPassword = new String(Base64.decode(encodedUserPassword));
		} catch (IOException e) {
			e.printStackTrace();
		}

		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
		final String username = tokenizer.nextToken();
		final String password = tokenizer.nextToken();

		User user = UserDatabase.findUserByID(id_user);

		ResponseBuilder rb;
		if(user == null)
		{
			rb = Response.serverError().status(404);
		}
		else 
		{
			if(!user.getUsername().equals(username)||!user.getPassword().equals(password))
			{
				rb = Response.serverError().status(403);
			}
			else
			{
				Command command = UserDatabase.findPanier(id_user);
				if(command != null)
				{
					System.out.println("EXISTE DEJA "+command.getId());
					rb = Response.serverError().status(403);
				}
				else
				{
					command = new Command();
					//command.setDate_command(new Timestamp(Calendar.getInstance().getTimeInMillis()).toString());
					command.setId_user(user.getId());
					command.setStatus("NON_PAYE");
					UserDatabase.saveCommand(command);	

					String json = command.getProperties();
					rb = Response.ok(json);
				}
			}	
		}
		return rb.build();
	}

	
	@RolesAllowed({"ADMIN", "USER"})
	@PUT
	@Path("users/{id_user}/commands/{id_command}/pay")
	public Response validateCommand(@PathParam("id_user") int id_user, @PathParam("id_command") int id_command,
			@Context HttpRequest request) throws JSONException
	{
		final HttpHeaders headers = request.getHttpHeaders();
		final String AUTHORIZATION_PROPERTY = "Authorization";
		final String AUTHENTICATION_SCHEME = "Basic";
		final List<String> authorization = headers.getRequestHeader(AUTHORIZATION_PROPERTY);
		final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");

		String usernameAndPassword="";
		try {
			usernameAndPassword = new String(Base64.decode(encodedUserPassword));
		} catch (IOException e) {
			e.printStackTrace();
		}

		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
		final String username = tokenizer.nextToken();
		final String password = tokenizer.nextToken();

		User user = UserDatabase.findUserByID(id_user);

		ResponseBuilder rb;
		if(user == null)
		{
			rb = Response.serverError().status(404);
		}
		else 
		{
			if(!user.getUsername().equals(username)||!user.getPassword().equals(password))
			{
				rb = Response.serverError().status(403);
			}
			else
			{
				Command command = UserDatabase.findCommand(id_command);
				if(command == null || command.getId_user()!=id_user || command.getLinecommands().isEmpty())
				{
					rb = Response.serverError().status(403);
				}
				else
				{
					command.setStatus("PAYE");
					UserDatabase.saveCommand(command);	

					String json = command.getProperties();
					rb = Response.ok(json);
				}
			}	
		}
		return rb.build();
	}

	
	@RolesAllowed({"ADMIN", "USER"})
	@DELETE
	@Path("users/{id_user}/commands/{id_command}/{id_line}")
	public Response deleteProductToCommand(@PathParam("id_user") int id_user, @PathParam("id_command") int id_command,
			@PathParam("id_line") int id_linecommand,
			@Context HttpRequest request) throws JSONException
	{
		final HttpHeaders headers = request.getHttpHeaders();
		final String AUTHORIZATION_PROPERTY = "Authorization";
		final String AUTHENTICATION_SCHEME = "Basic";
		final List<String> authorization = headers.getRequestHeader(AUTHORIZATION_PROPERTY);
		final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");

		String usernameAndPassword="";
		try {
			usernameAndPassword = new String(Base64.decode(encodedUserPassword));
		} catch (IOException e) {
			e.printStackTrace();
		}

		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
		final String username = tokenizer.nextToken();
		final String password = tokenizer.nextToken();

		User user = UserDatabase.findUserByID(id_user);

		ResponseBuilder rb;
		if(user == null)
		{
			rb = Response.serverError().status(404);
		}
		else 
		{
			if(!user.getUsername().equals(username)||!user.getPassword().equals(password))
			{
				rb = Response.serverError().status(403);
			}
			else
			{
				Command command = UserDatabase.findCommand(id_command);
				if(command == null || command.getId_user()!=id_user || !command.getStatus().equals("NON_PAYE"))
				{
					System.out.println("erreur command");
					rb = Response.serverError().status(403);
				}
				else
				{	
					LineCommand  linecmd = UserDatabase.findLineCommand(id_linecommand);
					if(linecmd == null)
					{
						rb = Response.serverError().status(403);
					}
					else
					{
						LineCommand line = command.removeLineCommand(linecmd);
						UserDatabase.saveCommand(command);
						String json = line.getProperties();
						rb = Response.ok(json);
					}
					
				}
			}	
		}
		return rb.build();
	}
	
	@RolesAllowed({"ADMIN", "USER"})
	@POST
	@Path("users/{id_user}/commands/{id_command}/add/{id_product}/{qt}")
	public Response addProductToCommand(@PathParam("id_user") int id_user, @PathParam("id_command") int id_command,
			@PathParam("id_product") int id_product, @PathParam("qt") int qt,
			@Context HttpRequest request) throws JSONException
	{
		final HttpHeaders headers = request.getHttpHeaders();
		final String AUTHORIZATION_PROPERTY = "Authorization";
		final String AUTHENTICATION_SCHEME = "Basic";
		final List<String> authorization = headers.getRequestHeader(AUTHORIZATION_PROPERTY);
		final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");

		String usernameAndPassword="";
		try {
			usernameAndPassword = new String(Base64.decode(encodedUserPassword));
		} catch (IOException e) {
			e.printStackTrace();
		}

		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
		final String username = tokenizer.nextToken();
		final String password = tokenizer.nextToken();

		User user = UserDatabase.findUserByID(id_user);

		ResponseBuilder rb;
		if(user == null)
		{
			rb = Response.serverError().status(404);
		}
		else 
		{
			if(!user.getUsername().equals(username)||!user.getPassword().equals(password))
			{
				rb = Response.serverError().status(403);
			}
			else
			{
				Command command = UserDatabase.findCommand(id_command);
				if(command == null || command.getId_user()!=id_user || !command.getStatus().equals("NON_PAYE"))
				{
					System.out.println("erreur command");
					rb = Response.serverError().status(403);
				}
				else
				{	
					Product  product = ProductDatabase.findProductByID(id_product);
					if(product == null)
					{
						System.out.println("erreur product");
						rb = Response.serverError().status(403);
					}
					else
					{
						LineCommand line = command.addLineCommand(product, qt);
						UserDatabase.saveCommand(command);
						String json = line.getProperties();
						rb = Response.ok(json);
					}
					
				}
			}	
		}
		return rb.build();
	}

	

	@RolesAllowed({"ADMIN", "USER"})
	@PUT
	@Path("users/{id_user}/commands/{id_command}/{id_line}/{qt}")
	public Response updateProductCommand(@PathParam("id_user") int id_user, @PathParam("id_command") int id_command,
			@PathParam("id_line") int id_linecommand, @PathParam("qt") int qt,
			@Context HttpRequest request) throws JSONException
	{
		final HttpHeaders headers = request.getHttpHeaders();
		final String AUTHORIZATION_PROPERTY = "Authorization";
		final String AUTHENTICATION_SCHEME = "Basic";
		final List<String> authorization = headers.getRequestHeader(AUTHORIZATION_PROPERTY);
		final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");

		String usernameAndPassword="";
		try {
			usernameAndPassword = new String(Base64.decode(encodedUserPassword));
		} catch (IOException e) {
			e.printStackTrace();
		}

		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
		final String username = tokenizer.nextToken();
		final String password = tokenizer.nextToken();

		User user = UserDatabase.findUserByID(id_user);

		ResponseBuilder rb;
		if(user == null)
		{
			rb = Response.serverError().status(404);
		}
		else 
		{
			if(!user.getUsername().equals(username)||!user.getPassword().equals(password))
			{
				rb = Response.serverError().status(403);
			}
			else
			{
				Command command = UserDatabase.findCommand(id_command);
				if(command == null || command.getId_user()!=id_user || !command.getStatus().equals("NON_PAYE"))
				{
					System.out.println("erreur command");
					rb = Response.serverError().status(403);
				}
				else
				{	
					LineCommand line = UserDatabase.findLineCommand(id_linecommand);
					
					if(line == null || line.getId_command() !=command.getId())
					{
						rb = Response.serverError().status(403);
					}
					else
					{
						line.setQuantity(qt);
						UserDatabase.saveLineCommand(line);
						
						String json = line.getProperties();
						rb = Response.ok(json);
					}			
					
				}
			}	
		}
		return rb.build();
	}



	@RolesAllowed({"ADMIN", "USER"})
	@GET
	@Path("/users/{id_user}/reviews")
	public Response getReviews(@PathParam("id_user") int id_user, @Context HttpRequest request)
	{
		final HttpHeaders headers = request.getHttpHeaders();
		final String AUTHORIZATION_PROPERTY = "Authorization";
		final String AUTHENTICATION_SCHEME = "Basic";
		final List<String> authorization = headers.getRequestHeader(AUTHORIZATION_PROPERTY);
		final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");

		String usernameAndPassword="";
		try {
			usernameAndPassword = new String(Base64.decode(encodedUserPassword));
		} catch (IOException e) {
			e.printStackTrace();
		}

		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
		final String username = tokenizer.nextToken();
		final String password = tokenizer.nextToken();

		User user = UserDatabase.findUserByID(id_user);

		ResponseBuilder rb;
		if(user == null)
		{
			rb = Response.serverError().status(404);
		}
		else 
		{
			if(!user.getUsername().equals(username)||!user.getPassword().equals(password))
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
	@Path("/users/{username}/{password}/{firstname}/{surname}/{mail}")
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
	@Path("/users/{id}/{username}/{password}/{firstname}/{surname}/{mail}")
	public Response updateUser(@PathParam("id") int id, @PathParam("username") String username, @PathParam("password") String password,
			@PathParam("firstname") String firstname, @PathParam("surname") String surname, 
			@PathParam("mail") String mail, @Context HttpRequest request
			)
	{
		final HttpHeaders headers = request.getHttpHeaders();
		final String AUTHORIZATION_PROPERTY = "Authorization";
		final String AUTHENTICATION_SCHEME = "Basic";
		final List<String> authorization = headers.getRequestHeader(AUTHORIZATION_PROPERTY);
		final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");

		String usernameAndPassword="";
		try {
			usernameAndPassword = new String(Base64.decode(encodedUserPassword));
		} catch (IOException e) {
			e.printStackTrace();
		}

		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
		final String auth_username = tokenizer.nextToken();
		final String auth_password = tokenizer.nextToken();

		User user = UserDatabase.findUserByID(id);

		ResponseBuilder rb;
		if(user == null)
		{
			rb = Response.serverError().status(404);
		}
		else 
		{
			if(!user.getUsername().equals(auth_username)||!user.getPassword().equals(auth_password))
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
	@Path("/users/role/{id}/{role}")
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