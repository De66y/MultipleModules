package nl.marktplaats.service;

import lombok.extern.log4j.Log4j2;
import nl.marktplaats.data.GebruikerDAO;
import nl.marktplaats.gedeeld.domeinmodel.Bezorgwijze;
import nl.marktplaats.gedeeld.domeinmodel.Gebruiker;
import nl.marktplaats.gedeeld.domeinmodel.Product;
import nl.marktplaats.gedeeld.helper.Wachtwoordgenerator;
import javax.persistence.NoResultException;
import java.util.List;

@Log4j2
public class GebruikersService {

    private GebruikerDAO gebruikerDAO;

    public GebruikersService(GebruikerDAO gebruikerDAO) {
        this.gebruikerDAO = gebruikerDAO;
    }

    //Methodes
    public String inloggen(String emailadres, String wachtwoord) {
        if (!emailadresBestaat(emailadres)) {
            return "Emailadres bestaat niet";
        } else if (!emailadresEnWachtwoordBestaat(emailadres, wachtwoord)) {
            return "Wachtwoord niet juist";
        }
        log.info(String.format("Inloggen succesvol met emailadres: %s", emailadres));
        return "Succesvol";
    }
    public Gebruiker registreren(String emailadres, String adres, List<Bezorgwijze> bezorgwijzen) {
        Gebruiker gebruiker =
                Gebruiker.builder()
                        .emailadres(emailadres)
                        .wachtwoord(new Wachtwoordgenerator().maakEenRandomWachtwoord())
                        .adres(adres)
                        .bezorgwijzen(bezorgwijzen)
                        .akkoordReglement("J").build();

        gebruikerDAO.opslaan(gebruiker);
        return gebruiker;

    }

    public void voegProductToe(Gebruiker gebruiker, Product product) {
        gebruikerDAO.voegProductToe(gebruiker, product);
        update(gebruiker);
    }
    public void verwijderProduct(Gebruiker gebruiker, Product product){
        gebruikerDAO.verwijderProduct(gebruiker, product);
    }

    //Zoekmethodes
    public boolean emailadresBestaat(String emailadres) {
        try {
            gebruikerDAO.zoekEmailadres(emailadres);
            return true;
        } catch (NoResultException e) {
            log.debug(e.getClass().getSimpleName() + " : " + e.getMessage());
            log.info("Het emailadres bestaat niet");
            return false;
        }
    }
    public Gebruiker zoekGebruiker(String emailadres) {
        try {
            return gebruikerDAO.zoekEmailadres(emailadres);
        } catch (NoResultException e) {
            log.debug(e.getClass().getSimpleName() + " : " + e.getMessage());
            log.info("Het emailadres bestaat niet");
            return null;
        }
    } //Alleen gebruiken als emailadres als true teruggegeven heeft
    public List<Bezorgwijze> vindEigenBezorgWijzen(Gebruiker gebruiker) {
        return gebruikerDAO.zoekEigenBezorgwijzen(gebruiker);
    }
    public List<Product> vindEigenProducten(Gebruiker gebruiker) {
        return gebruikerDAO.zoekEigenProducten(gebruiker);
    }

    //Hulpmethodes
    private boolean emailadresEnWachtwoordBestaat(String emailadres, String wachtwoord) {
        try {
            gebruikerDAO.zoekEmailadresEnWachtwoord(emailadres, wachtwoord);
            return true;
        } catch (NoResultException e) {
            log.debug(e.getClass().getSimpleName() + " : " + e.getMessage());
            log.info("Het wachtwoord is onjuist");
            return false;
        }
    }
    private void update(Gebruiker gebruiker) {
        gebruikerDAO.updateGebruiker(gebruiker);
    }
}
