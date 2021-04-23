package nl.marktplaats.gedeeld;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import nl.marktplaats.data.BezorgwijzeEnum;

import javax.persistence.*;

//@TODO
public class Fabriek {
    private final EntityManager em;
    BezorgwijzeDao bezorgwijzeDao;

    public Fabriek() {
        this.em = Persistence.createEntityManagerFactory("Productie").createEntityManager();
        this.bezorgwijzeDao = new BezorgwijzeDao(em);
    }

    public void enumsInDatabase() {
        for (BezorgwijzeEnum bezorgwijze : BezorgwijzeEnum.values()) {
            bezorgwijzeDao.opslaan(
                    Bezorgwijze.builder().id(bezorgwijze.getId()).omschrijving(bezorgwijze.getOmschrijving()).build());
        }
    }

    @Entity @Data @NoArgsConstructor @AllArgsConstructor @Builder
    @Table(name = "Bezorgwijze")
    private static class Bezorgwijze {
        @Id
        private int id;
        private String omschrijving;
    }

    @Log4j2
    private static class BezorgwijzeDao{
        //@TODO nog mee laten geven
        private final EntityManager em;

        public BezorgwijzeDao(EntityManager em) {
            this.em = em;
        }

        public void opslaan (Bezorgwijze bezorgwijze) {
            try {
                em.getTransaction().begin();
                em.persist(bezorgwijze);
                em.getTransaction().commit();
                log.info(String.format("Bezorgwijze %s is opgeslagen in de database voor gebruik", bezorgwijze.omschrijving));
            } catch (Exception e) {
                log.warn(e.getClass().getSimpleName() + " : " + e.getMessage());
                em.getTransaction().rollback();
            }
        }
    }

}
