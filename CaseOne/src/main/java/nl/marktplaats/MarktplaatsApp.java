package nl.marktplaats;

import nl.marktplaats.data.GebruikerDAO;
import nl.marktplaats.presentatie.Menu1;
import nl.marktplaats.presentatie.MenuInloggen;
import nl.marktplaats.service.GebruikersService;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Scanner;

public class MarktplaatsApp {

    private static final EntityManager em = Persistence.createEntityManagerFactory("Productie").createEntityManager();

    public static void main(String[] args) {
        //Moet voor nu nog worden aangemaakt
        GebruikerDAO gebruikerDAO = new GebruikerDAO(em);
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

        MenuInloggen menuInloggen = new MenuInloggen(gebruikersservice);
        menuInloggen.showMenu(new Scanner(System.in));

    }
}
