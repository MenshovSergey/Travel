package main.java.ru.meeting.db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.File;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    /**
     * Создание фабрики
     * @return {@link org.hibernate.SessionFactory}
     * @throws org.hibernate.HibernateException
     */
    static {
        try {
            //creates the session factory from hibernate.cfg.xml
            File file = new File("src/main/java/ru/meeting/db/hibernate.cfg.xml");
            System.out.println("file = " + file.getName());
            Configuration configuration = new Configuration().configure(file);
            System.out.println("corfig="+configuration);
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Получить фабрику сессий
     * @return {@link org.hibernate.SessionFactory}
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}