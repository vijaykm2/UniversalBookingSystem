package com.bookingsystem.dao;

import com.bookingsystem.entities.Location;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by vijay on 3/31/17.
 */
public class LocationDao {
    private SessionFactory sessionFactory;

    public LocationDao(SessionFactory sessionFactory) {
        this.sessionFactory =sessionFactory;
    }

    public void saveOrUpdate(Location airport, boolean isWithinSession) {
        Session session =sessionFactory.getCurrentSession();
        if(airport.getId() == null ){
            session.save(airport);
        }else {
            if(isWithinSession){
                session.merge(airport);
            } else {
                session.update(airport);
            }

        }
    }

    public List<Location> findLocationsByCity(String city) {
        Session session = sessionFactory.getCurrentSession();
        Query<Location> query = session.createQuery("from Location where city = :city", Location.class);
        return query.list();
    }

}
