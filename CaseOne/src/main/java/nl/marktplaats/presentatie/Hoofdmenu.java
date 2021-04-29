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
        boolean hoofdMenuDraait = true;
        try {
            do {
                System.out.printf("U bent in het %s.\nWaarmee kan ik u van dienst zijn: \n" +
                                "1. Product toevoegen\n" +
                                "2. Mijn producten inzien\n" +
                                "3. -----UNDER CONSTRUCTION Product toevoegen aan mijn verlanglijstje-----\n" +
                                "4. Product verwijderen\n" +
                                "5. Terug naar het aanmeldmenu \n"
                        , this.getClass().getSimpleName());
                System.out.print("Uw keuze: ");
                keuze = scanner.nextInt();

                switch (keuze) {
                    case 1:
                        showSubMenu(new Scanner(System.in));
                        break;
                    case 2:
                        mijnPoductenInzien();
                        break;
                    case 3:
                        log.error("UNDER CONSTRUCTION");
                        break;
                    case 4:
                        mijnProductVerwijderen();
                        break;
                        case 5:
                            System.out.println("Tot de volgende keer :)");
                            hoofdMenuDraait = false;
                            break;
                    default:
                        log.info("U heeft een keuze gemaakt die niet bestaat, kies opnieuw: ");
                        showMenu(new Scanner(System.in));
                }
            } while (hoofdMenuDraait);
        } catch (InputMismatchException e) {
            log.debug(e.getClass().getSimpleName() + " : " + e.getMessage());
            new Hoofdmenu(gebruiker, productService, gebruikersService, new Scanner(System.in));
        }
    }

    @Override
    public void showSubMenu(Scanner scanner) {
        String naam = vraagNaamProduct();
        String beschrijving = vraagBeschrijvingProduct();
        double prijs = vraagPrijsProduct();

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
    private String vraagNaamProduct() {
        String naam = "";
        while (naam.isEmpty()){
            System.out.print("Geef de naam van het artikel: ");
            naam = scanner.nextLine();
        }
        return naam;
    }
    private String vraagBeschrijvingProduct() {
        System.out.print("Geef een beschrijving van het artikel: ");
        return scanner.nextLine();

    }
    //@TODO mooier maken. leeg laten en letter invoeren
    private double vraagPrijsProduct() {
        double prijs = 0.0;
        try {
            while (prijs == 0.0) {
                System.out.print("Wat wordt de vraagprijs: ");
                prijs = scanner.nextDouble();
            }
        }catch (InputMismatchException e) {
            log.warn("Dit is geen geldig invoer.");
            showSubMenu(new Scanner(System.in));
        }
        return prijs;
    }
    //@TODO mooier maken
    private ProductCategorie kiesProductCategorie() {
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
        }
        return null;

    }
    private List bezorgwijzenKiezen() {
        List<Bezorgwijze> eigenBezorgWijzen = gebruikersService.vindEigenBezorgWijzen(gebruiker);
        List<Bezorgwijze> bezorgwijzenVoorProduct = new ArrayList<>();

        while (bezorgwijzenVoorProduct.size()==0) {
            System.out.println("Geef nu aan welke bezorgwijzen u wilt toevoegen voor dit product: ");
            Scanner scanner = new Scanner(System.in);

            for (Bezorgwijze bezorgwijze : eigenBezorgWijzen) {
                System.out.println(bezorgwijze.getId() + "  ||  " + bezorgwijze.getOmschrijving());
                System.out.print("Deze bezorgwijze toevoegen voor dit product? J voor ja, N voor nee: ");
                String keuze = scanner.nextLine();
                if (keuze.equals("J")) bezorgwijzenVoorProduct.add(bezorgwijze);
            }
            if (bezorgwijzenVoorProduct.size() == 0) log.info("Je moet minimaal 1 bezorgwijze kiezen.");
        }
        return bezorgwijzenVoorProduct;
    }

    public void mijnPoductenInzien() {
        gebruikersService.vindEigenProducten(gebruiker)
                .stream()
                .forEach(product -> System.out.println(product.getId() +" || "+ product.getNaam() + " || " + product.getPrijs()));
    }
    public void mijnProductVerwijderen() {
        mijnPoductenInzien();
        System.out.print("Geef het nummer op van het product welke je wilt verwijderen: ");
        int keuze = scanner.nextInt();
        productService.verwijderProduct(gebruiker, productService.zoekProduct(keuze));
    }

}
