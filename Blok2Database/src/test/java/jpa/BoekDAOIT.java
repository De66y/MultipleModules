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
    void zoekBoekOpIdWelkeNietBestaat() {
        //Given

        //When

        //Then
        Assertions.assertEquals(null, boekDAO.zoek(10));
    }

    @Test
    void zoekBoekOpId() {
        //Given

        //When
        Boek boek = boekDAO.zoek(2);


        //Then
        Assertions.assertEquals("The Cruel Prince", boek.getTitel());
    }

    @Test
    void aantalBoekenInDeDatabaseTest() {
      //Given

      //When
        int alleBoeken = boekDAO.alleBoekenLijst().size();
      //Then
        Assertions.assertEquals(2, alleBoeken);
    }

    @Test
    void verwijderEenBoek() {
        //Given

        //When
        boekDAO.opslaan(new Boek("From Blood and Ash", "Sarah J. Maas"));
        int nieuweAantalBoeken = boekDAO.alleBoekenLijst().size();

        boekDAO.verwijderen(3);
        int aantalBoekenNaVerwijderen = boekDAO.alleBoekenLijst().size();

        //Then
        Assertions.assertEquals(3, nieuweAantalBoeken);
        Assertions.assertEquals(2, aantalBoekenNaVerwijderen);
    }

    @Test
    void updateEenBoekTitel() {
        //Given

        //When
        String titelVoorAanpassing = boekDAO.zoek(1).getTitel();

        boekDAO.updateTitel(1, "Harry Potter and the Goblet of Fire");
        String titelNaAanpassing = boekDAO.zoek(1).getTitel();
        //Then
        Assertions.assertEquals( "Harry Potter", titelVoorAanpassing);
        Assertions.assertEquals("Harry Potter and the Goblet of Fire", titelNaAanpassing);
    }



}