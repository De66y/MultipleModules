package jpa;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class JpaApp {

    private static final EntityManager em = Persistence.createEntityManagerFactory("Productie").createEntityManager();

    public static void main(String[] args) {
        BoekDAO boekDAO = new BoekDAO(em);
        //new BoekDAO(em).opslaan(new Boek("Strange the Dreamer", "L. Taylor"));
        //new BoekDAO(em).opslaan(new Boek("Outlander", "D. Gabaldon"));
        //new BoekDAO(em).opslaan(new Boek("A Court of Thorns and Roses", "Sarah J. Maas"));
        System.out.println(boekDAO.zoek(1));

        System.out.println(boekDAO.alleBoekenLijst());
    }




}
