package nl.marktplaats.service;

import lombok.extern.log4j.Log4j2;
import nl.marktplaats.data.GebruikerDAO;
import nl.marktplaats.model.Gebruiker;

import javax.persistence.NoResultException;

@Log4j2
public class Gebruikersservice {

    private GebruikerDAO gebruikerDAO;

    public Gebruikersservice(GebruikerDAO gebruikerDAO) {
        this.gebruikerDAO = gebruikerDAO;
    }

    public void inloggen(String emailadres, String wachtwoord) {
        try {
            gebruikerDAO.zoekGebruikersnaamEnWachtwoord(emailadres, wachtwoord);
                    log.info(String.format("Inloggen succesvol met emailadres: %s", emailadres));
        } catch (NoResultException e){
            log.debug(e.getClass().getSimpleName() + " : " + e.getMessage());
            log.info("Het wachtwoord is onjuist");

        }


    }


}
