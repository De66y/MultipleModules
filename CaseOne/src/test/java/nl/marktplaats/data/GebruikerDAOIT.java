package nl.marktplaats.data;

import nl.marktplaats.model.Gebruiker;

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
    void zoekgebruikerOpIdTest() {
        //Given

        //When
        Gebruiker gebruiker = gebruikersDAO.zoek(2);


        //Then
        Assertions.assertEquals("Vincent@Smits.nl", gebruiker.getEmailadres());
    }

    @Test
    void zoekGebruikersnaamOpIdWelkeNietBestaatTest() {
        //Given

        //When

        //Then
        assertEquals(null, gebruikersDAO.zoek(42));
    }

    @Test
    void zoekOpEmailadresEnWachtwoordSuccesvolTest() {
        //Given

        //When
        Gebruiker gebruiker = gebruikersDAO.zoekGebruikersnaamEnWachtwoord("Br@m", "InfoSupport");

        //Then
        assertEquals("Br@m", gebruiker.getEmailadres());
        assertEquals("InfoSupport", gebruiker.getWachtwoord());
    }

    @Test
    void zoekOpEmailadresEnWachtwoordGefaaldTest() {
        //Given

        //When

        //Then
        assertThrows(NoResultException.class, () -> {gebruikersDAO.zoekGebruikersnaamEnWachtwoord("Debby@Home", "NightCourt");});
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