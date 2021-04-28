package nl.marktplaats.gedeeld;

import lombok.Data;
import nl.marktplaats.data.BezorgwijzeDAO;
import nl.marktplaats.data.GebruikerDAO;
import nl.marktplaats.data.ProductDAO;
import nl.marktplaats.gedeeld.domeinhelper.ProductCategorie;
import nl.marktplaats.gedeeld.domeinhelper.SoortArtikel;
import nl.marktplaats.gedeeld.domeinmodel.Bezorgwijze;
import nl.marktplaats.gedeeld.domeinmodel.Gebruiker;
import nl.marktplaats.gedeeld.domeinmodel.Product;
import nl.marktplaats.service.BezorgwijzeService;
import nl.marktplaats.service.GebruikersService;
import nl.marktplaats.service.ProductService;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class Fabriek {
    private final EntityManager em;

    private BezorgwijzeService bezorgwijzeService;
    private BezorgwijzeDAO bezorgwijzeDAO;

    private GebruikersService gebruikersService;
    private GebruikerDAO gebruikerDAO;

    private ProductService productService;
    private ProductDAO productDAO;

    public Fabriek() {
        this.em = Persistence.createEntityManagerFactory("Productie").createEntityManager();
    }

    public void aanmakenDAOs() {
        this.bezorgwijzeDAO = new BezorgwijzeDAO(em);
        this.gebruikerDAO = new GebruikerDAO(em);
        this.productDAO = new ProductDAO(em);
    }
    public void aanmakenServices() {
        this.bezorgwijzeService = new BezorgwijzeService(bezorgwijzeDAO);
        this.gebruikersService = new GebruikersService(gebruikerDAO);
        this.productService = new ProductService(productDAO, gebruikersService);
    }

    public EntityManager getEm() {
        return em;
    }

    public void startDataInDatabase() {
        bezorgwijzeInDatabase();

        //Gebruiker 1
        List<Bezorgwijze> bezorgwijzenAdmin = new ArrayList<>();
        bezorgwijzenAdmin.add(bezorgwijzeService.alleBezorgwijzen().get(0));
        bezorgwijzenAdmin.add(bezorgwijzeService.alleBezorgwijzen().get(1));
        bezorgwijzenAdmin.add(bezorgwijzeService.alleBezorgwijzen().get(2));
        bezorgwijzenAdmin.add(bezorgwijzeService.alleBezorgwijzen().get(3));

        Gebruiker admin = Gebruiker.builder().emailadres("admin").wachtwoord("admin").build();
        admin.setBezorgwijzen(bezorgwijzenAdmin);

        gebruikerDAO.opslaan(admin);

        productService.productTeKoopAanbieden(
                gebruikerDAO.zoekEmailadres("admin"),
                Product.builder().naam("Duikfles").build());

        //Gebruiker 2
        List<Bezorgwijze> bezorgwijzenRuby = new ArrayList<>();
        bezorgwijzenRuby.add(bezorgwijzeService.alleBezorgwijzen().get(1));
        bezorgwijzenRuby.add(bezorgwijzeService.alleBezorgwijzen().get(2));
        gebruikersService.registreren("Ruby@emailadres.nl", "Konijnenberg 8 Etten-Leur", bezorgwijzenRuby);

        //Gebruiker 3
        List<Bezorgwijze> bezorgwijzenVincent = new ArrayList<>();
        bezorgwijzenVincent.add(bezorgwijzeService.alleBezorgwijzen().get(0));
        bezorgwijzenVincent.add(bezorgwijzeService.alleBezorgwijzen().get(2));
        gebruikersService.registreren("Vincent@emailadres.nl", "Leelooierstraat 22 Etten-Leur", bezorgwijzenVincent);




    }

    private void bezorgwijzeInDatabase () {
        bezorgwijzeService.opslaan(
                Bezorgwijze.builder().omschrijving("Afhalen magazijn").build());
        bezorgwijzeService.opslaan(
                Bezorgwijze.builder().omschrijving("Thuis afhalen bij verkoper").build());
        bezorgwijzeService.opslaan(
                Bezorgwijze.builder().omschrijving("Versturen").build());
        bezorgwijzeService.opslaan(
                Bezorgwijze.builder().omschrijving("Versturen onder rembours").build());
    }
}
