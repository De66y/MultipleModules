package nl.marktplaats.presentatie;

import lombok.extern.log4j.Log4j2;
import nl.marktplaats.gedeeld.Fabriek;
import nl.marktplaats.presentatie.Hoofdmenu;
import nl.marktplaats.presentatie.IMenu;
import nl.marktplaats.presentatie.ISubMenu;
import nl.marktplaats.service.GebruikersService;

import java.util.InputMismatchException;
import java.util.Scanner;

@Log4j2
public class InlogMenu implements IMenu, ISubMenu {
    private Fabriek fabriek;
    private Scanner scanner;

    public InlogMenu(Fabriek fabriek, Scanner scanner) {
        this.fabriek = fabriek;
        this.scanner = scanner;
    }

    @Override
    public void showMenu(Scanner scanner) {
        int keuze;
        try {
            System.out.printf("U bent in het %s.\nWaarmee kan ik u van dienst zijn: \n" +
                            "1. Inloggen \n" +
                            "2. Terug naar het aanmeldmenu\n"
                    ,this.getClass().getSimpleName());
            System.out.print("Uw keuze: ");
            keuze = scanner.nextInt();

            switch (keuze) {
                case 1:
                    showSubMenu(new Scanner(System.in));
                    break;
                case 2:
                    System.out.println("Tot de volgende keer :)");
                    break;
                default:
                    log.info("U heeft een keuze gemaakt die niet bestaat, kies opnieuw: ");
                    showMenu(new Scanner(System.in));
            }
        } catch (InputMismatchException e){
            log.debug(e.getClass().getSimpleName() + " : " + e.getMessage());
            showMenu(new Scanner(System.in));
        }
    }

    @Override
    public void showSubMenu(Scanner scanner) {
        System.out.print("Wat is uw emailadres: ");
        String emailadres = scanner.nextLine();
        System.out.print("\nWat is uw wachtwoord: ");
        String wachtwoord = scanner.nextLine();

        switchSubMenu(emailadres, wachtwoord);
    }

    private void switchSubMenu(String emailadres, String wachtwoord) {
        switch (fabriek.getGebruikersService().inloggen(emailadres, wachtwoord)) {
            case "Emailadres bestaat niet":
                showMenu(new Scanner(System.in));
                break;
            case "Wachtwoord niet juist":
                showMenu(new Scanner(System.in));
                break;
            case "Succesvol":
                new Hoofdmenu(fabriek.getGebruikersService().zoekGebruiker(emailadres),fabriek.getProductService(), fabriek.getGebruikersService(), new Scanner(System.in)).showMenu(new Scanner(System.in));
                break;
        }
    }
}
