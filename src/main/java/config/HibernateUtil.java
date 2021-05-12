package config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import model.Customer;
import model.Demand;
import model.Distance;
import model.Vehicle;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;
import java.util.logging.Logger;

import static org.hibernate.cfg.AvailableSettings.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static final Logger logger = CustomLogger.getLogger(HibernateUtil.class);

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                var properties = new Properties();

                properties.put(DRIVER, System.getenv("database_driver"));
                properties.put(URL, System.getenv("database_url"));
                properties.put(USER, System.getenv("database_username"));
                properties.put(PASS, System.getenv("database_password"));
                properties.put(DIALECT, System.getenv("database_dialect"));
                properties.put(SHOW_SQL, System.getenv("database_show_sql"));
                properties.put(CURRENT_SESSION_CONTEXT_CLASS, System.getenv("database_context"));
                properties.put(HBM2DDL_AUTO, System.getenv("database_ddl"));

                var configuration = new Configuration();

                configuration.setProperties(properties);
                configuration.addAnnotatedClass(Customer.class);
                configuration.addAnnotatedClass(Demand.class);
                configuration.addAnnotatedClass(Distance.class);
                configuration.addAnnotatedClass(Vehicle.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                    configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception exception) {
                logger.severe(exception.getMessage());
            }
        }

        return sessionFactory;
    }
}
