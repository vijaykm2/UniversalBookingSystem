package com.bookingsystem.daoimpl;

import com.bookingsystem.entities.Reservation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.annotation.PreDestroy;

/**
 * Created by vijay on 2/27/17.
 */
public class ReservationDao {

    private SessionFactory sessionFactory;

    public ReservationDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveOrUpdate(Reservation reservation, boolean isWithinSession){
        Session session =sessionFactory.getCurrentSession();
        try{

            if(reservation.getId() == null  ){
                session.save(reservation);
            } else {
                if(isWithinSession ) {
                    session.merge(reservation);
                } else session.update(reservation);
            }

        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    public void saveOrUpdate(Reservation reservation){
        this.saveOrUpdate(reservation, false);
    }

    public Reservation findReservationById(String id){
        Session session =sessionFactory.getCurrentSession();
        Query<Reservation> query = session.createQuery("from Reservation where reservationId = :id ", Reservation.class);
        query.setParameter("id", id);
        return query.uniqueResult();
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Closing session factory!!");
        sessionFactory.getCurrentSession().close();
        sessionFactory.close();
    }
}
