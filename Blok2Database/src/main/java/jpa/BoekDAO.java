package jpa;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

public class BoekDAO {

    private final EntityManager em;

    public BoekDAO(EntityManager em) {
        this.em = em;
    }

    public void opslaan(Boek boek) {
        try {

            em.getTransaction().begin();
            em.persist(boek);
            em.getTransaction().commit();
        } catch (Exception e) {
            //LOGGER.debug(e.getClass().getSimpleName() + " : " + e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.clear();
        }
    }

    public Boek zoek(long id) {
        return em.find(Boek.class, id);
    }

    public List<Boek> alleBoekenLijst () {
        //JPQL (Java persistence query language) taal daarom geen * bij selecteer all
        return em.createQuery("SELECT b FROM Boek b", Boek.class).getResultList();    }

}
