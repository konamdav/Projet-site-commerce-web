package product;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;

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

import org.hibernate.Session;
import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.util.Base64;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.oracle.jrockit.jfr.RequestDelegate;

import console.Console;
import generic.Database;
import genre.Genre;
import pegi_classification.PegiClassification;
import picture.Picture;
import publisher.Publisher;
import review.Review;
import tag.Tag;
import user.User;
import user.UserDatabase;
import videogame.VideoGame;

/**
 * Webservice product
 * @author Davy
 *
 */
@Path("/products-service")
public class ProductService
{
	/**
	 * Get product
	 * @param id
	 * @param request
	 * @return
	 */
	@PermitAll
	@GET
	@Path("/products/{id}")
	public Response getProductById(@PathParam("id") int id, @Context HttpRequest request)
	{
		Session session = Database.init();
		Product product = ProductDatabase.findProductByID(id, session);

		Response response;
		if(product == null)
		{
			response  = new ServerResponse("NOT FOUND", 404, new Headers<Object>());
		}
		else
		{
			response = Response.ok(product.getProperties()).build();
		}		
		Database.close(session);
		return response;
	}
	
	@PermitAll
	@GET
	@Path("/videogames/{id}")
	public Response getvgById(@PathParam("id") int id, @Context HttpRequest request)
	{
		Session session = Database.init();
		VideoGame videogame = ProductDatabase.findVideoGameByID(id, session);

		Response response;
		if(videogame == null)
		{
			response  = new ServerResponse("NOT FOUND", 404, new Headers<Object>());
		}
		else
		{
			response = Response.ok(videogame.getProperties()).build();
		}		
		Database.close(session);
		return response;
	}

	@PermitAll
	@GET
	@Path("/products/discovery")
	public Response getDiscovery( @Context HttpRequest request)
	{
		Session session = Database.init();
		List list = ProductDatabase.RandomProducts(session);
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String json = "[]";
		try {
			json = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		Response response;
		if(json.isEmpty())
		{
			response  = new ServerResponse("NOT FOUND", 404, new Headers<Object>());
		}
		else
		{
			response = Response.ok(json).status(200).build();
		}		
		
		Database.close(session);
		return response;
	}
	
	@PermitAll
	@GET
	@Path("/products/best")
	public Response getBest( @Context HttpRequest request)
	{
		Session session = Database.init();
		List list = ProductDatabase.BestProducts(session);
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String json = "[]";
		try {			
			json = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		Response response;
		if(json.isEmpty())
		{
			response  = new ServerResponse("NOT FOUND", 404, new Headers<Object>());
		}
		else
		{
			response = Response.ok(json).status(200).build();
		}		
		
		Database.close(session);
		return response;
	}

	/**
	 * Get products
	 * @param request
	 * @return
	 */
	@PermitAll
	@GET
	@Path("/products")
	public Response getProducts(@Context HttpRequest request)
	{
		Session session = Database.init();
		List list = ProductDatabase.findAllProducts(session);
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String json = "[]";
		try {
			json = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		Response response;
		if(json.isEmpty())
		{
			response  = new ServerResponse("NOT FOUND", 404, new Headers<Object>());
		}
		else
		{
			response = Response.ok(json).status(200).build();
		}		
		
		Database.close(session);
		return response;
	}




	@PermitAll
	@GET
	@Path("/videogames")
	public Response getVideoGames(@Context HttpRequest request)
	{
		Session session = Database.init();
		List list = ProductDatabase.findAllVideoGames(session);
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String json = "[]";
		try {
			json = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		Response response;
		if(json.isEmpty())
		{
			response  = new ServerResponse("NOT FOUND", 404, new Headers<Object>());
		}
		else
		{
			response = Response.ok(json).status(200).build();
		}	
		
		Database.close(session);
		return response;
	}

	@PermitAll
	@GET
	@Path("/publishers")
	public Response getPublishers(@Context HttpRequest request)
	{
		Session session = Database.init();
		List list = ProductDatabase.findAllPublishers(session);
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String json = "[]";
		try {
			json = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		Response response;
		if(json.isEmpty())
		{
			response  = new ServerResponse("NOT FOUND", 404, new Headers<Object>());
		}
		else
		{
			response = Response.ok(json).status(200).build();
		}
		
		Database.close(session);
		return response;
	}


	@PermitAll
	@GET
	@Path("/consoles")
	public Response getConsoles(@Context HttpRequest request)
	{
		Session session = Database.init();
		List list = ProductDatabase.findAllConsoles(session);
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String json = "[]";
		try {
			json = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		Response response;
		if(json.isEmpty())
		{
			response  = new ServerResponse("NOT FOUND", 404, new Headers<Object>());
		}
		else
		{
			response = Response.ok(json).status(200).build();
		}
		
		Database.close(session);
		return response;
	}
	
	@PermitAll
	@GET
	@Path("/tags")
	public Response getTags(@Context HttpRequest request)
	{
		Session session = Database.init();
		List list = ProductDatabase.findAllTags(session);
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String json = "[]";
		try {
			json = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		Response response;
		if(json.isEmpty())
		{
			response  = new ServerResponse("NOT FOUND", 404, new Headers<Object>());
		}
		else
		{
			response = Response.ok(json).status(200).build();
		}
		
		Database.close(session);
		return response;
	}
	
	@PermitAll
	@GET
	@Path("/pegi")
	public Response getPegi(@Context HttpRequest request)
	{
		Session session = Database.init();
		List list = ProductDatabase.findAllPegi(session);
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String json = "[]";
		try {
			json = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		Response response;
		if(json.isEmpty())
		{
			response  = new ServerResponse("NOT FOUND", 404, new Headers<Object>());
		}
		else
		{
			response = Response.ok(json).status(200).build();
		}
		
		Database.close(session);
		return response;
	}

	
	@PermitAll
	@GET
	@Path("/genres")
	public Response getGenres(@Context HttpRequest request)
	{
		Session session = Database.init();
		List list = ProductDatabase.findAllGenres(session);
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String json = "[]";
		try {
			json = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		Response response;
		if(json.isEmpty())
		{
			response  = new ServerResponse("NOT FOUND", 404, new Headers<Object>());
		}
		else
		{
			response = Response.ok(json).status(200).build();
		}		
		
		Database.close(session);
		return response;
	}


	/**
	 * New product
	 * @param id_videogame
	 * @param id_console
	 * @param price
	 * @param date
	 * @param request
	 * @return
	 */
	@RolesAllowed({"ADMIN"})
	@POST
	@Path("/products/{id_videogame}/{id_console}/{price}/{date}")
	public Response newProduct(@PathParam("id_videogame") int id_videogame,
			@PathParam("id_console") int id_console,
			@PathParam("price") double price,
			@PathParam("date") String date,
			@Context HttpServletRequest request)
	{
		Session session = Database.init();
		VideoGame videogame = ProductDatabase.findVideoGameByID(id_videogame, session);
		Console console = ProductDatabase.findConsoleByID(id_console, session);

		Response response;

		if(request.getSession().getAttribute("USER")!=null && videogame!= null && console!=null)
		{
			Product product = ProductDatabase.findProductByKey(videogame, console, session);			
			if(product != null)
			{
				response  = new ServerResponse("FORBIDDEN", 403, new Headers<Object>());
			}
			else
			{
				product = new Product();
				product.setConsole(console);
				product.setDate_release(date);
				product.setPrice(price);
				product.setVideogame(videogame);

				ProductDatabase.insertProduct(product, session);
				response = Response.ok(product.getProperties()).build();
			}		
		}
		else
		{
			response  = new ServerResponse("FORBIDDEN", 403, new Headers<Object>());
		}
		
		Database.close(session);

		return response;
	}


	@RolesAllowed({"ADMIN"})
	@POST
	@Path("/videogames/{name}/{id_publisher}")
	public Response newVideoGame(@PathParam("name") String name,
			@PathParam("id_publisher") int id_publisher,
			@Context HttpRequest request)
	{
		Session session = Database.init();
		VideoGame videogame = ProductDatabase.findVideoGameByName(name, session);
		Publisher publisher = ProductDatabase.findPublisherByID(id_publisher, session);
		Response response;

		if(videogame == null && publisher !=null)
		{
			videogame = new VideoGame();
			videogame.setName(name);
			videogame.setPublisher(publisher);
			ProductDatabase.insertVideoGame(videogame, session);

			videogame = ProductDatabase.findVideoGameByName(name, session);
			response = Response.ok(videogame.getProperties()).build();

		}
		else
		{
			response  = new ServerResponse("FORBIDDEN", 403, new Headers<Object>());
		}

		Database.close(session);
		
		return response;
	}


	@RolesAllowed({"ADMIN"})
	@POST
	@Path("/videogames/{id_videogame}/genres/{id_genre}")
	public Response addGenre(@PathParam("id_videogame") int id_videogame,
			@PathParam("id_genre") int id_genre,
			@Context HttpRequest request)
	{
		Session session = Database.init();
		VideoGame videogame = ProductDatabase.findVideoGameByID(id_videogame,session);
		Genre genre = ProductDatabase.findGenreByID(id_genre, session);
		Response response;

		if(videogame != null && genre !=null  && !videogame.getGenres().contains(genre))
		{
			videogame.getGenres().add(genre);
			ProductDatabase.insertVideoGame(videogame, session);

			videogame = ProductDatabase.findVideoGameByID(id_videogame, session);
			response = Response.ok(videogame.getProperties()).build();

		}
		else
		{
			response  = new ServerResponse("FORBIDDEN", 403, new Headers<Object>());
		}
		Database.close(session);
		return response;
	}


	@RolesAllowed({"ADMIN"})
	@DELETE
	@Path("/videogames/{id_videogame}/genres/{id_genre}")
	public Response delGenre(@PathParam("id_videogame") int id_videogame,
			@PathParam("id_genre") int id_genre,
			@Context HttpRequest request)
	{
		Session session = Database.init();
		VideoGame videogame = ProductDatabase.findVideoGameByID(id_videogame,session);
		Genre genre = ProductDatabase.findGenreByID(id_genre, session);
		Response response;

		if(videogame != null && genre !=null  && videogame.getGenres().contains(genre))
		{
			videogame.getGenres().remove(genre);
			ProductDatabase.insertVideoGame(videogame, session);

			videogame = ProductDatabase.findVideoGameByID(id_videogame, session);
			response = Response.ok(videogame.getProperties()).build();

		}
		else
		{
			response  = new ServerResponse("FORBIDDEN", 403, new Headers<Object>());
		}
		Database.close(session);
		return response;
	}


	@RolesAllowed({"ADMIN"})
	@POST
	@Path("/videogames/{id_videogame}/pegi/{id_pegi}")
	public Response addPEGI(@PathParam("id_videogame") int id_videogame,
			@PathParam("id_pegi") int id_pegi,
			@Context HttpRequest request)
	{
		Session session = Database.init();
		
		VideoGame videogame = ProductDatabase.findVideoGameByID(id_videogame, session);
		PegiClassification pegi = ProductDatabase.findPegiByID(id_pegi, session);
		Response response;

		if(videogame != null && pegi !=null  && !videogame.getGenres().contains(pegi))
		{
			videogame.getClassifications().add(pegi);
			ProductDatabase.insertVideoGame(videogame, session);

			videogame = ProductDatabase.findVideoGameByID(id_videogame, session);
			response = Response.ok(videogame.getProperties()).build();

		}
		else
		{
			response  = new ServerResponse("FORBIDDEN", 403, new Headers<Object>());
		}

		Database.close(session);
		return response;
	}


	@RolesAllowed({"ADMIN"})
	@DELETE
	@Path("/videogames/{id_videogame}/pegi/{id_pegi}")
	public Response delPEGI(@PathParam("id_videogame") int id_videogame,
			@PathParam("id_pegi") int id_pegi,
			@Context HttpRequest request)
	{
		Session session = Database.init();
		
		VideoGame videogame = ProductDatabase.findVideoGameByID(id_videogame, session);
		PegiClassification pegi = ProductDatabase.findPegiByID(id_pegi, session);
		Response response;

		if(videogame != null && pegi !=null  && videogame.getGenres().contains(pegi))
		{
			videogame.getClassifications().remove(pegi);
			ProductDatabase.insertVideoGame(videogame, session);

			videogame = ProductDatabase.findVideoGameByID(id_videogame, session);
			response = Response.ok(videogame.getProperties()).build();

		}
		else
		{
			response  = new ServerResponse("FORBIDDEN", 403, new Headers<Object>());
		}

		Database.close(session);
		return response;
	}

	

	@RolesAllowed({"ADMIN"})
	@POST
	@Path("/videogames/{id_videogame}/tags/{name}")
	public Response addTag(@PathParam("id_videogame") int id_videogame,
			@PathParam("name") String name,
			@Context HttpRequest request)
	{
		Session session = Database.init();
		VideoGame videogame = ProductDatabase.findVideoGameByID(id_videogame, session);
		Tag tag = ProductDatabase.findTagByName(name, session);
		if(tag == null)
		{
			System.out.println("TAG new ");
			tag = new Tag();
			tag.setName(name);
			ProductDatabase.insertTag(tag, session);
			tag = ProductDatabase.findTagByName(name, session);
		}

		Response response;

		boolean flag = false;
		for(Tag t : videogame.getTags())
		{
			if(t.getName().equals(name))
			{
				flag = true;
			}
		}

		if(videogame != null && tag !=null  && !flag)
		{
			videogame.getTags().add(tag);
			ProductDatabase.insertVideoGame(videogame, session);

			videogame = ProductDatabase.findVideoGameByID(id_videogame, session);
			response = Response.ok(videogame.getProperties()).build();

		}
		else
		{
			response  = new ServerResponse("FORBIDDEN", 403, new Headers<Object>());
		}

		Database.close(session);
		return response;
	}
	
	
	@RolesAllowed({"ADMIN"})
	@DELETE
	@Path("/videogames/{id_videogame}/tags/{name}")
	public Response delTag(@PathParam("id_videogame") int id_videogame,
			@PathParam("name") String name,
			@Context HttpRequest request)
	{
		Session session = Database.init();
		VideoGame videogame = ProductDatabase.findVideoGameByID(id_videogame, session);
		Tag tag = ProductDatabase.findTagByName(name, session);
		Response response;

		if(videogame != null && tag !=null  )
		{
			videogame.getTags().remove(tag);
			ProductDatabase.insertVideoGame(videogame, session);

			videogame = ProductDatabase.findVideoGameByID(id_videogame, session);
			response = Response.ok(videogame.getProperties()).build();

		}
		else
		{
			response  = new ServerResponse("FORBIDDEN", 403, new Headers<Object>());
		}

		Database.close(session);
		return response;
	}

	@RolesAllowed({"ADMIN"})
	@PUT
	@Path("/videogames/{id_videogame}/{name}/{id_publisher}")
	public Response updateVideoGame(
			@PathParam("id_videogame") int id_videogame,
			@PathParam("name") String name,
			@PathParam("id_publisher") int id_publisher,
			@Context HttpRequest request)
	{
		Session session = Database.init();
		VideoGame videogame = ProductDatabase.findVideoGameByID(id_videogame, session);
		VideoGame videogame2 = ProductDatabase.findVideoGameByName(name, session);
		Publisher publisher = ProductDatabase.findPublisherByID(id_publisher, session);
		Response response;

		if(videogame != null && publisher !=null && (videogame2==null || videogame2.getName().equals(videogame.getName())))
		{
			videogame.setName(name);
			videogame.setPublisher(publisher);
			ProductDatabase.insertVideoGame(videogame, session);

			response = Response.ok(videogame.getProperties()).build();

		}
		else
		{
			response  = new ServerResponse("FORBIDDEN", 403, new Headers<Object>());
		}
		Database.close(session);
		return response;
	}

	/**
	 * update product
	 * @param id_product
	 * @param id_videogame
	 * @param id_console
	 * @param price
	 * @param date
	 * @param request
	 * @return
	 */
	@RolesAllowed({"ADMIN"})
	@PUT
	@Path("/products/{id_product}/{id_videogame}/{id_console}/{price}/{date}")
	public Response updateProduct(@PathParam("id_product") int id_product, 
			@PathParam("id_videogame") int id_videogame,
			@PathParam("id_console") int id_console,
			@PathParam("price") double price,
			@PathParam("date") String date,
			@Context HttpRequest request)
	{
		Session session = Database.init();
		Product product = ProductDatabase.findProductByID(id_product, session);

		Response response;
		if(product == null)
		{
			response  = new ServerResponse("NOT FOUND", 404, new Headers<Object>());
		}
		else
		{
			Console console = ProductDatabase.findConsoleByID(id_console, session);
			VideoGame videogame = ProductDatabase.findVideoGameByID(id_videogame, session);
			
			Product product2 = ProductDatabase.findProductByKey(videogame, console, session);
			
			if(console != null && videogame != null && (product2 == null || product2.getId()== product.getId()))
			{
				product.setConsole(console);
				product.setDate_release(date);
				product.setPrice(price);
				product.setVideogame(videogame);

				ProductDatabase.insertProduct(product, session);				
				response = Response.ok(product.getProperties()).build();
			}
			else
			{
				response  = new ServerResponse("NOT FOUND", 404, new Headers<Object>());
			}

		}		
		Database.close(session);
		return response;
	}

	/**
	 * Research
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@PermitAll
	@POST
	@Path("/products/research")
	public Response research(@Context HttpServletRequest request) throws IOException
	{
		Session session = Database.init();
		BufferedReader reader = request.getReader();
		String response = new String();
		for (String line; (line = reader.readLine()) != null; response += line);
		ObjectMapper map = new ObjectMapper();
		ResearchedProduct researchedProduct = map.readValue(response, ResearchedProduct.class);


		List list =	ProductDatabase.researchProduct(researchedProduct, session);
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String json = "[]";
		try {
			json = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		Response responseServ;
		if(json.isEmpty())
		{
			responseServ  = new ServerResponse("NOT FOUND", 404, new Headers<Object>());
		}
		else
		{
			responseServ = Response.ok(json).status(200).build();
		}		
		
		Database.close(session);
		return responseServ;
	}


	/**
	 * 
	 * @param name
	 * @param request
	 * @return
	 */
	@RolesAllowed({"ADMIN"})
	@POST
	@Path("/publishers/{name}")
	public Response createPublisher(@PathParam("name") String name, @Context HttpRequest request)
	{
		System.err.println(" post publisher ");
		Session session = Database.init();
		Publisher publisher = ProductDatabase.findPublisherByName(name, session);
		if(publisher == null){
			publisher = new Publisher();
			publisher.setName(name);

			ProductDatabase.insertPublisher(publisher, session);

			publisher = ProductDatabase.findPublisherByName(name, session);
			Database.close(session);
			return Response.ok(publisher.getProperties())
					.status(200)
					.build();
		}
		else
		{
			Database.close(session);
			return new ServerResponse("FORBIDDEN USER", 403, new Headers<Object>());
		}
	}




	/**
	 * Update publisher
	 * @param id_publisher
	 * @param name
	 * @param request
	 * @return
	 */
	@RolesAllowed({"ADMIN"})
	@PUT
	@Path("/publishers/{id_publisher}/{name}")
	public Response updatePublisher(@PathParam("id_publisher") int id_publisher, @PathParam("name") String name, @Context HttpRequest request)
	{
		System.err.println(" post publisher ");
		Session session = Database.init();
		Publisher publisher = ProductDatabase.findPublisherByID(id_publisher, session);
		Publisher publisher2 = ProductDatabase.findPublisherByName(name, session);
		if(publisher != null && (publisher2==null || publisher2.getId()== publisher.getId())){

			publisher.setName(name);
			ProductDatabase.insertPublisher(publisher, session);
			Database.close(session);
			return Response.ok(publisher.getProperties())
					.status(200)
					.build();
		}
		else
		{
			Database.close(session);
			return new ServerResponse("FORBIDDEN USER", 404, new Headers<Object>());
		}
	}


	/***
	 * create genre
	 * @param name
	 * @param request
	 * @return
	 */
	@RolesAllowed({"ADMIN"})
	@POST
	@Path("/genres/{name}")
	public Response createGenre(@PathParam("name") String name, @Context HttpRequest request)
	{
		Session session = Database.init();
		Genre genre = ProductDatabase.findGenreByName(name, session);
		if(genre == null){
			genre = new Genre();
			genre.setName(name);

			ProductDatabase.insertGenre(genre, session);

			genre = ProductDatabase.findGenreByName(name, session);
			Database.close(session);
			return Response.ok(genre.getProperties())
					.status(200)
					.build();
		}
		else
		{
			Database.close(session);
			return new ServerResponse("FORBIDDEN USER", 403, new Headers<Object>());
		}
	}

	/**
	 * Update Genre
	 * @param id_genre
	 * @param name
	 * @param request
	 * @return
	 */
	@RolesAllowed({"ADMIN"})
	@PUT
	@Path("/genres/{id_genre}/{name}")
	public Response updateGenre(@PathParam("id_genre") int id_genre, @PathParam("name") String name, @Context HttpRequest request)
	{
		Session session = Database.init();
		Genre genre = ProductDatabase.findGenreByID(id_genre, session);
		Genre genre2 = ProductDatabase.findGenreByName(name, session);
		if(genre != null && (genre2 == null || genre2.getId() == genre.getId())){

			genre.setName(name);
			ProductDatabase.insertGenre(genre, session);
			Database.close(session);
			return Response.ok(genre.getProperties())
					.status(200)
					.build();
		}
		else
		{
			Database.close(session);
			return new ServerResponse("FORBIDDEN USER", 404, new Headers<Object>());
		}
	}


	/**
	 * NEW console
	 * @param name
	 * @param request
	 * @return
	 */
	@RolesAllowed({"ADMIN"})
	@POST
	@Path("/consoles/{name}")
	public Response createConsole(@PathParam("name") String name, @Context HttpRequest request)
	{
		Session session = Database.init();
		Console console = ProductDatabase.findConsoleByName(name, session);
		if(console == null){
			console = new Console();
			console.setName(name);

			ProductDatabase.insertConsole(console, session);

			console = ProductDatabase.findConsoleByName(name, session);
			Database.close(session);
			return Response.ok(console.getProperties())
					.status(200)
					.build();
		}
		else
		{
			Database.close(session);
			return new ServerResponse("FORBIDDEN USER", 403, new Headers<Object>());
		}
	}

	/***
	 * UPDATE Console
	 * @param id_console
	 * @param name
	 * @param request
	 * @return
	 */
	@RolesAllowed({"ADMIN"})
	@PUT
	@Path("/consoles/{id_console}/{name}")
	public Response updateConsole(@PathParam("id_console") int id_console, 
			@PathParam("name") String name,
			@Context HttpRequest request)
	{
		Session session = Database.init();
		Console console = 	ProductDatabase.findConsoleByID(id_console, session);
		Console console2 = ProductDatabase.findConsoleByName(name, session);
		if(console != null && (console2 == null || console2.getId() == console.getId())){

			console.setName(name);
			ProductDatabase.insertConsole(console, session);

			Database.close(session);
			return Response.ok(console.getProperties())
					.status(200)
					.build();
		}
		else
		{
			Database.close(session);
			return new ServerResponse("FORBIDDEN USER", 404, new Headers<Object>());
		}
	}



	/***
	 * delete review
	 * @param request
	 * @return
	 */
	@RolesAllowed({"ADMIN", "USER"})
	@DELETE
	@Path("/reviews/{id}")
	public Response deleteReview(@PathParam("id") int id_review, @Context HttpServletRequest request)
	{
		Session session = Database.init();
		User user = (User) request.getSession().getAttribute("USER");
		Response response;
		if(user == null)
		{
			response  = new ServerResponse("NOT FOUND", 404, new Headers<Object>());
		}
		else 
		{
			Review review = ProductDatabase.findReviewByID(id_review, session);
			if(review == null || request.getAttribute("INTERCEPTOR-ID-USER")==null || review.getId_user() != user.getId() || user.getId() != (int)request.getAttribute("INTERCEPTOR-ID-USER"))
			{
				response  = new ServerResponse("FORBIDDEN", 403, new Headers<Object>());
			}
			else
			{

				ProductDatabase.deleteReview(review, session);
				response = Response.ok("SUCCESS").build();
			}	
		
		}
		Database.close(session);
		return response;
	}

	/**
	 * USER 
	 * @param title
	 * @param note
	 * @param content
	 * @param id_user
	 * @param id_product
	 * @param request
	 * @return
	 */
	@RolesAllowed({"USER", "ADMIN"})
	@POST
	@Path("/reviews/{title}/{note}/{content}/{product}")
	public Response createReview(@PathParam("title") String title,
			@PathParam("note") int note,
			@PathParam("content") String content,
			@PathParam("product") int id_product,
			@Context HttpServletRequest request)
	{
		Session session = Database.init();
		User user = (User) request.getSession().getAttribute("USER");
		Product product = ProductDatabase.findProductByID(id_product, session);

		Response response;
		System.out.println("REVIEW "+id_product +" ADD "+user.getId() + " "+request.getAttribute("INTERCEPTOR-ID-USER")+" "+product);
		if(user!= null && product!=null && user.getId() == (int) request.getAttribute("INTERCEPTOR-ID-USER"))
		{
			Review review = ProductDatabase.findReviewByKey(user, product, session);			
			if(review != null)
			{
				System.out.println("REVIEW existe deja "+review.getTitle());
				response  = new ServerResponse("FORBIDDEN REVIEW", 403, new Headers<Object>());
			}
			else
			{
				review = new Review();
				review.setTitle(title);
				review.setNote(note);
				review.setContent(content);
				review.setId_user(user.getId());
				review.setId_product(id_product);
				ProductDatabase.insertReview(review, session);
				response = Response.ok(review.getProperties()).build();

				System.out.println("REVIEW AJOUTE");
			}		
		}
		else
		{
			System.out.println("REVIEW PB");
			response  = new ServerResponse("FORBIDDEN USER", 403, new Headers<Object>());
		}
		
		Database.close(session);

		return response;
	}
	/**
	 * update review
	 * @param id_review
	 * @param title
	 * @param note
	 * @param content
	 * @param request
	 * @return
	 */
	@RolesAllowed({"USER", "ADMIN"})
	@PUT
	@Path("/reviews/{id}/{title}/{note}/{content}")
	public Response updateReview(@PathParam("id") int id_review,
			@PathParam("title") String title,
			@PathParam("note") int note,
			@PathParam("content") String content,

			@Context HttpRequest request)
	{
		Session session = Database.init();
		Review review = ProductDatabase.findReviewByID(id_review, session);

		Response response;
		if(review == null)
		{
			response  = new ServerResponse("NOT FOUND", 404, new Headers<Object>());
		}
		else
		{
			review.setContent(content);
			review.setNote(note);
			review.setTitle(title);
			ProductDatabase.insertReview(review, session);				
			response = Response.ok(review.getProperties()).build();
		}		
		
		Database.close(session);
		return response;
	}


	/**
	 * get review
	 * @param id_product
	 * @param request
	 * @return
	 */
	@PermitAll
	@GET
	@Path("/products/{id_product}/reviews")
	public Response getReviews(@PathParam("id_product") int id_product, @Context HttpRequest request)
	{
		Session session = Database.init();
		Product product = ProductDatabase.findProductByID(id_product, session);
		Response response;
		if(product == null)
		{
			response  = new ServerResponse("NOT FOUND", 404, new Headers<Object>());
		}
		else
		{
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			String json = "[]";
			try {
				json = mapper.writeValueAsString(product.getReviews());
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			response = Response.ok(json).build();
		}
		
		Database.close(session);
		return response;

	}

	/***
	 * add Picture
	 * @param name
	 * @param link
	 * @param id_product
	 * @param request
	 * @return
	 */
	@RolesAllowed({"ADMIN"})
	@POST
	@Path("/pictures/{link}/{product}")
	public Response addPicture(@PathParam("link") String link,
			@PathParam("product") int id_product,@Context HttpRequest request)
	{
		Session session = Database.init();
		String name = UUID.randomUUID().toString();
		Product product = ProductDatabase.findProductByID(id_product, session);
		Picture picture = ProductDatabase.findPictureByName(name, session);
		if(product != null && picture == null){
			picture = new Picture();
			picture.setName(name);
			picture.setId_product(id_product);
			picture.setImg(link);

			ProductDatabase.insertPicture(picture, session);

			picture = ProductDatabase.findPictureByName(name, session);
			Database.close(session);
			return Response.ok(picture.getProperties())
					.status(200)
					.build();
		}
		else
		{
			Database.close(session);
			return new ServerResponse("FORBIDDEN USER", 403, new Headers<Object>());
		}
	}
	
	
	@RolesAllowed({"ADMIN"})
	@DELETE
	@Path("/pictures/{id}")
	public Response delPicture(@PathParam("id") int id,@Context HttpRequest request)
	{
		Session session = Database.init();
		
		
		Picture picture = ProductDatabase.findPictureByID(id, session);
		
		if(picture != null){
			Product product = ProductDatabase.findProductByID(picture.getId_product(), session);
			product.getPictures().remove(picture);
			ProductDatabase.insertProduct(product, session);
			ProductDatabase.deletePictureByID(id, session);
			Database.close(session);
			return Response.ok()
					.status(200)
					.build();
		}
		else
		{
			Database.close(session);
			return new ServerResponse("FORBIDDEN USER", 403, new Headers<Object>());
		}
	}


	/**
	 * add pegi
	 * @param name
	 * @param img
	 * @param request
	 * @return
	 */
	@RolesAllowed({"ADMIN"})
	@POST
	@Path("/pegi/{name}/{link}")
	public Response addPegi(@PathParam("name") String name,
			@PathParam("link") String img
			,@Context HttpRequest request)
	{
		Session session = Database.init();
		PegiClassification pegi = ProductDatabase.findPegiByName(name, session);
		if(pegi == null){
			pegi = new PegiClassification();
			pegi.setName(name);
			pegi.setImg(img);

			ProductDatabase.insertPegi(pegi, session);

			pegi = ProductDatabase.findPegiByName(name, session);
			Database.close(session);
			return Response.ok(pegi.getProperties())
					.status(200)
					.build();
		}
		else
		{
			Database.close(session);
			return new ServerResponse("FORBIDDEN USER", 403, new Headers<Object>());
		}
	}

	

	/**
	 * add pegi
	 * @param name
	 * @param img
	 * @param request
	 * @return
	 */
	@RolesAllowed({"ADMIN"})
	@PUT
	@Path("/pegi/{id}/{name}/{link}")
	public Response updtPegi(@PathParam("id") int id, @PathParam("name") String name,
			@PathParam("link") String img
			,@Context HttpRequest request)
	{
		Session session = Database.init();
		PegiClassification pegi = ProductDatabase.findPegiByID(id, session);
		PegiClassification pegi2 = ProductDatabase.findPegiByName(name, session);
		
		if(pegi != null && (pegi2 == null || pegi2.getId() == pegi.getId())){
			pegi.setName(name);
			pegi.setImg(img);

			ProductDatabase.insertPegi(pegi, session);

			Database.close(session);
			return Response.ok(pegi.getProperties())
					.status(200)
					.build();
		}
		else
		{
			Database.close(session);
			return new ServerResponse("FORBIDDEN USER", 403, new Headers<Object>());
		}
	}





}