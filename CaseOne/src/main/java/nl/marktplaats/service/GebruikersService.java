package nl.marktplaats.service;

import lombok.extern.log4j.Log4j2;
import nl.marktplaats.data.GebruikerDAO;
import nl.marktplaats.gedeeld.domeinmodel.Bezorgwijze;
import nl.marktplaats.gedeeld.domeinmodel.Gebruiker;
import nl.marktplaats.service.helper.Wachtwoordgenerator;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class GebruikersService {

    private GebruikerDAO gebruikerDAO;

    public GebruikersService(GebruikerDAO gebruikerDAO) {
        this.gebruikerDAO = gebruikerDAO;
    }

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

    //Alleen gebruiken als emailadres als true teruggegeven heeft
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

    public Gebruiker zoekGebruiker(String emailadres) {
        try {
            return gebruikerDAO.zoekEmailadres(emailadres);
        } catch (NoResultException e) {
            log.debug(e.getClass().getSimpleName() + " : " + e.getMessage());
            log.info("Het emailadres bestaat niet");
            return null;
        }
    }

    public void update(Gebruiker gebruiker) {
        gebruikerDAO.updateGebruiker(gebruiker);
    }

    public List<Bezorgwijze> vindEigenBezorgWijzen(Gebruiker gebruiker) {
        return gebruiker.getBezorgwijzen();
    }
}
