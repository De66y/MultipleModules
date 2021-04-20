package data.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import presentation.mainmenu.Hoofdmenu;
import shared.model.IGebruiker;
import shared.model.Gebruiker;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@TODO omzetten naar de database
public class GebruikerDAO {
    private final Logger LOGGER = LoggerFactory.getLogger(GebruikerDAO.class);
    public static final EntityManager em =
            Persistence.createEntityManagerFactory("TestNaam").createEntityManager();

    /*private List<IGebruiker> klanten = new ArrayList<>();

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
    }*/

    public boolean opslaan (Gebruiker gebruiker) {
        try {
            em.getTransaction().begin();
            em.persist(gebruiker);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            LOGGER.debug(e.getClass().getSimpleName() + " : " + e.getMessage());
            em.getTransaction().rollback();
            return false;
        } finally {
            em.clear();
            em.close();
        }
    }

    //@TODO nog testen
    public Gebruiker zoek(int id) {
        return em.find(Gebruiker.class, id);
    }

    //@TODO nog testen
    public Gebruiker zoekGebruikersnaam (String gebruikersnaam) {
        return em.find(Gebruiker.class, gebruikersnaam);
    }

    public Gebruiker zoekGebruikersnaamEnWachtwoord(String gebruikersnaam, String wachtwoord) throws NoResultException, NullPointerException {
        TypedQuery query = em.createNamedQuery("GebruikerEntity.zoekVolledigeGebruiker", Gebruiker.class);
        Gebruiker gebruiker = (Gebruiker) query.setParameter(gebruikersnaam, gebruikersnaam)
                .setParameter(wachtwoord, wachtwoord).getSingleResult();
        em.clear();
        em.close();
        return gebruiker;
    }

    public List<Gebruiker> alleGebruikers() {
        return em.createNamedQuery("GebruikerEntity.zoekallen", Gebruiker.class).getResultList();
    }

    //@TODO
    public void updaten() {
    }

    //@TODO
    public void verwijderen() {

    }
}
