package nl.marktplaats;

import nl.marktplaats.gedeeld.Fabriek;
import nl.marktplaats.presentatie.AanmeldMenu;

import java.util.Scanner;

public class MarktplaatsApp {

    public static void main(String[] args) {
        //Start van de applicatie
        Fabriek fabriek = new Fabriek();
        fabriek.aanmakenDAOs();
        fabriek.aanmakenServices();
        fabriek.startDataInDatabase();

        //Officiële start
        new AanmeldMenu(fabriek).showMenu(new Scanner(System.in));
    }
}
