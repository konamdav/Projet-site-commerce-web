package interceptor;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ResourceMethodInvoker;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;
import org.jboss.resteasy.spi.metadata.ResourceMethod;
import org.jboss.resteasy.util.Base64;

import user.User;
import user.UserDatabase;




@Provider
@ServerInterceptor
public class SecurityInterceptor implements PreProcessInterceptor
{
	private static final String AUTHORIZATION_PROPERTY = "Authorization";
	private static final String AUTHENTICATION_SCHEME = "Basic";
	private static final ServerResponse ACCESS_DENIED = new ServerResponse("Access denied for this resource", 401, new Headers<Object>());;
	private static final ServerResponse ACCESS_FORBIDDEN = new ServerResponse("Nobody can access this resource", 403, new Headers<Object>());;
	private static final ServerResponse SERVER_ERROR = new ServerResponse("INTERNAL SERVER ERROR", 500, new Headers<Object>());;


	@Override
	public ServerResponse preProcess(HttpRequest request, ResourceMethodInvoker methodInvoked)
			throws Failure, WebApplicationException {

		Method method = methodInvoked.getMethod();

		//Access allowed for all 
		if(method.isAnnotationPresent(PermitAll.class))
		{
			return null;
		}
		//Access denied for all 
		if(method.isAnnotationPresent(DenyAll.class))
		{
			return ACCESS_FORBIDDEN;
		}

		//Get request headers
		final HttpHeaders headers = request.getHttpHeaders();
		
		
		
		//Fetch authorization header
		final List<String> authorization = headers.getRequestHeader(AUTHORIZATION_PROPERTY);

		//If no authorization information present; block access
		if(authorization == null || authorization.isEmpty())
		{
			return ACCESS_DENIED;
		}

		//Get encoded username and password
		final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");

		//Decode username and password
		String usernameAndPassword;
		try {
			usernameAndPassword = new String(Base64.decode(encodedUserPassword));
		} catch (IOException e) {
			return SERVER_ERROR;
		}

		//Split username and password tokens
		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
		final String username = tokenizer.nextToken();
		final String password = tokenizer.nextToken();

		System.out.println("data auth ");
		System.out.println(username);
		System.out.println(password);


		//Verify user access
		if(method.isAnnotationPresent(RolesAllowed.class))
		{
			RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
			Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));

			//Is user valid?
			if( ! isUserAllowed(username, password, rolesSet, request))
			{
				return ACCESS_DENIED;
			}
		}
		return null;
	}

	private boolean isUserAllowed(final String username, final String password, final Set<String> rolesSet, HttpRequest request) 
	{
		System.out.println("check auth ...");
		boolean isAllowed = false;

		User user = new User();
		user.setUsername(username);
		user.setPassword(password);

		user = UserDatabase.findByCriteria(username, password);

		

		if(user!= null && rolesSet.contains(user.getRole()))
		{
			System.out.println("INTERCEPT ID "+user.getId());
			request.setAttribute("INTERCEPTOR-ID-USER", user.getId());
			isAllowed = true;
			System.out.println("correct auth :/");
		}
		else
		{
			System.out.println("bad auth :/"+user.getProperties());
		}
		return isAllowed;
	}
}