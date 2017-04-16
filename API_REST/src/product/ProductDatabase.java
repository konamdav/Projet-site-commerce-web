package product;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import console.Console;
import generic.Database;
import genre.Genre;
import publisher.Publisher;
import user.User;

public class ProductDatabase extends Database{
	public static Product findProductByID(int id)
	{
		Session session = init(Product.class).openSession();
		session.beginTransaction();

		Product product = (Product) session.get(Product.class,id);
		session.getTransaction().commit();
		return product;

	}
	
	public static Publisher findPublisherByName(String name)
	{
		Session session = init(Publisher.class).openSession();
		return (Publisher) session.createCriteria(Publisher.class)
				.add(Restrictions.eq("name", name))
				.uniqueResult();
		

	}
	
	public static List findAll()
	{
		Session session = init(Product.class).openSession();
		return session.createCriteria(Product.class).list();
		
	}
	
	public static void insertProduct(Product product)
	{
		Session session = init(Product.class).openSession();
		session.beginTransaction();
		
		session.save(product);
		session.getTransaction().commit();
	}

	public static void insertPublisher(Publisher publisher) {
		Session session = init(Publisher.class).openSession();
		session.beginTransaction();
		
		session.save(publisher);
		session.getTransaction().commit();
	}

	public static void insertGenre(Genre genre) {
		Session session = init(Genre.class).openSession();
		session.beginTransaction();
		
		session.save(genre);
		session.getTransaction().commit();
		
	}

	public static Genre findGenreByName(String name) {
		Session session = init(Genre.class).openSession();
		return (Genre) session.createCriteria(Genre.class)
				.add(Restrictions.eq("name", name))
				.uniqueResult();
	}

	public static void insertConsole(Console console) {
		Session session = init(Console.class).openSession();
		session.beginTransaction();
		
		session.save(console);
		session.getTransaction().commit();
		
	}

	public static Console findConsoleByName(String name) {
		Session session = init(Console.class).openSession();
		return (Console) session.createCriteria(Console.class)
				.add(Restrictions.eq("name", name))
				.uniqueResult();
	}

	public static List findAllSameProducts(Product product) {

		Session session = init(Product.class).openSession();
		return session.createCriteria(Product.class)
				.add(Restrictions.ne("id", product.getId()))
				.createAlias("videogame", "vg")
				//.add(Restrictions.eq("vg.id_publisher", product.getVideogame().getPublisher().getId()))
				.list();
		
	}
}
