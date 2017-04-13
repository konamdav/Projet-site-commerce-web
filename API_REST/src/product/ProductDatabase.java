package product;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import generic.Database;
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
}
