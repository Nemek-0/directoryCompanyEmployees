package com.dao;

import com.entity.Position;
import com.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PositionDAO {
    public Position findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Position position = session.get(Position.class, id);
        session.close();
        return position;
    }

    public void save(Position position) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(position);
        tx1.commit();
        session.close();
    }

    public void update(Position position) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(position);
        tx1.commit();
        session.close();
    }

    public void delete(Position position) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(position);
        tx1.commit();
        session.close();
    }

    public List getAllPosition() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List list = session.createQuery("From Position").list();
        session.close();
        return list;
    }
}
