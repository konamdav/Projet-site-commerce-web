package generic;

import java.sql.Connection;
import java.sql.DriverManager;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.mysql.jdbc.Driver;

import user.User;

public abstract class Database {

	
	protected static SessionFactory init(Class c) {
		Configuration configuration = new Configuration().configure();
		configuration.addAnnotatedClass(c);
		
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).buildServiceRegistry();

		SessionFactory sessionFactory = configuration
				.buildSessionFactory(serviceRegistry);

		return sessionFactory;
		
	}

}
