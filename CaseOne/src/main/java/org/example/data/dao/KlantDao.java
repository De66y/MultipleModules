package org.example.data.dao;

import org.example.shared.model.Klant;

import java.util.ArrayList;
import java.util.List;

//@TODO
public class KlantDao {
    private List<Klant> klanten = new ArrayList<>();

    public void setUp() {
        klanten.add(new Klant("JamieF", "Outlander"));
        klanten.add(new Klant("HarryPotter", "Hogwarts"));
    }

    public List<Klant> getKlanten() {
        return klanten;
    }
    public void setKlanten(List<Klant> klanten) {
        this.klanten = klanten;
    }

    public void addKlant(Klant klant) {
        this.klanten.add(klant);
    }
}
