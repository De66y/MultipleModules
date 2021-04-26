package nl.marktplaats.data;

import lombok.extern.log4j.Log4j2;
import nl.marktplaats.gedeeld.domeinmodel.Bezorgwijze;
import nl.marktplaats.gedeeld.domeinmodel.Gebruiker;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Log4j2
public class BezorgwijzeDAO {

    private final EntityManager em;

    public BezorgwijzeDAO(EntityManager em) {
        this.em = em;
    }

    public void opslaan(Bezorgwijze bezorgwijze) {
        try {
            em.getTransaction().begin();
            em.persist(bezorgwijze);
            em.getTransaction().commit();
            log.info(String.format("Bezorgwijze %s is is opgeslagen in de database", bezorgwijze.getId()));
        } catch (Exception e) {
            log.warn(e.getClass().getSimpleName() + " : " + e.getMessage());
            em.getTransaction().rollback();
        }
    }

    public List<Bezorgwijze> alleBezorgwijzen() {
        return em.createNamedQuery("BezorgwijzeEntity.alleBezorgwijzen", Bezorgwijze.class).getResultList();
    }
}
