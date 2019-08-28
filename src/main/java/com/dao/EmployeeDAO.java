package com.dao;

import com.entity.Employee;
import com.entity.PhoneNumber;
import com.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeDAO {

        public Employee findById(int id) {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Employee employee = session.get(Employee.class, id);
            session.close();
            return employee;
        }

        public PhoneNumber findPhoneNumbersById(int id) {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            PhoneNumber phoneNumber = session.get(PhoneNumber.class, id);
            session.close();
            return phoneNumber;
        }

        public void save(Employee employee) {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.save(employee);
            tx1.commit();
            session.close();
        }

        public void update(Employee employee) {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.update(employee);
            tx1.commit();
            session.close();
        }

        public void delete(Employee employee) {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.delete(employee);
            tx1.commit();
            session.close();
        }

        public List<Employee> getAllEmployee() {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            List list = session.createQuery(
                    "select distinct employee From Employee as employee left join fetch employee.position " +
                            "left join fetch employee.phoneNumbers ").list();
            session.close();
            return list;
        }
}
