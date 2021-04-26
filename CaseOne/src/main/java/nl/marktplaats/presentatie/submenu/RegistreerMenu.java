package nl.marktplaats.presentatie.submenu;

import lombok.extern.log4j.Log4j2;
import nl.marktplaats.gedeeld.domeinmodel.Bezorgwijze;
import nl.marktplaats.presentatie.IMenu;
import nl.marktplaats.service.BezorgwijzeService;
import nl.marktplaats.service.GebruikersService;
import nl.marktplaats.service.helper.DocumentLezer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Log4j2
public class RegistreerMenu  implements IMenu{
    private GebruikersService gebruikersService;
    private BezorgwijzeService bezorgwijzeService;
    private final Scanner scanner;

    public RegistreerMenu(GebruikersService gebruikersService, BezorgwijzeService bezorgwijzeService, Scanner scanner) {
        this.gebruikersService = gebruikersService;
        this.bezorgwijzeService = bezorgwijzeService;
        this.scanner = scanner;
    }

    @Override
    public void showMenu() {
        System.out.print("Wat is uw emailadres: ");
        String emailadres = scanner.nextLine();
        if (gebruikersService.emailadresBestaat(emailadres)) showMenu();

        List<Bezorgwijze> bezorgwijzen = bezorgwijzenKiezen();
        String adres = adresOpvragen(bezorgwijzen);

        String akkoordReglement = akkoordMetReglement();

        gebruikersService.registreren(emailadres, adres, bezorgwijzen);
    }



    private List bezorgwijzenKiezen() {
        List<Bezorgwijze> bezorgwijzeList = new ArrayList<>();
        System.out.println("Geef nu aan welke bezorgwijzen u wilt toevoegen aan uw account: ");

        Scanner scanner = new Scanner(System.in);

        for (Bezorgwijze bezorgwijze : bezorgwijzeService.alleBezorgwijzen() ) {
            System.out.println(bezorgwijze.getId() + "  ||  " + bezorgwijze.getOmschrijving());
            System.out.print("Deze bezorgwijze toevoegen aan uw account? J voor ja, N voor nee: ");
            String keuze = scanner.nextLine();
            if (keuze.equals("J")) bezorgwijzeList.add(bezorgwijze);
        }

        if(bezorgwijzeList.size()==0) {
            log.info("Je moet minimaal 1 bezorgwijze kiezen.");
            bezorgwijzenKiezen();
        }
        return bezorgwijzeList;
    }

    private String adresOpvragen(List<Bezorgwijze> bezorgwijzen) {
        String adres = "";
        while(thuisAfhalenIsGekozen(bezorgwijzen) && adres.isEmpty()) {
            System.out.println("Wat is uw adres");
            adres = scanner.nextLine();
        }
        return adres;
    }

    private boolean thuisAfhalenIsGekozen(List<Bezorgwijze> bezorgwijzen) {
        return bezorgwijzen.stream().anyMatch(bezorgwijze -> bezorgwijze.getOmschrijving().equals("Thuis afhalen bij verkoper"));
    }

    private String akkoordMetReglement() {
        new DocumentLezer().lees();
        System.out.println("Voor akkoord typ: J");
        String keuze = scanner.nextLine();
        if (!keuze.equals("J")) {
            log.info("Zonder akkoord te gaan met het reglement kunt u zich niet registreren.");
            akkoordMetReglement();
        }
        return keuze;
    }

}
