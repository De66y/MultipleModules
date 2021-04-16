package org.example.presentation.mainmenu;

import org.example.presentation.IMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.InputMismatchException;
import java.util.Scanner;

//@TODO
public class MainMenuSecond implements IMenu {
    private final Logger LOGGER = LoggerFactory.getLogger(MainMenuSecond.class);

    public void showMenu(Scanner scanner) {
        int choice;
        boolean menuIsRunning = true;

        try {
            do {
                System.out.printf("U bent in het %s!\nWaarmee kan ik u van dienst zijn: \n" +
                                "1. UNDER CONSTRUCTION \n" +
                                "2. UNDER CONSTRUCTION.\n" +
                                "3. UNDER CONSTRUCTION.\n" +
                                "4. Afsluiten.\n"
                        , this.getClass().getSimpleName());
                System.out.print("Uw keuze: ");

                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        break;

                    case 2:
                        break;

                    case 3:
                        break;

                    case 4:
                        System.out.println("Tot de volgende keer :)");
                        menuIsRunning = false;
                        break;
                    default:
                        LOGGER.info("U heeft een keuze gemaakt die niet bestaat, kies opnieuw: ");
                }
            } while (menuIsRunning);

        } catch (InputMismatchException e) {
            LOGGER.debug("InputMismatchException message: " + e.getMessage());
            showMenu(new Scanner(System.in));
        }
    }
}
