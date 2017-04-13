package product;


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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.jrockit.jfr.RequestDelegate;



@Path("/products-service")
public class ProductService
{
	@PermitAll
	@GET
	@Path("/products/{id}")
	public Response getProductById(@PathParam("id") int id, @Context HttpRequest request)
	{
		Product product = ProductDatabase.findProductByID(id);

		ResponseBuilder rb;
		if(product == null)
		{
			rb = Response.serverError().status(404);
		}
		else
		{
			System.out.println(product.getProperties());
			rb = Response.ok(product.getProperties());
		}		
		return rb.build();
	}

	@PermitAll
	@GET
	@Path("/products")
	public Response getProducts(@Context HttpRequest request)
	{
		List list = ProductDatabase.findAll();
		ObjectMapper mapper = new ObjectMapper();
		String json = "{}";
		try {
			json = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		ResponseBuilder rb;
		if(json.isEmpty())
		{
			rb = Response.serverError().status(404);
		}
		else
		{
			rb = Response.ok(json).status(200);
		}		
		return rb.build();
	}


	@RolesAllowed({"ADMIN", "USER"})
	@PUT
	@Path("/products")
	public Response updateUserById()
	{
		return null;
	}
}