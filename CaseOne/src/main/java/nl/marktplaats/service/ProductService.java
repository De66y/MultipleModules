package nl.marktplaats.service;

import lombok.extern.log4j.Log4j2;
import nl.marktplaats.data.GebruikerDAO;
import nl.marktplaats.data.ProductDAO;
import nl.marktplaats.gedeeld.domeinmodel.Bezorgwijze;
import nl.marktplaats.gedeeld.domeinmodel.Gebruiker;
import nl.marktplaats.gedeeld.domeinmodel.Product;

import java.util.List;

@Log4j2
public class ProductService {
    private ProductDAO productDAO;
    private GebruikersService gebruikersService;
    private ProductService productService;

    public ProductService(ProductDAO productDAO, GebruikersService gebruikersService) {
        this.productDAO = productDAO;
        this.gebruikersService = gebruikersService;
    }

    public void productTeKoopAanbieden(String emailadres, Product product) {
        if (gebruikersService.zoekGebruiker(emailadres).equals(null)) {
            log.info("Er is een typfout gemaakt bij je emailadres.");
        }

        Gebruiker gebruiker = gebruikersService.zoekGebruiker(emailadres);
        gebruiker.addProduct(product);

        gebruikersService.update(gebruiker);
    }
}
