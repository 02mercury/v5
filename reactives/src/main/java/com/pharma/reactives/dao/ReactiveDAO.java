package com.pharma.reactives.dao;

import com.pharma.reactives.models.Reactive;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
public class ReactiveDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public ReactiveDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    /** SELECT ALL REACTIVES FROM DATABASE */
    @Transactional(readOnly = true)
    public List<Reactive> getAll(){
        Session session = sessionFactory.getCurrentSession();

        // hibernate cod
        List<Reactive> reactives = session.createQuery("select reactive FROM Reactive reactive", Reactive.class)
                .getResultList();

        return reactives;
    }

    /** SELECT ONE OF REACTIVES FROM DATABASE */
    @Transactional(readOnly = true)
    public Reactive getById(int id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(Reactive.class, id);
    }

    /** SAVE REACTIVE IN DATABASE */
    @Transactional
    public void save(Reactive reactive){
        Session session = sessionFactory.getCurrentSession();
        session.save(reactive);
    }

    /** UPDATE REACTIVE FROM DATABASE */
    @Transactional
    public void update(int id, Reactive updatedReactive){
        Session session = sessionFactory.getCurrentSession();

        Reactive reactiveToBeUpdated = session.get(Reactive.class, id);

        reactiveToBeUpdated.setStock(updatedReactive.getStock());
        reactiveToBeUpdated.setPrice(updatedReactive.getPrice());

    }

    /** DELETE REACTIVE FROM DATABASE */
    @Transactional
    public void delete(int id){
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Reactive.class, id));
    }
}
