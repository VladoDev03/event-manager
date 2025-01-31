package com.example.event_manager.dao;

import com.example.event_manager.configuration.SessionFactoryUtil;
import com.example.event_manager.dto.CreateMediaDto;
import com.example.event_manager.entity.Media;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class MediaDao {
    public static Media getMediaById(long id) {
        Media media;

        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            media = session.get(Media.class, id);
            transaction.commit();
        }

        return media;
    }

    public static void createMedia(CreateMediaDto media) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Media mediaToAdd = new Media(media.getUrl(), media.getPublicId(), EventDao.getEventById(media.getEventId()));
            Transaction transaction = session.beginTransaction();
            session.save(mediaToAdd);
            transaction.commit();
        }
    }

    public static void deleteMedia(Media media) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(media);
            transaction.commit();
        }
    }
}
