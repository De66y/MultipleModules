package nl.marktplaats.presentatie;

import lombok.extern.log4j.Log4j2;
import nl.marktplaats.gedeeld.Fabriek;
import nl.marktplaats.presentatie.submenu.InlogMenu;
import nl.marktplaats.presentatie.submenu.RegistreerMenu;
import nl.marktplaats.service.GebruikersService;

import java.util.InputMismatchException;
import java.util.Scanner;

@Log4j2
public class Menu1 implements IMenu{
    private final static String COMPANYNAME = "MarktByPlaats";
    private Fabriek fabriek;
    private final Scanner scanner;

    public Menu1(GebruikersService gebruikerService, Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void showMenu() {
        int keuze;
        boolean mainMenuIsRunning = true;

        try {
            do {
                System.out.printf("Welkom bij %s!\nWaarmee kan ik u van dienst zijn: \n" +
                                "1. -----UNDER CONSTRUCTION Inloggen----- \n" +
                                "2. -----UNDER CONSTRUCTION Registreren -----\n" +
                                "3. Afsluiten.\n"
                        , COMPANYNAME);
                System.out.print("Uw keuze: ");

                keuze = scanner.nextInt();

                switch (keuze) {
                    case 1:
                        new InlogMenu(fabriek.getGebruikersService(), scanner);
                        break;

                    case 2:
                        new RegistreerMenu(fabriek.getGebruikersService(), fabriek.getBezorgwijzeService(), scanner).showMenu();
                        break;

                    case 3:
                        System.out.println("Tot de volgende keer :)");
                        mainMenuIsRunning = false;
                        break;
                    default:
                        log.info("U heeft een keuze gemaakt die niet bestaat, kies opnieuw: ");
                }
            } while (mainMenuIsRunning);
        } catch (InputMismatchException e) {
            log.debug(e.getClass().getSimpleName() + " : " + e.getMessage());
            showMenu();
        }
    }
}
