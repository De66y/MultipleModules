package nl.marktplaats;

import nl.marktplaats.gedeeld.Fabriek;
import nl.marktplaats.presentatie.AanmeldMenu;

import java.util.Scanner;

public class MarktplaatsApp {

    public static void main(String[] args) {
        Fabriek fabriek = new Fabriek();
        fabriek.startOmgeving();

        new AanmeldMenu(fabriek).showMenu(new Scanner(System.in));
    }
}
