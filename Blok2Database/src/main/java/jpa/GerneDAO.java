package jpa;

import lombok.extern.log4j.Log4j2;

import javax.persistence.EntityManager;

@Log4j2
public class GerneDAO {

    private final EntityManager em;

    public GerneDAO(EntityManager em) {
        this.em = em;
    }

    public void maakGernesAan() {
        opslaan(new Gerne("Fantasy"));
        opslaan(new Gerne("Romantiek"));
        opslaan(new Gerne("Avontuurlijk"));
        opslaan(new Gerne("Si-fi"));
    }

    public void opslaan(Gerne gerne) {
        try {
            em.getTransaction().begin();
            em.persist(gerne);
            em.getTransaction().commit();
            log.info(String.format("%s is opgeslagen", gerne.getOmschrijving()));
        } catch (Exception e) {
            log.warn(e.getClass().getSimpleName() + " : " + e.getMessage());
            em.getTransaction().rollback();
        }
    }
    public Gerne zoek(int id) {
        try {
            Gerne gerne = em.find(Gerne.class, id);
            return gerne;
        } catch (NullPointerException e) {
            log.warn("Boek met id %s bestaat niet", id);
            return null;
        }

    }
}
