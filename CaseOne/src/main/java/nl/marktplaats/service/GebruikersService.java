package nl.marktplaats.service;

import lombok.extern.log4j.Log4j2;
import nl.marktplaats.data.BezorgwijzeEnum;
import nl.marktplaats.data.GebruikerDAO;
import nl.marktplaats.gedeeld.domeinmodel.Gebruiker;
import nl.marktplaats.service.helper.Wachtwoordgenerator;

import javax.persistence.NoResultException;
import java.util.List;

@Log4j2
public class GebruikersService {

    private GebruikerDAO gebruikerDAO;

    public GebruikersService(GebruikerDAO gebruikerDAO) {
        this.gebruikerDAO = gebruikerDAO;
    }

    public String inloggen(String emailadres, String wachtwoord) {
        if (!emailadresBestaat(emailadres))  {
            return "EN";
        } else if (!emailadresEnWachtwoordBestaat(emailadres, wachtwoord)){
            return "WN";
        }
        log.info(String.format("Inloggen succesvol met emailadres: %s", emailadres));
        return "S";
    }

    public Gebruiker registreren (String emailadres, String adres) {
        Gebruiker gebruiker =
        Gebruiker.builder()
                .emailadres(emailadres)
                .wachtwoord(new Wachtwoordgenerator().maakEenRandomWachtwoord())
                .adres(adres)
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
}
