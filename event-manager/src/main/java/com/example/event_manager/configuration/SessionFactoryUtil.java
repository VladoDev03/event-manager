package com.example.event_manager.configuration;

import com.example.event_manager.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SessionFactoryUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();

            configuration.addAnnotatedClass(BaseEntity.class);
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Event.class);
            configuration.addAnnotatedClass(Reservation.class);
            configuration.addAnnotatedClass(Review.class);
            configuration.addAnnotatedClass(Media.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }

        return sessionFactory;
    }
}
