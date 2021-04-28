package nl.marktplaats.presentatie;

import lombok.extern.log4j.Log4j2;
import nl.marktplaats.gedeeld.Fabriek;

import java.util.InputMismatchException;
import java.util.Scanner;

@Log4j2
public class AanmeldMenu implements  IMenu{
    private final static String COMPANYNAME = "MarktByPlaats";
    private Fabriek fabriek;

    public AanmeldMenu(Fabriek fabriek) {
        this.fabriek = fabriek;
    }

    @Override
    public void showMenu(Scanner scanner) {
        int keuze;
        boolean mainMenuIsRunning = true;

        try {
            do {
                System.out.printf("Welkom bij %s!\nWaarmee kan ik u van dienst zijn: \n" +
                                "1. Inloggen \n" +
                                "2. Registreren \n" +
                                "3. Afsluiten.\n"
                        , COMPANYNAME);
                System.out.print("Uw keuze: ");

                keuze = scanner.nextInt();

                switch (keuze) {
                    case 1:
                        new InlogMenu(fabriek, new Scanner(System.in)).showMenu(new Scanner(System.in));
                        break;

                    case 2:
                        new RegistreerMenu(fabriek.getGebruikersService(), fabriek.getBezorgwijzeService(), new Scanner(System.in)).showMenu(new Scanner(System.in));
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
            showMenu(new Scanner(System.in));
        }
    }
}
