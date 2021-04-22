package nl.marktplaats;

import nl.marktplaats.data.GebruikerDAO;
import nl.marktplaats.model.Gebruiker;
import nl.marktplaats.service.Gebruikersservice;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class MarktplaatsApp {

    private static final EntityManager em = Persistence.createEntityManagerFactory("Productie").createEntityManager();

    public static void main(String[] args) {
        //Voor testing
        GebruikerDAO gebruikerDAO = new GebruikerDAO(em);
        Gebruikersservice gebruikersservice = new Gebruikersservice(gebruikerDAO);

        //gebruikerDAO.opslaan(new Gebruiker("Calimero", "Wachtwoord"));
        //gebruikerDAO.opslaan(new Gebruiker("Dotje", "Neus"));
        //gebruikerDAO.opslaan(new Gebruiker("Beer", "SnufSnuf"));

        //System.out.println(gebruikerDAO.zoekGebruikersnaamEnWachtwoord("Dotje","Neus"));
        gebruikersservice.inloggen("Dotje", "Neus");


    }
}
