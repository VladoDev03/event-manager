package com.example.SpringProject.configuration;

import com.example.SpringProject.BaseEntity.BaseEntity;
import com.example.SpringProject.Creator.Creator;
import com.example.SpringProject.Event.Event;
import com.example.SpringProject.EventOnLocation.EventOnLocation;
import com.example.SpringProject.Guest.Guest;
import com.example.SpringProject.Location.Location;
import com.example.SpringProject.Media.Media;
import com.example.SpringProject.Reservation.Reservation;
import com.example.SpringProject.Review.Review;
import com.example.SpringProject.User.User;
import org.hibernate.SessionFactory;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SessionFactoryUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            Configuration configuration = new Configuration();

            configuration.addAnnotatedClass(BaseEntity.class);
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Creator.class);
            configuration.addAnnotatedClass(Guest.class);
            configuration.addAnnotatedClass(Event.class);
            configuration.addAnnotatedClass(Location.class);
            configuration.addAnnotatedClass(EventOnLocation.class);
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
