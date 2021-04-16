package org.example.presentation.mainmenu;

import org.example.data.dao.KlantDao;
import org.example.presentation.IMenu;
import org.example.presentation.submenu.RegistreerMenu;
import org.example.service.GebruikersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.example.presentation.submenu.InlogMenu;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu implements IMenu {
    private final static String COMPANYNAME = "MarktByPlaats";
    private final Logger LOGGER = LoggerFactory.getLogger(MainMenu.class);

    public void showMenu(Scanner scanner) {
        int choice;
        boolean mainMenuIsRunning = true;

        try {
            do {
                System.out.printf("Welkom bij %s!\nWaarmee kan ik u van dienst zijn: \n" +
                                "1. -----UNDER CONSTRUCTION Inloggen----- \n" +
                                "2. -----UNDER CONSTRUCTION Registreren -----\n" +
                                "3. Afsluiten.\n"
                        , COMPANYNAME);
                System.out.print("Uw keuze: ");

                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        KlantDao klantDao = new KlantDao();
                        klantDao.setUp();
                        new InlogMenu(new GebruikersService(klantDao)).showMenu(scanner);
                        break;

                    case 2:
                        new RegistreerMenu().showMenu(scanner);
                        break;

                    case 3:
                        System.out.println("Tot de volgende keer :)");
                        mainMenuIsRunning = false;
                        break;
                    default:
                        LOGGER.info("U heeft een keuze gemaakt die niet bestaat, kies opnieuw: ");
                }
            } while (mainMenuIsRunning);
        } catch (InputMismatchException e) {
            LOGGER.debug("InputMismatchException message: " + e.getMessage());
            showMenu(new Scanner(System.in));
        }
    }
}
