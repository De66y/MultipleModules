package nl.marktplaats.service;

import nl.marktplaats.data.BezorgwijzeDAO;
import nl.marktplaats.gedeeld.domeinmodel.Bezorgwijze;
import java.util.List;

public class BezorgwijzeService {
    private BezorgwijzeDAO bezorgwijzeDAO;

    public BezorgwijzeService(BezorgwijzeDAO bezorgwijzeDAO) {
        this.bezorgwijzeDAO = bezorgwijzeDAO;
    }

    //Methodes
    public boolean opslaan (Bezorgwijze bezorgwijze) {
        if (VierOfMeerBezorgwijzen()) return false;
        bezorgwijzeDAO.opslaan(bezorgwijze);
        return true;
    }

    //Zoekmethodes
    public List<Bezorgwijze> alleBezorgwijzen() {
        return bezorgwijzeDAO.alleBezorgwijzen();
    }

    //Hulpmethodes
    private boolean VierOfMeerBezorgwijzen() {
        int aantalBezorgwijzen = bezorgwijzeDAO.alleBezorgwijzen().size();
        if (aantalBezorgwijzen < 4) return false;
        return true;
    }
}
