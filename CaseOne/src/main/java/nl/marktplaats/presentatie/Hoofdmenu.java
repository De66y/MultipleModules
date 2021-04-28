package nl.marktplaats.presentatie;

import lombok.extern.log4j.Log4j2;
import nl.marktplaats.gedeeld.domeinhelper.ProductCategorie;
import nl.marktplaats.gedeeld.domeinhelper.SoortArtikel;
import nl.marktplaats.gedeeld.domeinmodel.Bezorgwijze;
import nl.marktplaats.gedeeld.domeinmodel.Gebruiker;
import nl.marktplaats.gedeeld.domeinmodel.Product;
import nl.marktplaats.service.GebruikersService;
import nl.marktplaats.service.ProductService;

import java.util.*;

@Log4j2
public class Hoofdmenu implements IMenu, ISubMenu {
    private Gebruiker gebruiker;
    private ProductService productService;
    private GebruikersService gebruikersService;
    private Scanner scanner;

    public Hoofdmenu(Gebruiker gebruiker, ProductService productService, GebruikersService gebruikersService, Scanner scanner) {
        this.gebruiker = gebruiker;
        this.productService = productService;
        this.gebruikersService = gebruikersService;
        this.scanner = scanner;
    }

    @Override
    public void showMenu(Scanner scanner) {
        int keuze;
        try {
            System.out.printf("U bent in het %s.\nWaarmee kan ik u van dienst zijn: \n" +
                            "1. -----UNDER CONSTRUCTION Product toevoegen -----\n" +
                            "2. -----UNDER CONSTRUCTION Mijn producten inzien -----\n" +
                            "3. -----UNDER CONSTRUCTION Product toevoegen aan mijn verlanglijstje-----\n" +
                            "4. Terug naar het aanmeldmenu \n"
                    , this.getClass().getSimpleName());
            System.out.print("Uw keuze: ");
            keuze = scanner.nextInt();

            switch (keuze) {
                case 1:
                    showSubMenu(new Scanner(System.in));
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    System.out.println("Tot de volgende keer :)");
                    break;
                default:
                    log.info("U heeft een keuze gemaakt die niet bestaat, kies opnieuw: ");
                    showMenu(new Scanner(System.in));
            }
        } catch (InputMismatchException e) {
            log.debug(e.getClass().getSimpleName() + " : " + e.getMessage());
            showMenu(new Scanner(System.in));
        }
    }


    //@TODO showsubmenu afmaken
    //@TODO bezorgwijzen kiezen afmaken
    //@TODO kiesProductCategorie afmaken
    @Override
    public void showSubMenu(Scanner scanner) {
        System.out.print("Geef de naam van het artikel: ");
        String naam = scanner.nextLine();
        System.out.print("Geef een beschrijving van het artikel: ");
        String beschrijving = scanner.nextLine();
        System.out.print("Wat wordt de vraagprijs: ");
        double prijs = scanner.nextDouble();

        ProductCategorie productCategorie = kiesProductCategorie();
        List<Bezorgwijze> bezorgwijzenVoorProduct = bezorgwijzenKiezen();

        productService.productTeKoopAanbieden(gebruiker,
                Product.builder()
                        .soortArtikel(SoortArtikel.PRODUCT)
                        .productCategorie(productCategorie)
                        .naam(naam)
                        .prijs(prijs)
                        .beschrijving(beschrijving)
                        .gebruiker(gebruiker)
                        .bezorgopties(bezorgwijzenVoorProduct).build());
    }

    private ProductCategorie kiesProductCategorie() {
        //scanner = new Scanner(System.in);

        int keuze = -1;
        Arrays.asList(ProductCategorie.values())
                .forEach(productCategorie -> System.out.println(productCategorie.getId() + " " + productCategorie));
        System.out.print("Voer het nummer in van de categorie waar je product het beste in past: ");

        try {
            keuze = scanner.nextInt();
            if (keuze > ProductCategorie.values().length) {
                log.info("Dit nummer is geen keuze. Voer uw keuze opnieuw in.");
                kiesProductCategorie();
            }
            return Arrays.asList(ProductCategorie.values()).get(keuze-1);

        } catch (InputMismatchException e) {
            log.debug(e.getClass().getSimpleName() + " : " + e.getMessage());
            kiesProductCategorie();
        }
        return null;

    }

    //@TODO public maken
    public List bezorgwijzenKiezen() {
        List<Bezorgwijze> eigenBezorgWijzen = gebruikersService.vindEigenBezorgWijzen(gebruiker);
        //List<Bezorgwijze> eigenBezorgWijzen = gebruikersService.vindEigenBezorgWijzen(gebruikersService.zoekGebruiker(gebruiker.getEmailadres()));

        List<Bezorgwijze> bezorgWijzenVoorProduct = new ArrayList<>();

        System.out.println("Geef nu aan welke bezorgwijzen u wilt toevoegen voor dit product: ");
        Scanner scanner = new Scanner(System.in);

        for (Bezorgwijze bezorgwijze : eigenBezorgWijzen ) {
            System.out.println(bezorgwijze.getId() + "  ||  " + bezorgwijze.getOmschrijving());
            System.out.print("Deze bezorgwijze toevoegen voor dit product? J voor ja, N voor nee: ");
            String keuze = scanner.nextLine();
            if (keuze.equals("J")) bezorgWijzenVoorProduct.add(bezorgwijze);
        }

        if(bezorgWijzenVoorProduct.size()==0) {
            log.info("Je moet minimaal 1 bezorgwijze kiezen.");
            bezorgwijzenKiezen();
        }
        return bezorgWijzenVoorProduct;
    }

}