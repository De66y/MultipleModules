package nl.marktplaats.data;

import nl.marktplaats.gedeeld.domeinmodel.Bezorgwijze;
import nl.marktplaats.gedeeld.domeinmodel.Gebruiker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

class BezorgwijzeDAOIT {
    private final EntityManager em =
            Persistence.createEntityManagerFactory("Test").createEntityManager();

    private BezorgwijzeDAO bezorgwijzeDAO;

    @BeforeEach
    void setUp() {
        bezorgwijzeDAO = new BezorgwijzeDAO(em);
        bezorgwijzeDAO.opslaan(new Bezorgwijze("Afhalen magazijn"));
        bezorgwijzeDAO.opslaan(new Bezorgwijze("Thuis afhalen bij verkoper"));
        bezorgwijzeDAO.opslaan(new Bezorgwijze("Versturen"));
    }

    @Test
    void opslaanBezorgwijzeTest() {
        //Given

        //When
        int aantalBezorgwijzenVoorOpslaan = bezorgwijzeDAO.alleBezorgwijzen().size();
        bezorgwijzeDAO.opslaan(new Bezorgwijze("Versturen onder rembours"));
        int aantalBezorgwijzenNaOpslaan = bezorgwijzeDAO.alleBezorgwijzen().size();

        //Then
        assertEquals(3, aantalBezorgwijzenVoorOpslaan);
        assertEquals(4, aantalBezorgwijzenNaOpslaan);
    }

    @Test
    void aantalBezorgwijzenInDeDatabaseTest () {
        //Given
        bezorgwijzeDAO.opslaan(new Bezorgwijze("Versturen onder rembours"));

        //When
        int alleBezorgwijzen = bezorgwijzeDAO.alleBezorgwijzen().size();

        //Then
        Assertions.assertEquals(4, alleBezorgwijzen);
    }



}