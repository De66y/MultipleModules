/*
TE DOEN:
1. MAINMENSECOND Lege keuzes weghalen en als nodigde afsluitoptie omhoog schuiven
2. Loggers aanmaken in de constructor of zo laten?

AFSPRAKEN
1. Verkeerde invoer en debug worden net via sout maar de logger gedaan.
 */

package org.example;

import org.example.presentation.mainmenu.MainMenu;
import java.util.Scanner;

public class MarktByPlaatsApp {

    public static void main(String[] args) {
        new MainMenu().showMenu(new Scanner(System.in));
    }
}
