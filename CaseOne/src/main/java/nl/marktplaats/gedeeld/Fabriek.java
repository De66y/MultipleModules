package nl.marktplaats.gedeeld;

import lombok.Data;
import nl.marktplaats.data.BezorgwijzeDAO;
import nl.marktplaats.data.GebruikerDAO;
import nl.marktplaats.gedeeld.domeinmodel.Bezorgwijze;
import nl.marktplaats.service.BezorgwijzeService;
import nl.marktplaats.service.GebruikersService;

import javax.persistence.*;

@Data
//@TODO mss uit constructor halen als het er niet te veel worden
public class Fabriek {
    private final EntityManager em;

    private BezorgwijzeService bezorgwijzeService;
    private BezorgwijzeDAO bezorgwijzeDAO;

    private GebruikersService gebruikersService;
    private GebruikerDAO gebruikerDAO;

    public Fabriek() {
        this.em = Persistence.createEntityManagerFactory("Productie").createEntityManager();
    }

    public void aanmakenDAOs() {
        this.bezorgwijzeDAO = new BezorgwijzeDAO(em);
        this.gebruikerDAO = new GebruikerDAO(em);
    }
    public void aanmakenServices() {
        this.bezorgwijzeService = new BezorgwijzeService(bezorgwijzeDAO);
        this.gebruikersService = new GebruikersService(gebruikerDAO);
    }

    public EntityManager getEm() {
        return em;
    }

    public void bezorgwijzeInDatabase () {
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
