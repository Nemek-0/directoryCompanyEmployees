package com.dao;

import com.entity.Employee;
import com.entity.PhoneNumber;
import com.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PhoneNumberDAO {
    public PhoneNumber findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        PhoneNumber phoneNumber = session.get(PhoneNumber.class, id);
        session.close();
        return phoneNumber;
    }

    public void save(PhoneNumber phoneNumber) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(phoneNumber);
        tx1.commit();
        session.close();
    }

    public void update(PhoneNumber phoneNumber) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(phoneNumber);
        tx1.commit();
        session.close();
    }

    public void delete(PhoneNumber phoneNumber) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(phoneNumber);
        tx1.commit();
        session.close();
    }

    public List getAllPhoneNumber() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List list = session.createQuery(
                "From PhoneNumber ").list();
        session.close();
        return list;
    }

}
