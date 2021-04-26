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
import nl.marktplaats.service.ProductService;
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
        Fabriek fabriek = new Fabriek();
        fabriek.aanmakenDAOs();
        fabriek.aanmakenServices();
        fabriek.bezorgwijzeInDatabase(); //Deze maar 1 keer


        //Standaard voor opslaan
        fabriek.getGebruikerDAO().opslaan(new Gebruiker("Calimero", "Wachtwoord"));
        fabriek.getGebruikerDAO().opslaan(new Gebruiker("Dotje", "Neus"));
        fabriek.getGebruikerDAO().opslaan(new Gebruiker("Beer", "SnufSnuf"));


        //TEST
        //System.out.println(gebruikerDAO.zoekGebruikersnaamEnWachtwoord("Dotje","Neus"));
        //gebruikersservice.inloggen("Dotje", "Neus");

        //gebruikerDAO.zoekEmailadresEnWachtwoord("h", "g");
        //System.out.println(gebruikersservice.inloggen("Dotje", "Neus"));

        //System.out.println(fabriek.getBezorgwijzeService().opslaan(new Bezorgwijze("Bezorgwijze 5")));

        //new RegistreerMenu(fabriek.getGebruikersService(), fabriek.getBezorgwijzeService(), new Scanner(System.in)).showMenu();

        List<Bezorgwijze> bezorgwijzen = new ArrayList<>();
        bezorgwijzen.add(new Bezorgwijze("Versturen"));
        bezorgwijzen.add(new Bezorgwijze("Afhalen magazijn"));

        Product product = new Product(ProductCategorie.DUIKBENODIGDHEDEN, "Duikfles", 139.99, "Duikfles voor perslucht");
        Product product2 = new Product(ProductCategorie.DIERBENODIGDHEDEN, "Konijnenren", 20.00, "Ren 4m bij 2m ");

        //new ProductDAO(fabriek.getEm()).opslaan(product);

        //List<Bezorgwijze> test = new ArrayList<>();
        //test.add(new Bezorgwijze("Versturen"));
        //test.add(new Bezorgwijze("Thuis afhalen bij verkoper"));


        //new ProductService(new ProductDAO(fabriek.getEm()), gebruikersservice).productTeKoopAanbieden("Dotje", product, test);
        //new ProductService(new ProductDAO(fabriek.getEm()), gebruikersservice).productTeKoopAanbieden("Dotje", product2, test);




    }
}
