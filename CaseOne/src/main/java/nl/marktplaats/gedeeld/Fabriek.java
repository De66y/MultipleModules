package nl.marktplaats.gedeeld;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import nl.marktplaats.data.BezorgwijzeEnum;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@TODO
public class Fabriek {
    private final EntityManager em;
    BezorgwijzeDao bezorgwijzeDao;

    public Fabriek() {
        this.em = Persistence.createEntityManagerFactory("Productie").createEntityManager();
        this.bezorgwijzeDao = new BezorgwijzeDao(em);
    }

    public EntityManager getEm() {
        return em;
    }

    public void enumsInDatabase() {
        for (BezorgwijzeEnum bezorgwijze : BezorgwijzeEnum.values()) {
            bezorgwijzeDao.opslaan(
                    Bezorgwijze.builder()
                            .id(bezorgwijze.getId())
                            .omschrijving(bezorgwijze.getOmschrijving()).build());
        }
    }

    public List<Object> opslaanVanEnumNaarEntity(List<BezorgwijzeEnum> bezorgwijzeEnums) {
        List<Object> bezorgwijzen = new ArrayList<>();
        for (BezorgwijzeEnum bezorgwijze : bezorgwijzeEnums) {
            bezorgwijzen.add(
            Bezorgwijze.builder()
                    .id(bezorgwijze.getId())
                    .omschrijving(bezorgwijze.getOmschrijving()).build());
        }
        return bezorgwijzen;
    }

    public List<BezorgwijzeEnum> opslaanVanEntitynaarEnum(List<Bezorgwijze> bezorgwijzen) {
        List<BezorgwijzeEnum> bezorgwijzeEnums = new ArrayList<>();
        for (Bezorgwijze bezorgwijze : bezorgwijzen) {
            for (BezorgwijzeEnum bezorgwijzeEnum : BezorgwijzeEnum.values()) {
                if (bezorgwijze.id == bezorgwijzeEnum.getId()) bezorgwijzeEnums.add(bezorgwijzeEnum);
            }
        }
        return bezorgwijzeEnums;
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
        private final EntityManager em;

        public BezorgwijzeDao(EntityManager em) {
            this.em = em;
        }

        public Bezorgwijze zoek(int id) {
            return em.find(Bezorgwijze.class, id);
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
