package com.bookingsystem.daoimpl;

import com.bookingsystem.entities.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.annotation.PreDestroy;
import java.util.List;

/**
 * Created by vijay on 2/27/17.
 */
public class CustomerDao {
    private SessionFactory sessionFactory;

    public CustomerDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveOrUpdate(Customer passenger){
        Session session =sessionFactory.getCurrentSession();
        try{

            session.saveOrUpdate(passenger);

        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    public Customer findPassengerById(String id){
        Session session =sessionFactory.getCurrentSession();
        Query<Customer> query = session.createQuery("from Passengers where id = :id ", Customer.class);
        query.setParameter("id", id);
        return query.uniqueResult();
    }

    public List<Customer> findPassengersByReservationId(String id){
        Session session =sessionFactory.getCurrentSession();
        Query<Customer> query = session.createQuery("select pax from Customer as pax inner join pax.reservation as res where  res.reservationId = :id ", Customer.class);
        query.setParameter("id", id);
        return query.list();

    }

    @PreDestroy
    public void destroy() {
        System.out.println("Closing session factory!!");
        sessionFactory.getCurrentSession().close();
        sessionFactory.close();
    }

}
