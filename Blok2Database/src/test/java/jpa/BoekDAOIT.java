package jpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;


class BoekDAOIT {

    private final EntityManager em =
            Persistence.createEntityManagerFactory("Test").createEntityManager();

    private BoekDAO boekDAO;

    @BeforeEach
    void setUp() {
        boekDAO = new BoekDAO(em);
        boekDAO.opslaan(new Boek("Harry Potter", "J.K. Rowling"));
        boekDAO.opslaan(new Boek("The Cruel Prince", "H. Black"));
    }

    @Test
    void boekOpslaan() {
        //Given

        //When
        boekDAO.opslaan(new Boek("From Blood and Ash", "Sarah J. Maas"));
        int nieuweAantalBoeken = boekDAO.alleBoekenLijst().size();
        Boek boek = boekDAO.zoek(3);
        String nieuweTitelBoek = boek.getTitel();


        //Then
        Assertions.assertEquals(3, nieuweAantalBoeken);
        Assertions.assertEquals("From Blood and Ash", nieuweTitelBoek);
    }

    @Test
    void aantalBoekenInDeDatabaseTest() {
      //Given

      //When
        int alleBoeken = boekDAO.alleBoekenLijst().size();
      //Then
        Assertions.assertEquals(2, alleBoeken);
    }

}