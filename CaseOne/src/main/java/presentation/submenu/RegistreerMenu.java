package presentation.submenu;

import presentation.IMenu;
import presentation.mainmenu.Hoofdmenu;
import presentation.mainmenu.MainMenu;
import service.service.GebruikerService;
import shared.Bezorgwijze;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import presentation.IMenu;

import java.util.*;

//@TODO nog testen
public class RegistreerMenu{/* implements IMenu {
    private GebruikerService gebruikerService;
    private final Logger LOGGER = LoggerFactory.getLogger(InlogMenu.class);

    public RegistreerMenu(GebruikerService gebruikerService) {
        this.gebruikerService = gebruikerService;
    }

    public void showMenu(Scanner scanner) {
        scanner = new Scanner(System.in);
        System.out.print("Wat is uw emailadres: ");
        String emailadres = scanner.nextLine();
        emailadresBestaat(emailadres);

        //List <Bezorgwijze> bezorgwijzeList = bezorgwijzenKiezen();
        //for (Bezorgwijze b : bezorgwijzeList) System.out.println(b);

        //Als het thuis afhalen is moet hij ook zijn adres opgeven
        //Systeem geeft een gegenereerd wachtwoord
        //Geef gebruiker bevestiging dat het gelukt is
        //Terug naar het mainmenu. Volgens mij hoef ik hier niks voor te doen

    private boolean emailadresBestaat(String emailadres) {
        if (gebruikerService.emailadresBestaat(emailadres)) {
            LOGGER.info("Emailadres is al in gebruik.");
            showMenu(new Scanner(System.in));
        }
        return false;
    }

    public List bezorgwijzenKiezen() {
        List<Bezorgwijze> bezorgwijzeList = new ArrayList<>();
        System.out.println("Geef nu aan welke bezorgwijzen u wilt toevoegen aan uw account: ");

        Scanner scanner = new Scanner(System.in);

        for (Bezorgwijze bezorgwijze : Bezorgwijze.values()) {
            System.out.println(bezorgwijze.getId() + "  ||  " + bezorgwijze.getOmschrijving());
            System.out.print("Deze bezorgwijze toevoegen aan uw account? J voor ja, N voor nee: ");
            String keuze = scanner.nextLine();
            if (keuze.equals("J")) bezorgwijzeList.add(bezorgwijze);
        }

        if(bezorgwijzeList.size()==0) {
            LOGGER.info("Je moet minimaal 1 bezorgwijze kiezen.");
            bezorgwijzenKiezen();
        }

        return bezorgwijzeList;
    }*/
}
