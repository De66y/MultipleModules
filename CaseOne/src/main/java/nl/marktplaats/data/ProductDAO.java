package nl.marktplaats.data;

import lombok.extern.log4j.Log4j2;
import nl.marktplaats.gedeeld.domeinmodel.Gebruiker;
import nl.marktplaats.gedeeld.domeinmodel.Product;

import javax.persistence.EntityManager;

@Log4j2
public class ProductDAO {
    private final EntityManager em;

    public ProductDAO(EntityManager em) {
        this.em = em;
    }

    public void opslaan(Product product) {
        try {
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();
            log.info(String.format("Product met naam: %s is opgeslagen.", product.getNaam()));
        } catch (Exception e) {
            e.printStackTrace();
            //log.warn(e.getClass().getSimpleName() + " : " + e.getMessage());
            em.getTransaction().rollback();
        }
    }
}
