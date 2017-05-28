package user;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import command.Command;
import command.LineCommand;
import generic.Database;
import product.Product;

public class UserDatabase {


	public static User findUserByID(int id, Session session)
	{
		session.beginTransaction();
		User usr = (User) session.get(User.class,id);
		session.getTransaction().commit();
		return usr;

	}

	public static User findByCriteria(String username, String password, Session session)
	{

		User resUser = (User) session.createCriteria(User.class)
				.add(Restrictions.and(
						Restrictions.eq("username", username),
						Restrictions.eq("password", password)
						)).uniqueResult();
		return resUser;
	}

	public static User findByCriteria(String username, Session session)
	{

		User resUser = (User) session.createCriteria(User.class)
				.add(Restrictions.eq("username", username))
				.uniqueResult();
		return resUser;
	}

	public static List findCommands(int id_user, Session session)
	{

		return session.createCriteria(Command.class)
				.add(Restrictions.eq("id_user", id_user))
				.list();		
	}

	public static void saveUser(User user, Session session)
	{
		session.beginTransaction();

		session.save(user);
		session.getTransaction().commit();
	}

	public static void saveCommand(Command command, Session session)
	{
		session.beginTransaction();
		session.save(command);
		session.getTransaction().commit();
	}

	public static void saveLineCommand(LineCommand lineCommand, Session session)
	{
		session.beginTransaction();
		session.save(lineCommand);
		session.getTransaction().commit();
	}

	public static Command findCommand(int id_command, Session session) {

		session.beginTransaction();
		Command cmd = (Command) session.get(Command.class,id_command);
		session.getTransaction().commit();
		return cmd;
	}

	public static Command findLastCommand(int id_user, Session session) {

		return (Command) session.createCriteria(Command.class)
				.addOrder(Order.desc("id"))
				.add(Restrictions.eq("id_user", id_user))
				.setMaxResults(1)
				.uniqueResult();		
	}

	public static LineCommand findLineCommand(int id_linecommand, Session session) {

		session.beginTransaction();
		LineCommand linecmd = (LineCommand) session.get(LineCommand.class,id_linecommand);
		session.getTransaction().commit();
		return linecmd;
	}


}
