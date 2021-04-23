package nl.marktplaats.data;

import nl.marktplaats.gedeeld.domeinmodel.Gebruiker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

class GebruikerDAOIT {
    private final EntityManager em =
            Persistence.createEntityManagerFactory("Test").createEntityManager();

    private GebruikerDAO gebruikersDAO;

    @BeforeEach
    void setUp() {
        gebruikersDAO = new GebruikerDAO(em);
        gebruikersDAO.opslaan(new Gebruiker("Br@m", "InfoSupport"));
        gebruikersDAO.opslaan(new Gebruiker("Vincent@Smits.nl", "DennieTechie"));
    }

    @Test
    void opslaanGebruikerTest() {
        //Given

        //When
        int aantalGebruikersVoorOpslaan = gebruikersDAO.alleGebruikers().size();
        gebruikersDAO.opslaan(new Gebruiker("Anton@bel.nl", "Belas"));
        int aantalGebruikersNaOpslaan = gebruikersDAO.alleGebruikers().size();

        Gebruiker gebruiker = gebruikersDAO.zoek(3);

        String emailadresNieuweGebruiker = gebruiker.getEmailadres();
        String wachtwoordNieuweGebruiker = gebruiker.getWachtwoord();

        //Then
        assertEquals(2, aantalGebruikersVoorOpslaan);
        assertEquals(3, aantalGebruikersNaOpslaan);
        assertEquals("Anton@bel.nl", emailadresNieuweGebruiker);
        assertEquals("Belas", wachtwoordNieuweGebruiker);


    }

    @Test
    void zoekGebruikerOpIdTest() {
        //Given

        //When
        Gebruiker gebruiker = gebruikersDAO.zoek(2);


        //Then
        Assertions.assertEquals("Vincent@Smits.nl", gebruiker.getEmailadres());
    }

    @Test
    void zoekOpIdWelkeNietBestaatTest() {
        //Given

        //When

        //Then
        assertNull(gebruikersDAO.zoek(42));
    }

    @Test
    void zoekGebruikerOpEmailadresTest() {
        //Given

        //When

        Gebruiker gebruiker = gebruikersDAO.zoekEmailadres("Vincent@Smits.nl");
        //Then

        assertEquals("Vincent@Smits.nl", gebruiker.getEmailadres());
    }

    @Test
    void zoekGebruikerOpEmailadresGefaaldTest() {
        //Given

        //When

        //Then
        assertThrows(NoResultException.class, () -> {gebruikersDAO.zoekEmailadres(("Debby@Home"));});
    }

    @Test
    void zoekOpEmailadresEnWachtwoordSuccesvolTest() {
        //Given

        //When
        Gebruiker gebruiker = gebruikersDAO.zoekEmailadresEnWachtwoord("Br@m", "InfoSupport");

        //Then
        assertEquals("Br@m", gebruiker.getEmailadres());
        assertEquals("InfoSupport", gebruiker.getWachtwoord());
    }

    @Test
    void zoekOpEmailadresEnWachtwoordGefaaldTest() {
        //Given

        //When

        //Then
        assertThrows(NoResultException.class, () -> {gebruikersDAO.zoekEmailadresEnWachtwoord("Debby@Home", "NightCourt");});
    }

    @Test
    void zoekOpEmailadresEnWachtwoordGefaaldTest2() {
        //Given

        //When

        //Then
        assertThrows(NoResultException.class, () -> {gebruikersDAO.zoekEmailadresEnWachtwoord("Br@m", "OCP");});
    }

    @Test
    void aantalGebruikerInDeDatabaseTest () {
        //Given

        //When
        int alleGebruikers = gebruikersDAO.alleGebruikers().size();
        //Then
        Assertions.assertEquals(2, alleGebruikers);
    }

}