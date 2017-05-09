package com.xfh.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Created by Fog on 2017/4/10.
 */
public class HibernateUtil {
    private static Session session;
    private static SessionFactory sessionFactory;

    static {
        Configuration configuration=new Configuration().configure();
        StandardServiceRegistryBuilder ssrb=new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        StandardServiceRegistry ssr=ssrb.build();
        sessionFactory=new AnnotationConfiguration().configure().buildSessionFactory(ssr);
    }

    public static SessionFactory getSessionFactoryon(){
        return sessionFactory;
    }
    public static Session getSession(){
        if(session!=null)
            return session;
        return session=sessionFactory.openSession();
    }

    public  static void clossSession(){
        if(session!=null)
            session.close();
    }

}
