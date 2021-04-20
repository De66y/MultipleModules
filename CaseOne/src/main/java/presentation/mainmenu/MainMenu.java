package presentation.mainmenu;

import data.dao.GebruikerDAO;
import presentation.IMenu;
import presentation.submenu.RegistreerMenu;
import service.service.GebruikerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import presentation.submenu.InlogMenu;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu{/* implements  IMenu{
    private final static String COMPANYNAME = "MarktByPlaats";
    private final Logger LOGGER = LoggerFactory.getLogger(MainMenu.class);
    private GebruikerService gebruikerService;

    public MainMenu(GebruikerService gebruikerService) {
        this.gebruikerService = gebruikerService;
    }

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
                        new InlogMenu(new GebruikerService(new GebruikerDAO())).showMenu(scanner);
                        break;

                    case 2:
                        new RegistreerMenu(gebruikerService).showMenu(scanner);
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
            LOGGER.debug(e.getClass().getSimpleName() + " : " + e.getMessage());
            showMenu(new Scanner(System.in));
        }
    }*/
}
