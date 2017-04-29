package user;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import command.Command;
import command.LineCommand;
import generic.Database;
import product.Product;

public class UserDatabase extends Database{
	private static Session session  = Database.session;
	
	public static User findUserByID(int id)
	{
		session.beginTransaction();
		User usr = (User) session.get(User.class,id);
		session.getTransaction().commit();
		return usr;

	}

	public static User findByCriteria(String username, String password)
	{
		
		User resUser = (User) session.createCriteria(User.class)
				.add(Restrictions.and(
						Restrictions.eq("username", username),
						Restrictions.eq("password", password)
						)).uniqueResult();
		return resUser;
	}
	
	public static User findByCriteria(String username)
	{
		
		User resUser = (User) session.createCriteria(User.class)
				.add(Restrictions.eq("username", username))
				.uniqueResult();
		return resUser;
	}
	
	public static List findCommands(int id_user)
	{
		
		return session.createCriteria(Command.class)
				.add(Restrictions.eq("id_user", id_user))
				.list();		
	}

	public static void saveUser(User user)
	{
		session.beginTransaction();
		
		session.save(user);
		session.getTransaction().commit();
	}
	
	public static void saveCommand(Command command)
	{
		session.beginTransaction();
		session.save(command);
		session.getTransaction().commit();
	}
	
	public static void saveLineCommand(LineCommand lineCommand)
	{
		session.beginTransaction();
		session.save(lineCommand);
		session.getTransaction().commit();
	}

	public static Command findCommand(int id_command) {
		
		session.beginTransaction();
		Command cmd = (Command) session.get(Command.class,id_command);
		session.getTransaction().commit();
		return cmd;
	}
	
public static LineCommand findLineCommand(int id_linecommand) {
		
		session.beginTransaction();
		LineCommand linecmd = (LineCommand) session.get(LineCommand.class,id_linecommand);
		session.getTransaction().commit();
		return linecmd;
	}
	
	
}
