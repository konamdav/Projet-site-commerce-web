package user;




import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.security.*;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
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

import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ServerResponse;
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
import review.Review;


/***
 * Webservice user
 * @author Davy
 *
 */
@Path("/users-service")
public class UserService
{
	public static String encrypt(String strClearText,String strKey) {
		String strData="";
		
		try {
			SecretKeySpec skeyspec=new SecretKeySpec(strKey.getBytes(),"Blowfish");
			Cipher cipher=Cipher.getInstance("Blowfish");
			cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
			byte[] encrypted=cipher.doFinal(strClearText.getBytes());
			strData=new String(encrypted);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strData;
	}
	
	/** get connected user **/
	@RolesAllowed({"ADMIN", "USER"})
	@GET
	@Path("/user")
	public Response getUser(@Context HttpServletRequest request)
	{
		User user = (User) request.getSession().getAttribute("USER");

		Response response;
		if(user == null)
		{
			response = new ServerResponse("USER NOT FOUND", 404, new Headers<Object>());
		}
		else if(request.getAttribute("INTERCEPTOR-ID-USER")==null || user.getId() != (int)request.getAttribute("INTERCEPTOR-ID-USER"))
		{
			response  = new ServerResponse("FORBIDDEN", 403, new Headers<Object>());
		}
		else
		{
			response = Response.ok(user.getProperties()).build();
		}		
		return response;
	}
	
	
	/** get  user **/
	@RolesAllowed({"ADMIN", "USER"})
	@GET
	@Path("/user/{id}")
	public Response getUser(@PathParam("id") int id, @Context HttpServletRequest request)
	{
		User user = (User) request.getSession().getAttribute("USER");
		User userTmp = UserDatabase.findUserByID(id);
		Response response;
		if(user == null || userTmp == null)
		{
			response = new ServerResponse("USER NOT FOUND", 404, new Headers<Object>());
		}
		else if(request.getAttribute("INTERCEPTOR-ID-USER")==null || user.getId() != (int)request.getAttribute("INTERCEPTOR-ID-USER"))
		{
			response  = new ServerResponse("FORBIDDEN", 403, new Headers<Object>());
		}
		else
		{
			response = Response.ok(userTmp.getProperties()).build();
		}		
		return response;
	}

	/***
	 * Connect user
	 * @param username
	 * @param password
	 * @param request
	 * @return
	 */
	@PermitAll
	@PUT
	@Path("/connect/{username}/{password}")
	public Response connectUser(@PathParam("username") String username, @PathParam("password") String password, @Context HttpServletRequest  request)
	{
		User user = UserDatabase.findByCriteria(username, password);
		Response  response;
		if(user == null)
		{
			response  = new ServerResponse("NOT FOUND", 404, new Headers<Object>());
		}
		else
		{
			request.getSession().setAttribute("USER", user);
			response = Response.ok(user.getProperties()).build();
		}

		return response;
	}
	
	
	/**
	 * Disconnect user
	 * @param request
	 * @return
	 */
	@RolesAllowed({"USER", "ADMIN"})
	@PUT
	@Path("/disconnect")
	public Response disconnectUser(@Context HttpServletRequest  request)
	{
		System.out.println("disconnect");
		User user = (User) request.getSession().getAttribute("USER");
		Response response = null;
		if(user == null)
		{
			response  = new ServerResponse("NOT FOUND", 404, new Headers<Object>());
		}
		else 
		{
			if(request.getAttribute("INTERCEPTOR-ID-USER")==null || user.getId() != (int)request.getAttribute("INTERCEPTOR-ID-USER"))
			{
				response  = new ServerResponse("FORBIDDEN", 403, new Headers<Object>());
			}
			else
			{
				request.getSession().removeAttribute("USER");
				request.getSession().invalidate();
				response = Response.ok("SUCCESS").build();
			}
		}
		return response;
	}

	/**
	 * Get command
	 * @param id_command
	 * @param request
	 * @return
	 */
	@RolesAllowed({"ADMIN", "USER"})
	@GET
	@Path("/user/commands/{id_command}")
	public Response getCommandById(@PathParam("id_command") int id_command, @Context HttpServletRequest request)
	{
		User user = (User) request.getSession().getAttribute("USER");
		Response response;
		if(user == null)
		{
			response  = new ServerResponse("NOT FOUND", 404, new Headers<Object>());
		}
		else 
		{
			if(request.getAttribute("INTERCEPTOR-ID-USER")==null || user.getId() != (int)request.getAttribute("INTERCEPTOR-ID-USER"))
			{
				response  = new ServerResponse("FORBIDDEN", 403, new Headers<Object>());
			}
			else
			{
				System.err.println("ID = "+request.getAttribute("INTERCEPTOR-ID-USER"));
				Command command = UserDatabase.findCommand(id_command);
				if(command == null || command.getId_user()!= user.getId())
				{
					response  = new ServerResponse("NOT FOUND", 404, new Headers<Object>());
				}
				else {
					response = Response.ok(command.getProperties()).build();
				}	
			}
		}
		return response;
	}


	/**
	 * Get user panier
	 * @param request
	 * @return
	 */
	@PermitAll
	@GET
	@Path("/user/panier")
	public Response getPanier(@Context HttpServletRequest request)
	{
		Response response;
		Command command = Functions.getPanier(request);
		if(command == null)
		{
			response  = new ServerResponse("NOT FOUND", 404, new Headers<Object>());
		}
		else {
			response = Response.ok(command.getProperties()).build();
		}	
		return response;
	}

	/**
	 * Get commands 
	 * @param request
	 * @return
	 * @throws JSONException
	 */
	@RolesAllowed({"ADMIN", "USER"})
	@GET
	@Path("user/commands")
	public Response getCommands(@Context HttpServletRequest request) throws JSONException
	{
		User user = (User) request.getSession().getAttribute("USER");
		Response response ;
		if(user == null)
		{
			response  = new ServerResponse("NOT FOUND", 404, new Headers<Object>());
		}
		else 
		{
			if(request.getAttribute("INTERCEPTOR-ID-USER")==null || user.getId() != (int)request.getAttribute("INTERCEPTOR-ID-USER"))
			{
				response  = new ServerResponse("FORBIDDEN", 403, new Headers<Object>());
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

				response = Response.ok(json).build();
			}	
		}
		return response;
	}

	/**
	 * Pay the command
	 * @param request
	 * @return
	 * @throws JSONException
	 */
	@RolesAllowed({"ADMIN", "USER"})
	@PUT
	@Path("user/panier/pay")
	public Response validateCommand(@Context HttpServletRequest request) throws JSONException
	{
		User user = (User) request.getSession().getAttribute("USER");

		Response  response;
		if(user == null)
		{
			response  = new ServerResponse("NOT FOUND", 404, new Headers<Object>());
		}
		else 
		{
			if(request.getAttribute("INTERCEPTOR-ID-USER")==null || user.getId() != (int)request.getAttribute("INTERCEPTOR-ID-USER"))
			{
				response  = new ServerResponse("FORBIDDEN", 403, new Headers<Object>());
			}
			else
			{
				Command command = Functions.getPanier(request);
				command.setId_user(user.getId());
				if(command == null  || command.getLinecommands().isEmpty())
				{
					response  = new ServerResponse("FORBIDDEN", 403, new Headers<Object>());
				}
				else
				{
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					command.setDate_command(sdf.format(new Date()));
					UserDatabase.saveCommand(command);	
					
					Command newCommand = UserDatabase.findLastCommand(user.getId());
					
					for (LineCommand lc : command.getLinecommands())
					{
						lc.setId_command(newCommand.getId());
						UserDatabase.saveLineCommand(lc);
					}
					
					Functions.newPanier(request);
					
					String json = command.getProperties();
					response = Response.ok(json).build();
				}
			}	
		}
		return response;
	}


	/**
	 * Delete line command
	 * @param id_product
	 * @param request
	 * @return
	 * @throws JSONException
	 */
	@RolesAllowed({"ADMIN", "USER"})
	@DELETE
	@Path("user/panier/{id_product}")
	public Response deleteProductToCommand(	@PathParam("id_product") int id_product,
			@Context HttpServletRequest request) throws JSONException
	{
		User user = (User) request.getSession().getAttribute("USER");
		Response response;
		if(user == null)
		{
			response  = new ServerResponse("NOT FOUND", 404, new Headers<Object>());
		}
		else 
		{
			if(request.getAttribute("INTERCEPTOR-ID-USER")==null || user.getId() != (int)request.getAttribute("INTERCEPTOR-ID-USER"))
			{
				response  = new ServerResponse("FORBIDDEN", 403, new Headers<Object>());
			}
			else
			{
				Command command = Functions.getPanier(request);
				Product product = ProductDatabase.findProductByID(id_product);
				if(command == null || product == null){
					response  = new ServerResponse("FORBIDDEN", 403, new Headers<Object>());
				}
				else
				{	
					LineCommand  linecmd = command.findLineCommand(product);
					if(linecmd == null)
					{
						response  = new ServerResponse("FORBIDDEN", 403, new Headers<Object>());
					}
					else
					{
						LineCommand line = command.removeLineCommand(linecmd);
						String json = line.getProperties();
						response = Response.ok(json).build();
					}

				}
			}	
		}
		return response;
	}

	/**
	 * add a product
	 * @param id_product
	 * @param qt
	 * @param request
	 * @return
	 * @throws JSONException
	 */
	@PermitAll
	@POST
	@Path("user/panier/add/{id_product}/{qt}")
	public Response addProductToCommand(@PathParam("id_product") int id_product, @PathParam("qt") int qt,
			@Context HttpServletRequest request) throws JSONException
	{

		Response response;
		Command command = Functions.getPanier(request);
		if(command == null)
		{
			response  = new ServerResponse("FORBIDDEN", 403, new Headers<Object>());
		}
		else
		{	
			Product  product = ProductDatabase.findProductByID(id_product);
			if(product == null)
			{
				response  = new ServerResponse("FORBIDDEN", 403, new Headers<Object>());
			}
			else
			{
				LineCommand line = command.addLineCommand(product, qt);
				String json = line.getProperties();
				response = Response.ok(json).build();
			}
		}

		return response;
	}


/**
 * New line commmand
 * @param id_product
 * @param qt
 * @param request
 * @return
 * @throws JSONException
 */
	@PermitAll
	@PUT
	@Path("user/panier/{id_product}/{qt}")
	public Response updateProductCommand( @PathParam("id_product") int id_product, @PathParam("qt") int qt,
			@Context HttpServletRequest request) throws JSONException
	{


		Response response;

		Command command = Functions.getPanier(request);
		Product product = ProductDatabase.findProductByID(id_product);
		if(product == null || command == null)
		{
			response  = new ServerResponse("FORBIDDEN", 403, new Headers<Object>());
		}
		else
		{	
			LineCommand line = command.findLineCommand(product);
			if(line == null || line.getId_command() !=command.getId())
			{
				response  = new ServerResponse("FORBIDDEN", 403, new Headers<Object>());
			}
			else
			{
				line.setQuantity(qt);
				String json = line.getProperties();
				response = Response.ok(json).build();
			}			
		}
		return response;
	}

	/***
	 * Get review
	 * @param request
	 * @return
	 */
	@RolesAllowed({"ADMIN", "USER"})
	@GET
	@Path("/user/reviews")
	public Response getReviews(@Context HttpServletRequest request)
	{
		User user = (User) request.getSession().getAttribute("USER");
		Response response;
		if(user == null)
		{
			response  = new ServerResponse("NOT FOUND", 404, new Headers<Object>());
		}
		else 
		{
			if(request.getAttribute("INTERCEPTOR-ID-USER")==null || user.getId() != (int)request.getAttribute("INTERCEPTOR-ID-USER"))
			{
				response  = new ServerResponse("FORBIDDEN", 403, new Headers<Object>());
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

				response = Response.ok(json).build();
			}	
		}
		return response;
	}
	
	


	/**
	 * Sign in
	 * @param username
	 * @param password
	 * @param firstname
	 * @param surname
	 * @param mail
	 * @return
	 */
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
			String s = "";
			user = new User();
			user.setFirstname(firstname);
			user.setMail(mail);
			user.setRole("USER");
			user.setSurname(surname);
			user.setUsername(username);
			user.setPassword(Base64.encodeBytes(password.getBytes()));
			
			System.out.println("PASSWORD CRYPT "+user.getPassword());
			
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

	
	/**
	 * Update user
	 * @param username
	 * @param password
	 * @param firstname
	 * @param surname
	 * @param mail
	 * @param request
	 * @return
	 */
	
	@RolesAllowed({"ADMIN", "USER"})
	@PUT
	@Path("/user/{username}/{password}/{firstname}/{surname}/{mail}")
	public Response updateUser(@PathParam("username") String username, @PathParam("password") String password,
			@PathParam("firstname") String firstname, @PathParam("surname") String surname, 
			@PathParam("mail") String mail, @Context HttpServletRequest request
			)
	{
		
		User user = (User) request.getSession().getAttribute("USER");	

		Response response;
		if(user == null)
		{
			response  = new ServerResponse("NOT FOUND", 404, new Headers<Object>());
		}
		else 
		{
			if(	request.getAttribute("INTERCEPTOR-ID-USER")==null || user.getId() != (int)request.getAttribute("INTERCEPTOR-ID-USER"))
			{
				response  = new ServerResponse("FORBIDDEN", 403, new Headers<Object>());
			}
			else
			{
				String json ="";
				user.setUsername(username);
				//user.setPassword(Base64.encodeBytes(password.getBytes()));
				user.setFirstname(firstname);
				user.setSurname(surname);
				user.setMail(mail);

				UserDatabase.saveUser(user);
				json = user.getProperties();

				response = Response.ok(json).build();
			}	
		}
		return response;
	}


	/**
	 * Update role user
	 * @param id
	 * @param role
	 * @param request
	 * @return
	 */
	@RolesAllowed({"ADMIN"})
	@PUT
	@Path("/user/role/{id}/{role}")
	public Response updateRoleUser(@PathParam("id") int id, @PathParam("role") String role, @Context HttpRequest request)
	{
		User user = UserDatabase.findUserByID(id);
		Response response;
		if(user == null)
		{
			response  = new ServerResponse("NOT FOUND", 404, new Headers<Object>());
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

			response = Response.ok(json).build();

		}
		return response;
	}
}