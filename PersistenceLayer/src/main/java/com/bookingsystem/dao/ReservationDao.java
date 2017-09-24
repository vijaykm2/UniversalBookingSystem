package com.bookingsystem.dao;

import com.bookingsystem.entities.Reservation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vijay on 2/27/17.
 */
final public class ReservationDao extends BaseEntityDao {


    public ReservationDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void saveOrUpdate(Reservation reservation){
        this.saveOrUpdate(reservation, false);
    }

    public Reservation findReservationById(String id){

        Map<String , Object> paramMap = new HashMap<> ();
        paramMap.put("id", id);
        return executeQueryUniqueResult("from Reservation where reservationId = :id", paramMap, Reservation.class );
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Closing session factory!!");
        this.getSessionFactory().getCurrentSession().close();
        this.getSessionFactory().close();
    }
}
