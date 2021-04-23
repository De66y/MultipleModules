package nl.marktplaats.presentatie.submenu;

import lombok.extern.log4j.Log4j2;
import nl.marktplaats.data.BezorgwijzeEnum;
import nl.marktplaats.presentatie.IMenu;
import nl.marktplaats.service.GebruikersService;
import nl.marktplaats.service.helper.DocumentLezer;
import nl.marktplaats.service.helper.Wachtwoordgenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Log4j2
public class RegistreerMenu implements IMenu {
    private GebruikersService gebruikersService;
    private final Scanner scanner;
    public RegistreerMenu(GebruikersService gebruikersService, Scanner scanner) {
        this.gebruikersService = gebruikersService;
        this.scanner = scanner;
    }


    @Override
    public void showMenu(Scanner scanner) {
        scanner = new Scanner(System.in);
        System.out.print("Wat is uw emailadres: ");
        String emailadres = scanner.nextLine();

        if (gebruikersService.emailadresBestaat(emailadres)) showMenu(scanner);

        List <BezorgwijzeEnum> bezorgwijzeList = bezorgwijzenKiezen();
        for (BezorgwijzeEnum b : bezorgwijzeList) System.out.println(b);

        String adres = adresOpvragen();

        if (adres == null && thuisAfhalenIsGekozen(bezorgwijzeList)) adresOpvragen();

        String akkoordReglement = akkoordMetReglement();

        String wachtwoord = new Wachtwoordgenerator().maakEenRandomWachtwoord();

        //Opgeven emailadres XXXXXXXXXX

        //Check of dit emailadres al bestaat XXXXXXXXXX

        //Kiezen bezorgwijzen XXXXXXXXXX

        //if thuis afhalen = bezorgwijze dan adres registreren XXXXXXXXXX

        //Akkoord met regelement XXXXXXXXXX

        //gegenereerd wachtwoord door het systeem XXXXXXXXXX


    }



    public List bezorgwijzenKiezen() {
        List<BezorgwijzeEnum> bezorgwijzeList = new ArrayList<>();
        System.out.println("Geef nu aan welke bezorgwijzen u wilt toevoegen aan uw account: ");

        Scanner scanner = new Scanner(System.in);

        for (BezorgwijzeEnum bezorgwijze : BezorgwijzeEnum.values()) {
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

    private String adresOpvragen() {
        System.out.println("Wat is uw adres");
        return scanner.nextLine();

    }

    private boolean thuisAfhalenIsGekozen(List<BezorgwijzeEnum> bezorgwijzen) {
        return bezorgwijzen.stream().anyMatch(bezorgwijzeEnum -> bezorgwijzeEnum.getOmschrijving().equals("Thuis afhalen bij verkoper"));
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
