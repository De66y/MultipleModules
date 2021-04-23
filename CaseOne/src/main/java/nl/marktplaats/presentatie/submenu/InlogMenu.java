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

    public InlogMenu(GebruikersService gebruikersService) {
        this.gebruikersService = gebruikersService;
    }

    @Override
    public void showMenu(Scanner scanner) {
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
                    showSubMenu(scanner);
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
                showMenu(new Scanner(System.in));
                break;
            case "WN":
                showMenu(new Scanner(System.in));
                break;
            case "S":
                //@TODO ingelogd hoofdmenu weergeven
                break;
        }
    }
}
