package nl.marktplaats.data;

import lombok.extern.log4j.Log4j2;
import nl.marktplaats.gedeeld.domeinmodel.Product;
import javax.persistence.EntityManager;
import java.util.List;

@Log4j2
public class ProductDAO {
    private final EntityManager em;

    public ProductDAO(EntityManager em) {
        this.em = em;
    }

    public Product zoek(int id) {
        try {
            Product product = em.find(Product.class, id);
            return product;
        } catch (NullPointerException e) {
            log.warn("Product met id %s bestaat niet", id);
            return null;
        }
    }
    public void verwijderProduct(Product product) {
        try {
            em.getTransaction().begin();
            em.remove(product);
            em.getTransaction().commit();
            log.info(String.format("Product met naam: %s is verwijderd.", product.getNaam()));
        } catch (Exception e) {
            e.printStackTrace();
            log.warn(e.getClass().getSimpleName() + " : " + e.getMessage());
            em.getTransaction().rollback();
        }
    }
    //@TODO VOOR VERLANGLIJSTJE
    public List<Product> alleProducten() {
        return em.createNamedQuery("ProductEntity.geefAlleProducten", Product.class).getResultList();
    }
}
