package nl.marktplaats.gedeeld;

import lombok.Data;
import nl.marktplaats.data.BezorgwijzeDAO;
import nl.marktplaats.data.GebruikerDAO;
import nl.marktplaats.data.ProductDAO;
import nl.marktplaats.gedeeld.domeinmodel.helper.ProductCategorie;
import nl.marktplaats.gedeeld.domeinmodel.helper.SoortArtikel;
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
    public EntityManager getEm() {
        return em;
    }

    public void startOmgeving() {
        aanmakenDAOs();
        aanmakenServices();
        startDataInDatabase();
    }

    private void aanmakenDAOs() {
        this.bezorgwijzeDAO = new BezorgwijzeDAO(em);
        this.gebruikerDAO = new GebruikerDAO(em);
        this.productDAO = new ProductDAO(em);
    }
    private void aanmakenServices() {
        this.bezorgwijzeService = new BezorgwijzeService(bezorgwijzeDAO);
        this.gebruikersService = new GebruikersService(gebruikerDAO);
        this.productService = new ProductService(productDAO, gebruikersService);
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
    private void startDataInDatabase() {
        bezorgwijzeInDatabase();

        //Gebruiker 1
        List<Bezorgwijze> bezorgwijzenAdmin = new ArrayList<>();
        bezorgwijzenAdmin.add(bezorgwijzeService.alleBezorgwijzen().get(0));
        bezorgwijzenAdmin.add(bezorgwijzeService.alleBezorgwijzen().get(1));
        bezorgwijzenAdmin.add(bezorgwijzeService.alleBezorgwijzen().get(2));
        bezorgwijzenAdmin.add(bezorgwijzeService.alleBezorgwijzen().get(3));

        Gebruiker admin = Gebruiker.builder().emailadres("admin").wachtwoord("admin").akkoordReglement("J").bezorgwijzen(bezorgwijzenAdmin).build();
        gebruikerDAO.opslaan(admin);

        List<Bezorgwijze> productBezorgwijzen = new ArrayList<>();
        productBezorgwijzen.add((bezorgwijzeService.alleBezorgwijzen().get(0)));
        productBezorgwijzen.add((bezorgwijzeService.alleBezorgwijzen().get(2)));
        productBezorgwijzen.add((bezorgwijzeService.alleBezorgwijzen().get(3)));
        productService.productTeKoopAanbieden(
                gebruikerDAO.zoekEmailadres("admin"),
                Product.builder().naam("Duikfles").bezorgopties(productBezorgwijzen).productCategorie(ProductCategorie.DUIKBENODIGDHEDEN).soortArtikel(SoortArtikel.PRODUCT).prijs(50).build());

        List<Bezorgwijze> productBezorglijsten2 = new ArrayList<>();
        productBezorglijsten2.add((bezorgwijzeService.alleBezorgwijzen().get(2)));
        productBezorglijsten2.add((bezorgwijzeService.alleBezorgwijzen().get(3)));
        productService.productTeKoopAanbieden(
                gebruikerDAO.zoekEmailadres("admin"),
                Product.builder().naam("Harry Potter serie").bezorgopties(productBezorglijsten2).productCategorie(ProductCategorie.BOEKEN).soortArtikel(SoortArtikel.PRODUCT).prijs(80).build());

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

        List<Bezorgwijze> productBezorglijsten3 = new ArrayList<>();
        productBezorglijsten3.add((bezorgwijzeService.alleBezorgwijzen().get(2)));
        productBezorglijsten3.add((bezorgwijzeService.alleBezorgwijzen().get(3)));
        productService.productTeKoopAanbieden(
                gebruikerDAO.zoekEmailadres("Vincent@emailadres.nl"),
                Product.builder().naam("Kam").bezorgopties(productBezorglijsten3).productCategorie(ProductCategorie.DUIKBENODIGDHEDEN).soortArtikel(SoortArtikel.PRODUCT).prijs(0.90).build());
    }
}
