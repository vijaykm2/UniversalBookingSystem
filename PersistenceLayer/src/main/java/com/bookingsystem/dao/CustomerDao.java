package com.bookingsystem.dao;

import com.bookingsystem.entities.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vijay on 2/27/17.
 */
public class CustomerDao extends BaseEntityDao {
    private SessionFactory sessionFactory;

    public CustomerDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Customer findPassengerById(String id){
        /*Session session =this.getSessionFactory().getCurrentSession();
        Query<Customer> query = session.createQuery("from Passengers where id = :id ", Customer.class);
        query.setParameter("id", id);
        return query.uniqueResult();*/

        Map<String , Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        return executeQueryUniqueResult("from Passengers where id = :id", paramMap, Customer.class);
    }

    public List<Customer> findPassengersByReservationId(String id){

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        return executeQueryList("select pax from Customer as pax inner join pax.reservation as res where  res.reservationId = :id ", paramMap, Customer.class);

    }

    @PreDestroy
    public void destroy() {
        System.out.println("Closing session factory!!");
        this.getSessionFactory().getCurrentSession().close();
        this.getSessionFactory().close();
    }

}
