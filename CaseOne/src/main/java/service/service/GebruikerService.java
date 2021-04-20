package service.service;

import data.dao.GebruikerDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shared.model.Gebruiker;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class GebruikerService {
    private GebruikerDAO gebruikerDAO;
    private final Logger LOGGER = LoggerFactory.getLogger(GebruikerDAO.class);

    public GebruikerService(GebruikerDAO gebruikerDAO) {
        this.gebruikerDAO = gebruikerDAO;
    }
/*
    public boolean emailAdresBestaat (String emailadres) {
        return gebruikerDAO.getKlanten().stream()
                .anyMatch(IGebruiker -> IGebruiker.getGebruikersnaam().equals(emailadres));
    }
    public void gebruikenToevoegenAanDatabase(String emailadres, String wachtwoord) {}
    public void gebruikenToevoegenAanDatabase(String emailadres, String wachtwoord, String adres) {}





    //JPA

    //@TODO vervangen voor degene in het commentaar
    @Override
    public boolean emailadresBestaat(String gebruikersnaam) {
        if (gebruikerDAO.zoek(gebruikersnaam) != null) return false;
        return true;
    }

    //@TODO vervangen voor degene in het commentaar
    @Override
    public boolean usernameAndPasswordExist(String gebruikersnaam, String wachtwoord) {
        if (gebruikerDAO.zoekGebruikersnaamEnWachtwoord(gebruikersnaam, wachtwoord))
        return false;
    }

    public String gebruikerOpslaan(Gebruiker gebruiker) {
        if(gebruikerDAO.opslaan(gebruiker)) {
            return String.format("Gebruiker: %s is opgeslagen", gebruiker.getGebruikersnaam());
        }

        return String.format("Er is iets misgegaan tijdens het opslaan van gebruiker: %s", gebruiker.getGebruikersnaam());
    } */

    public boolean zoekGebruikersnaamEnWachtwoord(String gebruikersnaam, String wachtwoord) {
        try {
            Gebruiker gebruiker = gebruikerDAO.zoekGebruikersnaamEnWachtwoord(gebruikersnaam, wachtwoord);
            return true;
        } catch (NoResultException e) {
            LOGGER.debug(e.getClass().getSimpleName() + " : " + e.getMessage());
            LOGGER.info("Het wachtwoord is onjuist");
            return false;
        }
    }

}
