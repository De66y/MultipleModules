package nl.marktplaats.service;

import lombok.extern.log4j.Log4j2;
import nl.marktplaats.data.GebruikerDAO;

import javax.persistence.NoResultException;

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

    private boolean emailadresBestaat(String emailadres) {
        try {
            gebruikerDAO.zoekEmailadres(emailadres);
            return true;
        } catch (NoResultException e) {
            log.debug(e.getClass().getSimpleName() + " : " + e.getMessage());
            log.info("Het emailadres is onjuist");
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
