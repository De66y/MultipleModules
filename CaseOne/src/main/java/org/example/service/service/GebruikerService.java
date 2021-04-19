package org.example.service.service;

import org.example.data.dao.GebruikerDAO;
import org.example.shared.model.Gebruiker;

public class GebruikerService implements IControleerGebruikergegevens, IRegistreerGebruiker {
    private GebruikerDAO gebruikerDAO;

    public GebruikerService(GebruikerDAO gebruikerDAO) {
        this.gebruikerDAO = gebruikerDAO;
    }

    public boolean emailadresBestaat(String username) {
        return gebruikerDAO.getKlanten().stream()
                .anyMatch(IGebruiker -> IGebruiker.getGebruikersnaam().equals(username));

    }

    public boolean usernameAndPasswordExist (String gebruikersnaam, String wachtwoord) {
        return gebruikerDAO.getKlanten().stream()
                .anyMatch(IGebruiker -> IGebruiker.getGebruikersnaam().equals(gebruikersnaam)
                && IGebruiker.getWachtwoord().equals(wachtwoord));

    }

    public boolean emailAdresBestaat (String emailadres) {
        return gebruikerDAO.getKlanten().stream()
                .anyMatch(IGebruiker -> IGebruiker.getGebruikersnaam().equals(emailadres));
    }

    public void gebruikenToevoegenAanDatabase(String emailadres, String wachtwoord) {}
    public void gebruikenToevoegenAanDatabase(String emailadres, String wachtwoord, String adres) {}
}
