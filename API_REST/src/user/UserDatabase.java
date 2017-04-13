package user;

import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import generic.Database;

public class UserDatabase extends Database{
	public static User findUserByID(int id)
	{
		Session session = init(User.class).openSession();
		session.beginTransaction();

		User usr = (User) session.get(User.class,id);
		session.getTransaction().commit();
		return usr;

	}

	public static User findByCriteria(String username, String password)
	{
		Session session = init(User.class).openSession();

		User resUser = (User) session.createCriteria(User.class)
				.add(Restrictions.and(
						Restrictions.eq("username", username),
						Restrictions.eq("password", password)
						)).uniqueResult();
		return resUser;
	}
	
	public static User findByCriteria(String username)
	{
		Session session = init(User.class).openSession();

		User resUser = (User) session.createCriteria(User.class)
				.add(Restrictions.eq("username", username))
				.uniqueResult();
		return resUser;
	}

	public static void insertUser(User user)
	{
		Session session = init(User.class).openSession();
		session.beginTransaction();
		
		session.save(user);
		session.getTransaction().commit();
	}
}
