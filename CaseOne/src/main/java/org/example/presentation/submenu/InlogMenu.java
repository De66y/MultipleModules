package org.example.presentation.submenu;

import org.example.presentation.IMenu;
import org.example.presentation.ISubMenu;
import org.example.presentation.mainmenu.Hoofdmenu;
import org.example.service.service.GebruikerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InlogMenu implements IMenu, ISubMenu {
    private final Logger LOGGER = LoggerFactory.getLogger(InlogMenu.class);
    private GebruikerService gebruikerService;

    public InlogMenu(GebruikerService gebruikerService) {
        this.gebruikerService = gebruikerService;
    }

    public void showMenu(Scanner scanner) {
        int choice;
        try {
            System.out.printf("U bent in het %s.\n Waarmee kan ik u van dienst zijn: \n" +
                    "1. ----- Inloggen UNDER CONSTRUCTION----- \n" +
                    "2. Afsluiten\n"
            ,this.getClass().getSimpleName());
            System.out.print("Uw keuze: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showSubMenu(scanner);
                    break;
                case 2:
                    System.out.println("Tot de volgende keer :)");
                    break;
                default:
                    LOGGER.info("U heeft een keuze gemaakt die niet bestaat, kies opnieuw: ");

            }
        } catch (InputMismatchException e) {
            LOGGER.debug(e.getClass().getSimpleName() + " : " + e.getMessage());
            showMenu(new Scanner(System.in));
        }

    }

    public void showSubMenu(Scanner scanner) {
        scanner = new Scanner(System.in);
        System.out.print("Wat is uw emailadres: ");
        String username = scanner.nextLine();

        if(usernameExist(username)) {
            System.out.print("Wat is uw wachtwoord: ");
            String password = scanner.nextLine();

            if(usernameAndPasswordExist(username, password)) new Hoofdmenu().showMenu(scanner);
        }
    }

    private boolean usernameExist(String username) {
        if (!gebruikerService.emailadresBestaat(username)) {
            LOGGER.info("Gebruikersnaam is niet juist.");
            showMenu(new Scanner(System.in));
            return false;
        }
        return true;
    }

    private boolean usernameAndPasswordExist(String username, String password) {
        if (!gebruikerService.usernameAndPasswordExist(username, password)) {
            LOGGER.info("Wachtwoord is niet juist.");
            showMenu(new Scanner(System.in));
            return false;
        }
        return true;
    }
}
