package product;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

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

import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.util.Base64;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.oracle.jrockit.jfr.RequestDelegate;

import console.Console;
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
		Product product = ProductDatabase.findProductByID(id);

		Response response;
		if(product == null)
		{
			response  = new ServerResponse("NOT FOUND", 404, new Headers<Object>());
		}
		else
		{
			response = Response.ok(product.getProperties()).build();
		}		
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
		List list = ProductDatabase.findAllProducts();
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
		return response;
	}




	@PermitAll
	@GET
	@Path("/videogames")
	public Response getVideoGames(@Context HttpRequest request)
	{
		List list = ProductDatabase.findAllVideoGames();
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
		return response;
	}

	@PermitAll
	@GET
	@Path("/publishers")
	public Response getPublishers(@Context HttpRequest request)
	{
		List list = ProductDatabase.findAllPublishers();
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
		return response;
	}


	@PermitAll
	@GET
	@Path("/consoles")
	public Response getConsoles(@Context HttpRequest request)
	{
		List list = ProductDatabase.findAllConsoles();
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
			@Context HttpRequest request)
	{
		VideoGame videogame = ProductDatabase.findVideoGameByID(id_videogame);
		Console console = ProductDatabase.findConsoleByID(id_console);

		Response response;

		if(videogame!= null && console!=null)
		{
			Product product = ProductDatabase.findProductByKey(videogame, console);			
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

				ProductDatabase.insertProduct(product);
				response = Response.ok(product.getProperties()).build();
			}		
		}
		else
		{
			response  = new ServerResponse("FORBIDDEN", 403, new Headers<Object>());
		}

		return response;
	}


	@RolesAllowed({"ADMIN"})
	@POST
	@Path("/videogames/{name}/{id_publisher}")
	public Response newVideoGame(@PathParam("name") String name,
			@PathParam("id_publisher") int id_publisher,
			@Context HttpRequest request)
	{
		VideoGame videogame = ProductDatabase.findVideoGameByName(name);
		Publisher publisher = ProductDatabase.findPublisherByID(id_publisher);
		Response response;

		if(videogame == null && publisher !=null)
		{
			videogame = new VideoGame();
			videogame.setName(name);
			videogame.setPublisher(publisher);
			ProductDatabase.insertVideoGame(videogame);

			videogame = ProductDatabase.findVideoGameByName(name);
			response = Response.ok(videogame.getProperties()).build();

		}
		else
		{
			response  = new ServerResponse("FORBIDDEN", 403, new Headers<Object>());
		}

		return response;
	}


	@RolesAllowed({"ADMIN"})
	@POST
	@Path("/videogames/{id_videogame}/genres/{id_genre}")
	public Response addGenre(@PathParam("id_videogame") int id_videogame,
			@PathParam("id_genre") int id_genre,
			@Context HttpRequest request)
	{
		VideoGame videogame = ProductDatabase.findVideoGameByID(id_videogame);
		Genre genre = ProductDatabase.findGenreByID(id_genre);
		Response response;

		if(videogame != null && genre !=null  && !videogame.getGenres().contains(genre))
		{
			videogame.getGenres().add(genre);
			ProductDatabase.insertVideoGame(videogame);

			videogame = ProductDatabase.findVideoGameByID(id_videogame);
			response = Response.ok(videogame.getProperties()).build();

		}
		else
		{
			response  = new ServerResponse("FORBIDDEN", 403, new Headers<Object>());
		}

		return response;
	}


	@RolesAllowed({"ADMIN"})
	@POST
	@Path("/videogames/{id_videogame}/pegi/{id_pegi}")
	public Response addPEGI(@PathParam("id_videogame") int id_videogame,
			@PathParam("id_pegi") int id_pegi,
			@Context HttpRequest request)
	{
		VideoGame videogame = ProductDatabase.findVideoGameByID(id_videogame);
		PegiClassification pegi = ProductDatabase.findPegiByID(id_pegi);
		Response response;

		if(videogame != null && pegi !=null  && !videogame.getGenres().contains(pegi))
		{
			videogame.getClassifications().add(pegi);
			ProductDatabase.insertVideoGame(videogame);

			videogame = ProductDatabase.findVideoGameByID(id_videogame);
			response = Response.ok(videogame.getProperties()).build();

		}
		else
		{
			response  = new ServerResponse("FORBIDDEN", 403, new Headers<Object>());
		}

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
		VideoGame videogame = ProductDatabase.findVideoGameByID(id_videogame);
		Publisher publisher = ProductDatabase.findPublisherByID(id_publisher);
		Response response;

		if(videogame != null && publisher !=null)
		{
			videogame.setName(name);
			videogame.setPublisher(publisher);
			ProductDatabase.insertVideoGame(videogame);

			response = Response.ok(videogame.getProperties()).build();

		}
		else
		{
			response  = new ServerResponse("FORBIDDEN", 403, new Headers<Object>());
		}

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
		Product product = ProductDatabase.findProductByID(id_product);

		Response response;
		if(product == null)
		{
			response  = new ServerResponse("NOT FOUND", 404, new Headers<Object>());
		}
		else
		{
			Console console = ProductDatabase.findConsoleByID(id_console);
			VideoGame videogame = ProductDatabase.findVideoGameByID(id_videogame);
			if(console != null && videogame != null)
			{
				product.setConsole(console);
				product.setDate_release(date);
				product.setPrice(price);
				product.setVideogame(videogame);

				ProductDatabase.insertProduct(product);				
				response = Response.ok(product.getProperties()).build();
			}
			else
			{
				response  = new ServerResponse("NOT FOUND", 404, new Headers<Object>());
			}

		}		
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
		BufferedReader reader = request.getReader();
		String response = new String();
		for (String line; (line = reader.readLine()) != null; response += line);
		ObjectMapper map = new ObjectMapper();
		ResearchedProduct researchedProduct = map.readValue(response, ResearchedProduct.class);


		List list =	ProductDatabase.researchProduct(researchedProduct);
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

		Publisher publisher = ProductDatabase.findPublisherByName(name);
		if(publisher == null){
			publisher = new Publisher();
			publisher.setName(name);

			ProductDatabase.insertPublisher(publisher);

			publisher = ProductDatabase.findPublisherByName(name);
			return Response.ok(publisher.getProperties())
					.status(200)
					.build();
		}
		else
		{
			System.err.println(" post publisher 500 ");
			return Response.ok().status(500).build();
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

		Publisher publisher = ProductDatabase.findPublisherByID(id_publisher);

		if(publisher != null){

			publisher.setName(name);
			ProductDatabase.insertPublisher(publisher);

			return Response.ok(publisher.getProperties())
					.status(200)
					.build();
		}
		else
		{
			return Response.ok().status(404).build();
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
		Genre genre = ProductDatabase.findGenreByName(name);
		if(genre == null){
			genre = new Genre();
			genre.setName(name);

			ProductDatabase.insertGenre(genre);

			genre = ProductDatabase.findGenreByName(name);
			return Response.ok(genre.getProperties())
					.status(200)
					.build();
		}
		else
		{
			return Response.ok().status(500).build();
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
		Genre genre = ProductDatabase.findGenreByID(id_genre);
		if(genre != null){

			genre.setName(name);
			ProductDatabase.insertGenre(genre);

			return Response.ok(genre.getProperties())
					.status(200)
					.build();
		}
		else
		{
			return Response.ok().status(404).build();
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
		Console console = ProductDatabase.findConsoleByName(name);
		if(console == null){
			console = new Console();
			console.setName(name);

			ProductDatabase.insertConsole(console);

			console = ProductDatabase.findConsoleByName(name);
			return Response.ok(console.getProperties())
					.status(200)
					.build();
		}
		else
		{
			return Response.ok().status(500).build();
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
		Console console = 	ProductDatabase.findConsoleByID(id_console);
		if(console != null){

			console.setName(name);
			ProductDatabase.insertConsole(console);

			return Response.ok(console.getProperties())
					.status(200)
					.build();
		}
		else
		{
			return Response.ok().status(404).build();
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
		User user = (User) request.getSession().getAttribute("USER");
		Response response;
		if(user == null)
		{
			response  = new ServerResponse("NOT FOUND", 404, new Headers<Object>());
		}
		else 
		{
			Review review = ProductDatabase.findReviewByID(id_review);
			if(review == null || request.getAttribute("INTERCEPTOR-ID-USER")==null || review.getId_user() != user.getId() || user.getId() != (int)request.getAttribute("INTERCEPTOR-ID-USER"))
			{
				response  = new ServerResponse("FORBIDDEN", 403, new Headers<Object>());
			}
			else
			{

				ProductDatabase.deleteReview(review);
				response = Response.ok("SUCCESS").build();
			}	
		}
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
		User user = (User) request.getSession().getAttribute("USER");
		Product product = ProductDatabase.findProductByID(id_product);

		Response response;

		if(user!= null && product!=null && user.getId() == (int) request.getAttribute("INTERCEPTOR-ID-USER"))
		{
			Review review = ProductDatabase.findReviewByKey(user, product);			
			if(review != null)
			{
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
				ProductDatabase.insertReview(review);
				response = Response.ok(review.getProperties()).build();
			}		
		}
		else
		{
			response  = new ServerResponse("FORBIDDEN USER", 403, new Headers<Object>());
		}

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
		Review review = ProductDatabase.findReviewByID(id_review);

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
			ProductDatabase.insertReview(review);				
			response = Response.ok(review.getProperties()).build();
		}		
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
		Product product = ProductDatabase.findProductByID(id_product);
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
	@Path("/picture/{name}/{link}/{product}")
	public Response addPicture(@PathParam("name") String name,
			@PathParam("link") String link,
			@PathParam("product") int id_product,@Context HttpRequest request)
	{
		Product product = ProductDatabase.findProductByID(id_product);
		Picture picture = ProductDatabase.findPictureByName(name);
		if(product != null && picture == null){
			picture = new Picture();
			picture.setName(name);
			picture.setId_product(id_product);
			picture.setImg(link);

			ProductDatabase.insertPicture(picture);

			picture = ProductDatabase.findPictureByName(name);
			return Response.ok(picture.getProperties())
					.status(200)
					.build();
		}
		else
		{
			return Response.ok().status(500).build();
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
		PegiClassification pegi = ProductDatabase.findPegiByName(name);
		if(pegi == null){
			pegi = new PegiClassification();
			pegi.setName(name);
			pegi.setImg(img);

			ProductDatabase.insertPegi(pegi);

			pegi = ProductDatabase.findPegiByName(name);
			return Response.ok(pegi.getProperties())
					.status(200)
					.build();
		}
		else
		{
			return Response.ok().status(500).build();
		}
	}





}