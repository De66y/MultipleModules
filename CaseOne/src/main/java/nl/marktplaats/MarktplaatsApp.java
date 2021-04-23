package nl.marktplaats;

import nl.marktplaats.data.GebruikerDAO;
import nl.marktplaats.gedeeld.Fabriek;
import nl.marktplaats.gedeeld.domeinmodel.Gebruiker;
import nl.marktplaats.service.GebruikersService;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class MarktplaatsApp {

    private static final EntityManager em = Persistence.createEntityManagerFactory("Productie").createEntityManager();

    public static void main(String[] args) {
        //Moet voor nu nog worden aangemaakt
        GebruikerDAO gebruikerDAO = new GebruikerDAO(em);
        GebruikersService gebruikersservice = new GebruikersService(gebruikerDAO);

        //Standaard voor opslaan
        gebruikerDAO.opslaan(new Gebruiker("Calimero", "Wachtwoord"));
        gebruikerDAO.opslaan(new Gebruiker("Dotje", "Neus"));
        gebruikerDAO.opslaan(new Gebruiker("Beer", "SnufSnuf"));


        //TEST
        //System.out.println(gebruikerDAO.zoekGebruikersnaamEnWachtwoord("Dotje","Neus"));
        //gebruikersservice.inloggen("Dotje", "Neus");

        //gebruikerDAO.zoekEmailadresEnWachtwoord("h", "g");
        //System.out.println(gebruikersservice.inloggen("Dotje", "Neus"));

        Fabriek fabriek = new Fabriek();
        fabriek.enumsInDatabase();


    }
}
