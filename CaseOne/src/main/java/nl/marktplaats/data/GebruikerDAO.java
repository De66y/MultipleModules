package nl.marktplaats.data;

import lombok.extern.log4j.Log4j2;
import nl.marktplaats.gedeeld.domeinmodel.Bezorgwijze;
import nl.marktplaats.gedeeld.domeinmodel.Gebruiker;
import nl.marktplaats.gedeeld.domeinmodel.Product;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class GebruikerDAO {

    private final EntityManager em;

    public GebruikerDAO(EntityManager em) {
        this.em = em;
    }

    public void opslaan(Gebruiker gebruiker) {
        try {
            em.getTransaction().begin();
            em.persist(gebruiker);
            em.getTransaction().commit();
            log.info(String.format("Gebruiker met emailadres: %s is opgeslagen met wachtwoord: %s", gebruiker.getEmailadres(), gebruiker.getWachtwoord()));
        } catch (Exception e) {
            log.warn(e.getClass().getSimpleName() + " : " + e.getMessage());
            em.getTransaction().rollback();
        }
    }

    public Gebruiker zoek(int id) {
        try {
            Gebruiker gebruiker = em.find(Gebruiker.class, id);
            return gebruiker;
        } catch (NullPointerException e) {
            log.warn("Gebruiker met id %s bestaat niet", id);
            return null;
        }
    }

    public Gebruiker zoekEmailadres(String emailadres) {
        TypedQuery query = em.createNamedQuery("GebruikerEntity.zoekEmailadres", Gebruiker.class);
        return (Gebruiker) query.setParameter("emailadres", emailadres).getSingleResult();
    }

    public Gebruiker zoekEmailadresEnWachtwoord(String gebruikersnaam, String wachtwoord) {
        TypedQuery query = em.createNamedQuery("GebruikerEntity.zoekVolledigeGebruiker", Gebruiker.class);
        Gebruiker gebruiker = (Gebruiker) query.setParameter("gebruikersnaam", gebruikersnaam)
                .setParameter("wachtwoord", wachtwoord).getSingleResult();
        return gebruiker;
    }

    public List<Gebruiker> alleGebruikers() {
        return em.createNamedQuery("GebruikerEntity.alleGebruikers", Gebruiker.class).getResultList();
    }

    public void updateGebruiker(Gebruiker gebruiker) {
        try {
            em.getTransaction().begin();
            em.merge(gebruiker);
            em.getTransaction().commit();
            log.info(String.format("Nieuw product is opgeslagen", gebruiker.getProducten().get(gebruiker.getProducten().size()-1)));
        } catch (Exception e) {
            e.printStackTrace();
            //log.warn(e.getClass().getSimpleName() + " : " + e.getMessage());
            em.getTransaction().rollback();
        }
    }

    public List<Bezorgwijze> zoekEigenBezorgwijzen(Gebruiker gebruiker) {
        return gebruiker.getBezorgwijzen();
    }

    public List<Product> zoekEigenProducten(Gebruiker gebruiker) {
        return gebruiker.getProducten();
    }
}
