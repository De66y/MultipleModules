/*
TE DOEN:
1. MAINMENSECOND Lege keuzes weghalen en als nodigde afsluitoptie omhoog schuiven
2. Loggers aanmaken in de constructor of zo laten?
3. Gebruikersservice nog interface maken bij de loginmenu?
4. Bij bezorgwijze kiezen een inputmismatch afvangen
5. registreer menu->show menu + inlogmenu->showsubmenu || nieuwe scanner moeten maken
6. Gebruikersservice implementen 2 interfaces

AFSPRAKEN
1. Verkeerde invoer en debug worden net via sout maar de logger gedaan.
 */

import data.dao.GebruikerDAO;
import presentation.mainmenu.Hoofdmenu;
import presentation.mainmenu.MainMenu;
import presentation.submenu.RegistreerMenu;
import service.WachtwoordGenerator;
import service.service.GebruikerService;
import shared.model.Gebruiker;

import java.util.Scanner;

public class MarktByPlaatsApp {

    public static void main(String[] args) {
        //new MainMenu(new GebruikerService(new GebruikerDAO())).showMenu(new Scanner(System.in));
        GebruikerDAO gebruikerDAO = new GebruikerDAO();
        //gebruikerDAO.opslaan(new Gebruiker("Lazlo", "Dreamer"));

        for (Gebruiker gebruiker : gebruikerDAO.alleGebruikers()) {
            System.out.println(gebruiker);
        }

        GebruikerService gebruikerService = new GebruikerService(gebruikerDAO);
        System.out.println(gebruikerService.zoekGebruikersnaamEnWachtwoord("Lazlo", "Dreamer"));



    }
}
