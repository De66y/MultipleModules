package nl.marktplaats.service;

import lombok.extern.log4j.Log4j2;
import nl.marktplaats.data.ProductDAO;
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
        gebruiker.voegProductToe(product);
        gebruikersService.update(gebruiker);
    }

    //@TODO uitbreiden met check op eigen product
    public void verwijderProduct(Gebruiker gebruiker, Product product) {
        if (isEigenProduct(gebruiker, product))
        gebruiker.verwijderProduct(product);
        productDAO.verwijderProduct(product);
    }

    //@TODO op private zetten
    public boolean isEigenProduct(Gebruiker gebruiker, Product product) {
        return gebruikersService.vindEigenProducten(gebruiker).stream().anyMatch(product1 -> product.equals(product1));

    }
}
