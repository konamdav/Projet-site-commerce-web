package user;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.security.*;
import javax.interceptor.Interceptors;
import javax.ws.rs.GET;
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
	@PUT
	@Path("/users/{username}/{password}/{firstname}/{surname}/{mail}")
	public Response updateUserById(@PathParam("username") String username, @PathParam("password") String password,
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
			UserDatabase.insertUser(user);

			return Response.ok(user.getProperties())
					.status(200)
					.build();
		}
		else
		{
			return Response.ok().status(500).build();
		}
	}
}