package nl.marktplaats;

import nl.marktplaats.data.BezorgwijzeDAO;
import nl.marktplaats.data.GebruikerDAO;
import nl.marktplaats.data.ProductDAO;
import nl.marktplaats.gedeeld.Fabriek;
import nl.marktplaats.gedeeld.domeinhelper.ProductCategorie;
import nl.marktplaats.gedeeld.domeinmodel.Bezorgwijze;
import nl.marktplaats.gedeeld.domeinmodel.Gebruiker;
import nl.marktplaats.gedeeld.domeinmodel.Product;
import nl.marktplaats.presentatie.submenu.RegistreerMenu;
import nl.marktplaats.service.BezorgwijzeService;
import nl.marktplaats.service.GebruikersService;
import nl.marktplaats.service.helper.DocumentLezer;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MarktplaatsApp {

    public static void main(String[] args) {
        //Start van de applicatie
        Fabriek fabriek = new Fabriek(
                new BezorgwijzeService(
                        new BezorgwijzeDAO(
                                Persistence.createEntityManagerFactory("Productie").createEntityManager()
                        ))); //@TODO em oplossen
        //Deze maar 1 keer
        fabriek.bezorgwijzeInDatabase();

        //Moet voor nu nog worden aangemaakt
        /*GebruikerDAO gebruikerDAO = new GebruikerDAO(fabriek.getEm());
        GebruikersService gebruikersservice = new GebruikersService(gebruikerDAO);
        BezorgwijzeService bezorgwijzeService = fabriek.getBezorgwijzeService();*/

        //Standaard voor opslaan
        /*gebruikerDAO.opslaan(new Gebruiker("Calimero", "Wachtwoord"));
        gebruikerDAO.opslaan(new Gebruiker("Dotje", "Neus"));
        gebruikerDAO.opslaan(new Gebruiker("Beer", "SnufSnuf"));*/


        //TEST
        //System.out.println(gebruikerDAO.zoekGebruikersnaamEnWachtwoord("Dotje","Neus"));
        //gebruikersservice.inloggen("Dotje", "Neus");

        //gebruikerDAO.zoekEmailadresEnWachtwoord("h", "g");
        //System.out.println(gebruikersservice.inloggen("Dotje", "Neus"));

        //System.out.println(fabriek.getBezorgwijzeService().opslaan(new Bezorgwijze("Bezorgwijze 5")));

        List<Bezorgwijze> bezorgwijzen = new ArrayList<>();
        bezorgwijzen.add(new Bezorgwijze("Versturen"));
        bezorgwijzen.add(new Bezorgwijze("Afhalen magazijn"));

        Product product = new Product();

        new ProductDAO(fabriek.getEm()).opslaan(new Product(ProductCategorie.DUIKBENODIGDHEDEN, "Duikfles", 139.99, "Duikfles voor perslucht"));


    }
}
