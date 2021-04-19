package jpa;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

public class BoekDAO {

    public static final EntityManager em =
            Persistence.createEntityManagerFactory("TestNaam").createEntityManager();

    public void opslaan(Boek boek) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(boek);
        transaction.commit();
    }

    public Boek zoek(long id) {
        return em.find(Boek.class, id);
    }

    public List<Boek> alleBoekenLijst () {
        //JPQL (Java persistence query language) taal daarom geen * bij selecteer all
        return em.createQuery("SELECT b FROM Boek b", Boek.class).getResultList();    }

}
