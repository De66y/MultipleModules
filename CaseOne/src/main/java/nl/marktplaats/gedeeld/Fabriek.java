package nl.marktplaats.gedeeld;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import nl.marktplaats.data.BezorgwijzeDAO;
import nl.marktplaats.data.BezorgwijzeEnum;
import nl.marktplaats.data.GebruikerDAO;
import nl.marktplaats.gedeeld.domeinmodel.Bezorgwijze;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@TODO mss uit constructor halen als het er niet te veel worden
public class Fabriek {
    private final EntityManager em;
    private BezorgwijzeDAO bezorgwijzeDAO;

    public Fabriek(BezorgwijzeDAO bezorgwijzeDAO) {
        this.em = Persistence.createEntityManagerFactory("Productie").createEntityManager();
        this.bezorgwijzeDAO = bezorgwijzeDAO;
    }

    public EntityManager getEm() {
        return em;
    }

    public void bezorgwijzeInDatabase () {
        bezorgwijzeDAO.opslaan(
                Bezorgwijze.builder().omschrijving("Afhalen magazijn").build());
        bezorgwijzeDAO.opslaan(
                Bezorgwijze.builder().omschrijving("Thuis afhalen bij verkoper").build());
        bezorgwijzeDAO.opslaan(
                Bezorgwijze.builder().omschrijving("Versturen").build());
        bezorgwijzeDAO.opslaan(
                Bezorgwijze.builder().omschrijving("Versturen onder rembours").build());
    }






}
