package org.example.presentation.submenu;

import org.example.presentation.IMenu;
import org.example.presentation.mainmenu.MainMenuSecond;
import org.example.service.GebruikersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InlogMenu implements IMenu {
    private final Logger LOGGER = LoggerFactory.getLogger(InlogMenu.class);
    private GebruikersService gebruikersService;

    public InlogMenu(GebruikersService gebruikersService) {
        this.gebruikersService = gebruikersService;
    }

    public void showMenu(Scanner scanner) {
        int choice;
        try {
            System.out.printf("U bent in het %s\n. Waarmee kan ik u van dienst zijn: \n" +
                    "1. ----- Inloggen UNDER CONSTRUCTION----- \n" +
                    "2. Afsluiten\n"
            ,this.getClass().getSimpleName());
            System.out.print("Uw keuze: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showInlogMenu(scanner);
                    break;
                case 2:
                    System.out.println("Tot de volgende keer :)");
                    break;
                default:
                    LOGGER.info("U heeft een keuze gemaakt die niet bestaat, kies opnieuw: ");

            }
        } catch (InputMismatchException e) {
            LOGGER.debug("InputMismatchException message: " + e.getMessage());
            showMenu(new Scanner(System.in));
        }

    }

    private void showInlogMenu(Scanner scanner) {
        scanner = new Scanner(System.in);
        System.out.print("Wat is uw gebruikersnaam: ");
        String username = scanner.nextLine();

        if(usernameExist(username)) {
            System.out.print("Wat is uw wachtwoord: ");
            String password = scanner.nextLine();

            if(usernameAndPasswordExist(username, password)) new MainMenuSecond().showMenu(scanner);
        }
    }

    private boolean usernameExist(String username) {
        if (!gebruikersService.usernameExist(username)) {
            LOGGER.info("Gebruikersnaam is niet juist.");
            showMenu(new Scanner(System.in));
            return false;
        }
        return true;
    }

    private boolean usernameAndPasswordExist(String username, String password) {
        if (!gebruikersService.usernameAndPasswordExist(username, password)) {
            LOGGER.info("Wachtwoord is niet juist.");
            showMenu(new Scanner(System.in));
            return false;
        }
        return true;
    }
}
