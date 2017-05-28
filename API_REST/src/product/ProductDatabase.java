package product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import console.Console;
import generic.Database;
import genre.Genre;
import publisher.Publisher;
import user.User;
import videogame.VideoGame;
import tag.Tag;
import pegi_classification.PegiClassification;
import picture.Picture;
import review.Review;

public  class ProductDatabase {
	//private  Session session  = Database.session;

	public static Product findProductByID(int id, Session session){
		
		session.beginTransaction();
		Product product = (Product) session.get(Product.class,id);
		session.refresh(product);
		session.getTransaction().commit();
		return product;

	}

	public static  User findUserByID(int id, Session session){
		session.beginTransaction();
		User user = (User) session.get(User.class,id);
		session.refresh(user);
		session.getTransaction().commit();
		return user;

	}
	
	public static  Picture findPictureByID(int id, Session session){
		session.beginTransaction();
		Picture picture = (Picture) session.get(Picture.class,id);
		session.refresh(picture);
		session.getTransaction().commit();
		return picture;

	}
	
	public static  Picture deletePictureByID(int id, Session session){
		session.beginTransaction();
		Picture picture = (Picture) session.get(Picture.class,id);
		session.delete(picture);
		session.getTransaction().commit();
		return picture;

	}




	public static  List researchProduct(ResearchedProduct rp, Session session)
	{
		String request = "select p from product.Product as p "; //join genre.Genre as g join videogame.VideoGame as v join publisher.Publisher as pub";//, pegi_classification.PegiClassification as peg ";
		if(!rp.videogame.equals("") || !rp.publisher.equals("") ||!rp.console.equals("") || rp.tags.size() >0 || rp.genres.size() >0 || rp.pegis.size() >0) 
		{
			request += " where ";
			if(!rp.videogame.equals("")) request += "p.videogame.name = '" + rp.videogame + "' AND ";
			if(!rp.console.equals("")) request += "p.console.name = '" + rp.console + "' AND ";
			if(!rp.publisher.equals("")) request +="p.videogame.publisher.name = '" + rp.publisher + "' AND ";
			for(int i = 0; i<rp.tags.size() ; i++){
				if(!rp.tags.get(i).equals("")) request += "(select g from tag.Tag as g where g.name = '" + rp.tags.get(i) + "') in elements(p.videogame.tags) AND ";
			}
			for(int i = 0; i<rp.pegis.size() ; i++){
				if(!rp.pegis.get(i).equals("")) request += "(select g from pegi_classification.PegiClassification as g where g.name = '" + rp.pegis.get(i) + "') in elements(p.videogame.classifications) AND ";
			}
			for(int i = 0; i<rp.genres.size() ; i++){
				if(!rp.genres.get(i).equals("")) request += "(select g from genre.Genre as g where g.name = '" + rp.genres.get(i) + "') in elements(p.videogame.genres) AND ";
			}
			request = request.substring(0, request.length()-5);
		}
		Query query = session.createQuery(request);
		List result = query.list();

		return result;
	}

	public static  List RandomProducts(Session session){
		
		String request = "select p from product.Product as p order by rand() "; 
		Query query = session.createQuery(request);
		List result = query.setMaxResults(3).list();
		return result;
	}

	public static  List BestProducts( Session session){

		List<Product> res = new ArrayList<Product>();
		List<Product> list =  session.createCriteria(Product.class)
				//.addOrder(Order.asc("note"))
				.list();

		Product p1 = null, p2 = null, p3 = null;
		if(!list.isEmpty()){
			int max = -1;
			for(Product p : list)
			{
				if(p.getNote() >= max)
				{
					max = p.getNote();
					p1 = p;
				}
			}

			res.add(p1);
			list.remove(p1);
		}
		if(!list.isEmpty())
		{
			int max = -1;
			for(Product p : list)
			{
				if(p.getNote() >= max)
				{
					max = p.getNote();
					p2 = p;
				}
			}

			res.add(p2);
			list.remove(p2);
		}


		if(!list.isEmpty())
		{
			int max = -1;
			for(Product p : list)
			{
				if(p.getNote() >= max)
				{
					max = p.getNote();
					p3 = p;
				}
			}

			res.add(p3);
			list.remove(p3);
		}

		return res;

	}



	public static  List researchProduct(String name, String genre, String publisher, String tag, String pegi, String console, Session session){
		
		String request = "select p.videogame from product.Product as p "; //join genre.Genre as g join videogame.VideoGame as v join publisher.Publisher as pub";//, pegi_classification.PegiClassification as peg ";
		if(!name.equals("") || !name.equals("") ||!genre.equals("")) 
		{
			request += " where ";
			if(!name.equals("")) request += "p.videogame.name LIKE '%" + name + "%' AND ";
			if(!genre.equals("")) request += "(select g from genre.Genre as g where g.name = '" + genre + "') in elements(p.videogame.genres) AND ";
			if(!publisher.equals("")) request +="p.videogame.publisher.name LIKE '%" + publisher + "%' AND ";
			if(!tag.equals("")) request += "(select g from tag.Genre as g where g.nameLIKE '%" + genre + "%') in elements(p.videogame.genres) AND ";
			if(!pegi.equals("")) request += "(select g from pegi_classification.PegiClassification as g where g.nameLIKE '%" + genre + "%') in elements(p.videogame.PegiClassification) AND ";
			if(!console.equals("")) request += "p.videogame.console.name LIKE '%" + console + "%' AND ";
			request = request.substring(0, request.length()-5);
		}
		Query query = session.createQuery(request);
		List result = query.list();

		return result;

	}



	public static  void deleteConsole(Console c, Session session)
	{
		session.beginTransaction();
		session.delete(c);
		session.getTransaction().commit();

	}

	public static  void deleteGenre(Genre c, Session session)
	{
		session.beginTransaction();
		session.delete(c);
		session.getTransaction().commit();

	}

	public static  void deletePublisher(Publisher c, Session session)
	{
		session.beginTransaction();
		session.delete(c);
		session.getTransaction().commit();

	}

	public static  void deleteProduct(Product c, Session session)
	{
		session.beginTransaction();
		session.delete(c);
		session.getTransaction().commit();

	}

	public static  void deleteVideoGame(VideoGame c, Session session)
	{
		session.beginTransaction();
		session.delete(c);
		session.getTransaction().commit();

	}

	public static  void deleteReview(Review c, Session session)
	{
		session.beginTransaction();
		session.delete(c);
		session.getTransaction().commit();

	}


	public static  Console findConsoleByID(int id, Session session)
	{
		session.beginTransaction();
		Console console = (Console) session.get(Console.class,id);
		session.refresh(console);
		session.getTransaction().commit();
		return console;

	}

	public static  Publisher findPublisherByID(int id, Session session)
	{
		session.beginTransaction();
		Publisher publisher = (Publisher) session.get(Publisher.class,id);
		session.refresh(publisher);
		session.getTransaction().commit();
		return publisher;

	}

	public static  Genre findGenreByID(int id, Session session)
	{
		session.beginTransaction();
		Genre genre = (Genre) session.get(Genre.class,id);
		session.refresh(genre);
		session.getTransaction().commit();
		return genre;

	}

	public static  Tag findTagByID(int id, Session session)
	{
		session.beginTransaction();
		Tag tag = (Tag) session.get(Tag.class,id);
		session.refresh(tag);
		session.getTransaction().commit();
		return tag;

	}

	public static  Review findReviewByID(int id, Session session)
	{
		session.beginTransaction();
		Review review = (Review) session.get(Review.class,id);
		session.refresh(review);
		session.getTransaction().commit();
		return review;

	}

	public static  Product findProductByKey(VideoGame videogame, Console console, Session session)
	{

		return (Product) session.createCriteria(Product.class)
				.add(Restrictions.eq("videogame", videogame))
				.add(Restrictions.eq("console", console))
				.uniqueResult();
	}

	public static  Review findReviewByKey(User user, Product product, Session session)
	{

		return (Review) session.createCriteria(Review.class)
				.add(Restrictions.eq("id_user", user.getId()))
				.add(Restrictions.eq("id_product", product.getId()))
				.uniqueResult();
	}

	public static  Publisher findPublisherByName(String name, Session session)
	{

		return (Publisher) session.createCriteria(Publisher.class)
				.add(Restrictions.eq("name", name))
				.uniqueResult();


	}

	public static  Tag findTagByName(String name, Session session)
	{
		session.beginTransaction();

		Tag tag = (Tag) session.createCriteria(Tag.class)
				.add(Restrictions.eq("name", name))
				.uniqueResult();

		session.getTransaction().commit();
		return tag;
	}

	public static  Picture findPictureByName(String name, Session session)
	{
		return (Picture) session.createCriteria(Picture.class)
				.add(Restrictions.eq("name", name))
				.uniqueResult();
	}

	public static  PegiClassification findPegiByName(String name, Session session)
	{

		return (PegiClassification) session.createCriteria(PegiClassification.class)
				.add(Restrictions.eq("name", name))
				.uniqueResult();
	}


	public static  VideoGame findVideoGameByID(int id, Session session)
	{
		session.beginTransaction();
		VideoGame videogame = (VideoGame) session.get(VideoGame.class,id);
		session.refresh(videogame);
		session.getTransaction().commit();
		return videogame;

	}

	public static  PegiClassification findPegiByID(int id, Session session)
	{
		session.beginTransaction();
		PegiClassification pegi = (PegiClassification) session.get(PegiClassification.class,id);
		session.refresh(pegi);
		session.getTransaction().commit();
		return pegi;

	}


	public static  VideoGame findVideoGameByName(String name, Session session)
	{
		return (VideoGame) session.createCriteria(VideoGame.class)
				.add(Restrictions.eq("name", name))
				.uniqueResult();

	}

	public static  Genre findGenreByName(String name, Session session) {

		return (Genre) session.createCriteria(Genre.class)
				.add(Restrictions.eq("name", name))
				.uniqueResult();
	}

	public static  Console findConsoleByName(String name, Session session) {
		return (Console) session.createCriteria(Console.class)
				.add(Restrictions.eq("name", name))
				.uniqueResult();
	}



	public static  List findAllProducts(Session session)
	{
		return session.createCriteria(Product.class).list();

	}
	
	
	public static  List findAllGenres(Session session)
	{
		return session.createCriteria(Genre.class).list();

	}

	public static  List findAllPublishers( Session session)
	{
		return session.createCriteria(Publisher.class).list();

	}

	public static  List findAllConsoles(Session session)
	{
		return session.createCriteria(Console.class).list();

	}

	public static  List findAllPegi(Session session)
	{
		return session.createCriteria(PegiClassification.class).list();

	}

	public static  List findAllVideoGames(Session session)
	{
		return session.createCriteria(VideoGame.class).list();

	}

	public static  List findAllTags(Session session)
	{
		return session.createCriteria(Tag.class).list();

	}

	
	public static  void insertProduct(Product product, Session session)
	{
		session.beginTransaction();

		session.save(product);
		session.getTransaction().commit();
	}

	public static  void insertTag(Tag tag, Session session)
	{
		session.beginTransaction();
		session.save(tag);
		session.getTransaction().commit();
	}


	public static  void insertTag(Tag tag, VideoGame vg, Session session)
	{
		session.beginTransaction();
		session.save(tag);
		//session.save(vg);
		session.getTransaction().commit();
	}

	public static  void insertVideoGame(VideoGame videoGame, Session session)
	{
		session.beginTransaction();

		session.save(videoGame);
		session.getTransaction().commit();
	}

	public static  void insertPublisher(Publisher publisher, Session session) {

		session.beginTransaction();

		session.save(publisher);
		session.getTransaction().commit();
	}

	public static  void insertGenre(Genre genre, Session session) {
		session.beginTransaction();

		session.save(genre);
		session.getTransaction().commit();

	}

	public static  void insertConsole(Console console, Session session) {
		session.beginTransaction();

		session.save(console);
		session.getTransaction().commit();

	}

	public static  void insertReview(Review review, Session session) {
		session.beginTransaction();
		session.save(review);
		session.getTransaction().commit();

	}

	public static  void insertPicture(Picture picture, Session session) {
		session.beginTransaction();
		session.save(picture);
		session.getTransaction().commit();

	}

	public static  void insertPegi(PegiClassification pegi, Session session) {
		session.beginTransaction();
		session.save(pegi);
		session.getTransaction().commit();

	}


}
