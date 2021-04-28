package nl.marktplaats;

import nl.marktplaats.data.GebruikerDAO;
import nl.marktplaats.gedeeld.Fabriek;
import nl.marktplaats.gedeeld.domeinhelper.ProductCategorie;
import nl.marktplaats.gedeeld.domeinhelper.SoortArtikel;
import nl.marktplaats.gedeeld.domeinmodel.Bezorgwijze;
import nl.marktplaats.gedeeld.domeinmodel.Gebruiker;
import nl.marktplaats.gedeeld.domeinmodel.Product;
import nl.marktplaats.presentatie.AanmeldMenu;
import nl.marktplaats.presentatie.Hoofdmenu;
import nl.marktplaats.service.GebruikersService;
import nl.marktplaats.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MarktplaatsApp {

    public static void main(String[] args) {
        //Start van de applicatie
        Fabriek fabriek = new Fabriek();
        fabriek.aanmakenDAOs();
        fabriek.aanmakenServices();
        fabriek.startDataInDatabase();

        //TEST INLOGGEN
        //System.out.println(gebruikerDAO.zoekGebruikersnaamEnWachtwoord("Dotje","Neus"));
        //gebruikersservice.inloggen("Dotje", "Neus");

        //TEST ZOEK OP EMAILADERS EN WACHTWOORD
        //gebruikerDAO.zoekEmailadresEnWachtwoord("h", "g");
        //System.out.println(gebruikersservice.inloggen("Dotje", "Neus"));

        //TEST PROBEREN EEN VIJFDE BEZORGWIJZE OP TE SLAAN
        //System.out.println(fabriek.getBezorgwijzeService().opslaan(new Bezorgwijze("Bezorgwijze 5")));

        //EIGEN BEZORGWIJZELIJSTEN TESTTS
        //Gebruikersdao vind eigen bezorgwijzen
        GebruikerDAO gebruikerDAO = new GebruikerDAO(fabriek.getEm());

        /*Gebruiker gebruiker = gebruikerDAO.zoekEmailadres("Ruby@emailadres.nl");
        List<Bezorgwijze> testLijst1 = gebruikerDAO.zoekEigenBezorgwijzen(gebruiker);
        System.out.println(testLijst1);*/

        //Gebruikersservice vind eigen bezorgwijzen
        /*List<Bezorgwijze> testLijst =
        fabriek.getGebruikersService().vindEigenBezorgWijzen(
                gebruikerDAO.zoekEmailadres("Ruby@emailadres.nl")
        );
        System.out.println(testLijst);*/



        //Hoofdmenu vind eigen bezorgwijzen
        new Hoofdmenu(
                fabriek.getGebruikerDAO().zoekEmailadres("Ruby@emailadres.nl"),
                fabriek.getProductService(),
                fabriek.getGebruikersService(),
                new Scanner(System.in)
        ).showSubMenu(new Scanner(System.in));




        //Officiële start
        //new AanmeldMenu(fabriek).showMenu(new Scanner(System.in));
    }
}
