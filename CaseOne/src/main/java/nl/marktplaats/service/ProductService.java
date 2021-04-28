package nl.marktplaats.service;

import lombok.extern.log4j.Log4j2;
import nl.marktplaats.data.GebruikerDAO;
import nl.marktplaats.data.ProductDAO;
import nl.marktplaats.gedeeld.domeinmodel.Bezorgwijze;
import nl.marktplaats.gedeeld.domeinmodel.Gebruiker;
import nl.marktplaats.gedeeld.domeinmodel.Product;


@Log4j2
public class ProductService {
    private ProductDAO productDAO;
    private GebruikersService gebruikersService;
    private ProductService productService;

    public ProductService(ProductDAO productDAO, GebruikersService gebruikersService) {
        this.productDAO = productDAO;
        this.gebruikersService = gebruikersService;
    }

    public void productTeKoopAanbieden(Gebruiker gebruiker, Product product) {
        gebruiker.addProduct(product);
        gebruikersService.update(gebruiker);
    }
}
