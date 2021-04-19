package org.example.data.dao;

import org.example.shared.model.IGebruiker;
import org.example.shared.model.Gebruiker;

import java.util.ArrayList;
import java.util.List;

//@TODO omzetten naar de database
public class GebruikerDAO {
    private List<IGebruiker> klanten = new ArrayList<>();

    public void setUp() {
        klanten.add(new Gebruiker("JamieF", "Outlander"));
        klanten.add(new Gebruiker("HarryPotter", "Hogwarts"));
    }

    public List<IGebruiker> getKlanten() {
        return klanten;
    }
    public void setKlanten(List<IGebruiker> klanten) {
        this.klanten = klanten;
    }

    public void addKlant(IGebruiker klant) {
        this.klanten.add(klant);
    }
}
