package nl.marktplaats.presentatie.submenu;

import lombok.extern.log4j.Log4j2;
import nl.marktplaats.presentatie.IMenu;
import nl.marktplaats.presentatie.ISubMenu;
import nl.marktplaats.service.GebruikersService;

import java.util.InputMismatchException;
import java.util.Scanner;

@Log4j2
public class InlogMenu implements IMenu, ISubMenu {

    private GebruikersService gebruikersService;
    private Scanner scanner;

    public InlogMenu(GebruikersService gebruikersService, Scanner scanner) {
        this.gebruikersService = gebruikersService;
        this.scanner = scanner;
    }

    @Override
    public void showMenu() {
        int keuze;
        try {
            System.out.printf("U bent in het %s.\nWaarmee kan ik u van dienst zijn: \n" +
                            "1. Inloggen \n" +
                            "2. Afsluiten\n"
                    ,this.getClass().getSimpleName());
            System.out.print("Uw keuze: ");
            keuze = scanner.nextInt();

            switch (keuze) {
                case 1:
                    showSubMenu();
                    break;
                case 2:
                    System.out.println("Tot de volgende keer :)");
                    break;
                default:
                    log.info("U heeft een keuze gemaakt die niet bestaat, kies opnieuw: ");
                    showMenu();
            }
        } catch (InputMismatchException e){
            log.debug(e.getClass().getSimpleName() + " : " + e.getMessage());
            showMenu();
        }
    }

    @Override
    public void showSubMenu() {
        scanner = new Scanner(System.in);
        System.out.print("Wat is uw emailadres: ");
        String emailadres = scanner.nextLine();
        System.out.print("\nWat is uw wachtwoord: ");
        String wachtwoord = scanner.nextLine();

        switchSubMenu(emailadres, wachtwoord);
    }

    private void switchSubMenu(String emailadres, String wachtwoord) {
        switch (gebruikersService.inloggen(emailadres, wachtwoord)) {
            case "EN":
                showMenu();
                break;
            case "WN":
                showMenu();
                break;
            case "S":
                //@TODO ingelogd hoofdmenu weergeven
                break;
        }
    }
}
