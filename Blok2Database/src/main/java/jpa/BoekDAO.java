package jpa;

import lombok.extern.log4j.Log4j2;
import javax.persistence.*;
import java.util.List;

@Log4j2
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
            log.info(String.format("%s is opgeslagen", boek.getTitel()));
        } catch (Exception e) {
            log.warn(e.getClass().getSimpleName() + " : " + e.getMessage());
            em.getTransaction().rollback();
        }
    }

    public Boek zoek(int id) {
        try {
            Boek boek = em.find(Boek.class, id);
            return boek;
        } catch (NullPointerException e) {
            log.warn("Boek met id %s bestaat niet", id);
            return null;
        }

    }

    public List<Boek> alleBoekenLijst () {
        //JPQL (Java persistence query language) taal daarom geen * bij selecteer all
        return em.createNamedQuery("BoekEntity.zoekAllen", Boek.class).getResultList();
        //return em.createQuery("SELECT b FROM Boek b", Boek.class).getResultList();
    }

    //Beter een entity krijgen volgens Bram. Dit regelen met een service zeg ik
    public Boek updateTitel (int id, String name) {
        try {
            em.getTransaction().begin();
            Boek boek = zoek(id);
            boek.setTitel(name);
            em.merge(boek);
            em.getTransaction().commit();
            log.info(String.format("%s is opgeslagen", boek.getTitel()));
            return boek;
        } catch (Exception e) {
            log.warn(e.getClass().getSimpleName() + " : " + e.getMessage());
            em.getTransaction().rollback();
            return null;
        }
    }

    //Beter een entity krijgen volgens Bram. Dit regelen met een service zeg ik
    public void verwijderen(int id) {
        try {
            em.getTransaction().begin();
            Boek boek = zoek(id);
            em.remove(boek);
            em.getTransaction().commit();
            log.info(String.format("%s is verwijderd", boek.getTitel()));
        } catch (Exception e) {
            log.warn(e.getClass().getSimpleName() + " : " + e.getMessage());
            em.getTransaction().rollback();
        }
    }

    public void opslaanGenreBijBoek(Boek boek, Genre genre) {
        try {
            em.getTransaction().begin();
            boek.setGenres(genre);
            em.merge(boek);
            em.getTransaction().commit();
            log.info(String.format("Het genre %s is opgeslagen bij boek %s", genre.getOmschrijving(), boek.getTitel()));
        } catch (Exception e) {
            log.warn(e.getClass().getSimpleName() + " : " + e.getMessage());
            em.getTransaction().rollback();
        }
    }

}
