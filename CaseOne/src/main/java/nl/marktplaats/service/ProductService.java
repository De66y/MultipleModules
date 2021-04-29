package nl.marktplaats.service;

import lombok.extern.log4j.Log4j2;
import nl.marktplaats.data.ProductDAO;
import nl.marktplaats.gedeeld.domeinmodel.Gebruiker;
import nl.marktplaats.gedeeld.domeinmodel.Product;
import javax.persistence.NoResultException;

@Log4j2
public class ProductService {
    private ProductDAO productDAO;
    private GebruikersService gebruikersService;

    public ProductService(ProductDAO productDAO, GebruikersService gebruikersService) {
        this.productDAO = productDAO;
        this.gebruikersService = gebruikersService;
    }

    //Methodes
    public void productTeKoopAanbieden(Gebruiker gebruiker, Product product) {
        gebruikersService.voegProductToe(gebruiker, product);
    }
    public void verwijderProduct(Gebruiker gebruiker, Product product) {
        try {
            if (isEigenProduct(gebruiker, product)) {
                gebruikersService.verwijderProduct(gebruiker, product);
                productDAO.verwijderProduct(product);
            } else log.info("Dit product kan niet worden verwijderd omdat het niet van jou is");
        } catch (NullPointerException e) {
            log.warn("Dit product bestaat niet");
        }
    }

    //Zoekmethodes
    public Product zoekProduct(int id) {
        try {
            return productDAO.zoek(id);
        } catch (NoResultException e) {
            log.debug(e.getClass().getSimpleName() + " : " + e.getMessage());
            log.info("Het product bestaat niet");
            return null;
        }
    }

    //Hulpmethodes
    private boolean isEigenProduct(Gebruiker gebruiker, Product product) {
        return gebruikersService.vindEigenProducten(gebruiker).stream().anyMatch(product1 -> product.equals(product1));

    }
}
