package com.bookingsystem.dao;

import com.bookingsystem.entities.BaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Map;

/**
 * Created by vijay on 4/1/17.
 */
public abstract class BaseEntityDao {


    private SessionFactory sessionFactory;

    public BaseEntityDao(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return  sessionFactory;
    }

    public void saveOrUpdate(BaseEntity baseEntity, boolean isWithinSession) {
        Session session =sessionFactory.getCurrentSession();
        try{

            if(baseEntity.getId() == null  ){
                session.save(baseEntity);
            } else {
                if(isWithinSession ) {
                    session.merge(baseEntity);
                } else session.update(baseEntity);
            }

        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    public <E extends BaseEntity> List<E> executeQueryList(String queryString, Map<String, Object> paramsMap, Class<E> entityClass ) {
        Query<E> query = sessionFactory.getCurrentSession().createQuery(queryString, entityClass);
        for (String param : paramsMap.keySet()) {
            query.setParameter(param, paramsMap.get(param));
        }
        return query.list();
    }

    public <E extends BaseEntity> E executeQueryUniqueResult(String queryString, Map<String, Object> paramsMap, Class<E> entityClass ) {
        Query<E> query = sessionFactory.getCurrentSession().createQuery(queryString, entityClass);
        for (String param : paramsMap.keySet()) {
            query.setParameter(param, paramsMap.get(param));
        }
        return query.uniqueResult();
    }

}
