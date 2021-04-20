package jpa;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name= "BoekEntity.zoekAllen", query= "SELECT b FROM Boek b")
})

@Entity
public class Boek {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String Titel;
    private String Auteur;

    public Boek() {
    }
    public Boek(String Titel, String Auteur) {
        this.Titel = Titel;
        this.Auteur = Auteur;
    }

    public int getId() {
        return id;
    }
    public String getTitel() {
        return Titel;
    }
    public void setTitel(String titel) {
        this.Titel = titel;
    }
    public String getAuteur() {
        return Auteur;
    }
    public void setAuteur(String author) {
        this.Auteur = author;
    }

    @Override
    public String toString() {
        return String.format("Id: %s  ||  Title: %s  ||  Author: %s \n", this.id, this.Titel, this.Auteur);
    }
}
