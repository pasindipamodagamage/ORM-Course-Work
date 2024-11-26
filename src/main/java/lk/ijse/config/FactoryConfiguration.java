package lk.ijse.config;

import lk.ijse.Entity.*;
import lk.ijse.Entity.Login;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Configuration configuration = new Configuration();

        Properties property = new Properties();
        try {
            property.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("lib/hibernate.properties"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        configuration.setProperties(property);
        configuration.addAnnotatedClass(Student.class);
        configuration.addAnnotatedClass(Course.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Payment.class);
        configuration.addAnnotatedClass(Student_Course.class);
        configuration.addAnnotatedClass(Login.class);

        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() {
        return (factoryConfiguration == null) ?
                factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
