package jpa;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class JpaApp {

    private static final EntityManager em = Persistence.createEntityManagerFactory("Productie").createEntityManager();

    public static void main(String[] args) {
        BoekDAO boekDAO = new BoekDAO(em);

        //Opslaan werkt
        //new BoekDAO(em).opslaan(new Boek("Strange the Dreamer", "L. Taylor"));
        //new BoekDAO(em).opslaan(new Boek("Outlander", "D. Gabaldon"));
        //new BoekDAO(em).opslaan(new Boek("A Court of Thorns and Roses", "Sarah J. Maas"));

        //Zoek werkt
        //System.out.println(boekDAO.zoek(8));

        //Alle boeken printen werkt
        //System.out.println(boekDAO.alleBoekenLijst());

        //Verwijderen werkt
        //boekDAO.verwijderen(5);

        //Updaten werkt
        //new BoekDAO(em).opslaan(new Boek("Lord Rings", "J.R.R. Tolkien"));
        //System.out.println(boekDAO.alleBoekenLijst());
        //boekDAO.updateTitel(1, "Strange the Dreamer");
        //System.out.println(boekDAO.alleBoekenLijst());

    }




}
