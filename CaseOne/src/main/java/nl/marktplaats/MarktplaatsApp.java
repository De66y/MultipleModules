package nl.marktplaats;

import nl.marktplaats.data.GebruikerDAO;
import nl.marktplaats.gedeeld.Fabriek;
import nl.marktplaats.service.GebruikersService;
import nl.marktplaats.service.helper.DocumentLezer;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.File;

public class MarktplaatsApp {

    private static final EntityManager em = Persistence.createEntityManagerFactory("Productie").createEntityManager();

    public static void main(String[] args) {
        //Start van de applicatie
        Fabriek fabriek = new Fabriek();
        //Deze maar 1 keer
        //fabriek.enumsInDatabase();


        //Moet voor nu nog worden aangemaakt
        GebruikerDAO gebruikerDAO = new GebruikerDAO(fabriek.getEm());
        GebruikersService gebruikersservice = new GebruikersService(gebruikerDAO);

        //Standaard voor opslaan
        //gebruikerDAO.opslaan(new Gebruiker("Calimero", "Wachtwoord"));
        //gebruikerDAO.opslaan(new Gebruiker("Dotje", "Neus"));
        //gebruikerDAO.opslaan(new Gebruiker("Beer", "SnufSnuf"));


        //TEST
        //System.out.println(gebruikerDAO.zoekGebruikersnaamEnWachtwoord("Dotje","Neus"));
        //gebruikersservice.inloggen("Dotje", "Neus");

        //gebruikerDAO.zoekEmailadresEnWachtwoord("h", "g");
        //System.out.println(gebruikersservice.inloggen("Dotje", "Neus"));



        //RegistreerMenu registreerMenu = new RegistreerMenu(gebruikersservice);
        //registreerMenu.showMenu(new Scanner(System.in));

        DocumentLezer documentLezer = new DocumentLezer();
        documentLezer.lees();
    }
}
