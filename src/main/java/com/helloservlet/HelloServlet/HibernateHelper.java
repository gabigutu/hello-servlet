package com.helloservlet.HelloServlet;

import com.helloservlet.HelloServlet.model.UserHibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateHelper {

    private static HibernateHelper hibernateHelper;
    private Session session;

    public static HibernateHelper getInstance() {
        if (hibernateHelper == null) {
            hibernateHelper = new HibernateHelper();
        }
        return hibernateHelper;
    }

    public HibernateHelper() {
        Configuration configuration = new Configuration();
        configuration.configure();
//        Properties properties = new Properties();
//        properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
//        properties.setProperty(Environment.HBM2DDL_AUTO,"update");
//        properties.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
//        properties.setProperty(Environment.USER, "root"); // System.getenv("ANC_USER")
//        properties.setProperty(Environment.PASS, "D@t@tables");
//        properties.setProperty(Environment.URL, "jdbc:mysql://localhost:3306/ancdb");
//        configuration = configuration.setProperties(properties);
        configuration.addAnnotatedClass(UserHibernate.class);
        try {
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            if (sessionFactory == null) {
                System.out.println("Error session factory!");
            } else {
                System.out.println("Am deschis session!");
            }
            session = sessionFactory.openSession();
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Exceptie: " + exception.getMessage());
        }

    }

    public Session getSession() {
        return session;
    }
}
