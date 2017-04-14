package user;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import command.Command;
import generic.Database;
import product.Product;

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
	
	public static List findCommands(int id_user)
	{
		Session session = init(Command.class).openSession();
		return session.createCriteria(Command.class)
				.add(Restrictions.eq("id_user", id_user))
				.list();		
	}

	public static void insertUser(User user)
	{
		Session session = init(User.class).openSession();
		session.beginTransaction();
		
		session.save(user);
		session.getTransaction().commit();
	}

	public static Command findCommand(int id_command) {
		
		Session session = init(Command.class).openSession();
		session.beginTransaction();

		Command cmd = (Command) session.get(Command.class,id_command);
		session.getTransaction().commit();
		return cmd;
	}
}
