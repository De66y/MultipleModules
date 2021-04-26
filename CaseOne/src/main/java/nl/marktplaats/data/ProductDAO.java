package nl.marktplaats.data;

import lombok.extern.log4j.Log4j2;

import javax.persistence.EntityManager;

@Log4j2
public class ProductDAO {
    private final EntityManager em;

    public ProductDAO(EntityManager em) {
        this.em = em;
    }
}
