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

import com.oracle.jrockit.jfr.RequestDelegate;



@Path("/user-service")
public class UserService
{
	@RolesAllowed({"ADMIN", "USER"})
	@GET
	@Path("/users/{id}")
	public Response getUserById(@PathParam("id") int id, @Context HttpRequest request)
	{
		System.out.println("GET USER "+id);
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