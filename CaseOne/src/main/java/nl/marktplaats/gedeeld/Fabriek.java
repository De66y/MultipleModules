package nl.marktplaats.gedeeld;

import lombok.Data;
import nl.marktplaats.data.BezorgwijzeDAO;
import nl.marktplaats.gedeeld.domeinmodel.Bezorgwijze;
import nl.marktplaats.service.BezorgwijzeService;

import javax.persistence.*;

@Data
//@TODO mss uit constructor halen als het er niet te veel worden
public class Fabriek {
    private final EntityManager em;
    private BezorgwijzeService bezorgwijzeService;

    public Fabriek(BezorgwijzeService bezorgwijzeService) {
        this.em = Persistence.createEntityManagerFactory("Productie").createEntityManager();
        this.bezorgwijzeService = bezorgwijzeService;
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
