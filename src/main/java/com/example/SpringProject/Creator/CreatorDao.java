package com.example.SpringProject.Creator;

import com.example.SpringProject.configuration.SessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CreatorDao {
    public static void createCreator(Creator creator){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(creator);
            transaction.commit();
        }
    }
    public static Creator getCreatorById(long id){
        Creator creator;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            creator = session.get(Creator.class,id);
            transaction.commit();
        }
        return creator;
    }
    public static void updateCreator(Creator creator){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(creator);
            transaction.commit();
        }
    }
    public static void deleteCreator(Creator creator){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(creator);
            transaction.commit();
        }
    }
    public static List<Creator> getCreators(){
        List<Creator> creators;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            creators = session.createQuery("SELECT c FROM Creator c",Creator.class)
                    .getResultList();
            transaction.commit();
        }
        return  creators;
    }
}
