package nl.marktplaats;

import nl.marktplaats.data.GebruikerDAO;
import nl.marktplaats.gedeeld.Fabriek;
import nl.marktplaats.gedeeld.domeinmodel.Gebruiker;
import nl.marktplaats.gedeeld.domeinmodel.Product;
import nl.marktplaats.presentatie.AanmeldMenu;

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
        /*new Hoofdmenu(
                fabriek.getGebruikerDAO().zoekEmailadres("Ruby@emailadres.nl"),
                fabriek.getProductService(),
                fabriek.getGebruikersService(),
                new Scanner(System.in)
        ).showSubMenu(new Scanner(System.in));*/

        //TEST EIGEN PRODUCTEN OPHALEN
        Gebruiker gebruiker = gebruikerDAO.zoekEmailadres("admin");
        /*List<Product> testLijst = fabriek.getGebruikersService().vindEigenProducten(gebruiker);
        System.out.println(testLijst.size());
        for (Product product : testLijst) {
            System.out.println(product.getNaam());
        }*/
        //testLijst.stream().forEach(product -> System.out.println(product.getNaam()));



        //TEST verwijderen vanuit dao
        /*System.out.println(fabriek.getProductDAO().alleProducten().size());
        Product product = fabriek.getProductDAO().zoek(2);
        System.out.println(product.getNaam());
        fabriek.getProductService().verwijderProduct(gebruiker, product);*/

        //System.out.println(fabriek.getProductDAO().alleProducten().size());

        //TEST TOTALE LIJST VAN ALLE PRODUCTEN
        //List<Product> lijst = fabriek.getProductDAO().alleProducten();
        //System.out.println(lijst.size());

        /*fabriek.getProductService().verwijderProduct(gebruiker,
                fabriek.getProductDAO().zoek(2));

        List<Product> lijst2 = fabriek.getProductDAO().alleProducten();
        System.out.println(lijst2.size());*/

        System.out.println(
        fabriek.getProductService().isEigenProduct(gebruiker,
                fabriek.getProductDAO().zoek(2))
        );




        //Officiële start
        //new AanmeldMenu(fabriek).showMenu(new Scanner(System.in));
    }
}
