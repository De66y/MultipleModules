package org.example.shared.model;

import lombok.Builder;
import org.example.shared.Bezorgwijze;
import java.util.ArrayList;
import java.util.List;


//@TODO omzetten naar database
public class Gebruiker implements IGebruiker {
    private String gebruikersnaam;
    private String wachtwoord;
    private String adres;
    private List<Bezorgwijze> bezorgwijzen;

    public Gebruiker(String gebruikersnaam, String wachtwoord) {
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
        bezorgwijzen = new ArrayList<>();
    }
    public Gebruiker (String gebruikersnaam, String wachtwoord, String adres) {
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
        this.adres = adres;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }
    public String getWachtwoord() {
        return wachtwoord;
    }
    public List<Bezorgwijze> getBezorgwijzen() {
        return bezorgwijzen;
    }
}
