/*
TE DOEN:
1. MAINMENSECOND Lege keuzes weghalen en als nodigde afsluitoptie omhoog schuiven
2. Loggers aanmaken in de constructor of zo laten?
3. Gebruikersservice nog interface maken bij de loginmenu?
4. Bij bezorgwijze kiezen een inputmismatch afvangen
5. registreer menu->show menu + inlogmenu->showsubmenu || nieuwe scanner moeten maken

AFSPRAKEN
1. Verkeerde invoer en debug worden net via sout maar de logger gedaan.
 */

package org.example;

import org.example.data.dao.GebruikerDAO;
import org.example.presentation.mainmenu.Hoofdmenu;
import org.example.presentation.mainmenu.MainMenu;
import org.example.presentation.submenu.RegistreerMenu;
import org.example.service.WachtwoordGenerator;
import org.example.service.service.GebruikerService;

import java.util.Scanner;

public class MarktByPlaatsApp {

    public static void main(String[] args) {
        GebruikerDAO gebruikerDAO = new GebruikerDAO();
        gebruikerDAO.setUp();
        new MainMenu(new GebruikerService(gebruikerDAO)).showMenu(new Scanner(System.in));

    }
}
