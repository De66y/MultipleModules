package nl.marktplaats;

import nl.marktplaats.data.BezorgwijzeDAO;
import nl.marktplaats.data.GebruikerDAO;
import nl.marktplaats.gedeeld.Fabriek;
import nl.marktplaats.gedeeld.domeinmodel.Bezorgwijze;
import nl.marktplaats.gedeeld.domeinmodel.Gebruiker;
import nl.marktplaats.presentatie.submenu.RegistreerMenu;
import nl.marktplaats.service.GebruikersService;
import nl.marktplaats.service.helper.DocumentLezer;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.File;
import java.util.Scanner;

public class MarktplaatsApp {

    private static final EntityManager em = Persistence.createEntityManagerFactory("Productie").createEntityManager();

    public static void main(String[] args) {
        //Start van de applicatie
        Fabriek fabriek = new Fabriek(new BezorgwijzeDAO(em)); //@TODO em oplossen
        //Deze maar 1 keer
        fabriek.bezorgwijzeInDatabase();


        //Moet voor nu nog worden aangemaakt
        GebruikerDAO gebruikerDAO = new GebruikerDAO(fabriek.getEm());
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



        //RegistreerMenu registreerMenu = new RegistreerMenu(gebruikersservice);
        //registreerMenu.showMenu(new Scanner(System.in));

        //new RegistreerMenu(gebruikersservice, new Scanner(System.in)).showMenu();

        //new RegistreerMenu(gebruikersservice, new Scanner(System.in)).showMenu();

        //System.out.println(gebruikersservice.registreren("Testemail", "Testadres"));


    }
}
