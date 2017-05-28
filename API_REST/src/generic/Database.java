package generic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.mysql.jdbc.Driver;

import user.User;

public class Database {
	public static Map<Session, SessionFactory> map= new HashMap<Session, SessionFactory>();
	public static Session session  = init();
	
	public static Session init() {
		Configuration configuration = new Configuration().configure();		
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).buildServiceRegistry();

		SessionFactory sessionFactory = configuration
				.buildSessionFactory(serviceRegistry);

		Session session= sessionFactory.openSession();
		map.put(session, sessionFactory);
		return session;
	}
	
	public static void close(Session session)
	{
		session.close();
		map.get(session).close();
		map.remove(session);
		
		
	}

}
