package jpa;

public class jpaApp {

    public static void main(String[] args) {
        new BoekDAO().opslaan(new Boek("Strange the Dreamer", "L. Taylor"));
        new BoekDAO().opslaan(new Boek("Outlander", "D. Gabaldon"));
        new BoekDAO().opslaan(new Boek("A Cort of Thorns and Roses", "Sarah J. Maas"));
        System.out.println(new BoekDAO().zoek(1));

        System.out.println(new BoekDAO().alleBoekenLijst());
    }




}
