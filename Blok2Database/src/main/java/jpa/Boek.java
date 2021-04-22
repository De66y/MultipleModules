package jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedQueries({
        @NamedQuery(name= "BoekEntity.zoekAllen", query= "SELECT b FROM Boek b")
})

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class Boek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String Titel;
    private String Auteur;

    @OneToMany //(fetch = FetchType.EAGER)
    private List<Gerne> Gernes;

    public Boek(String Titel, String Auteur) {
        this.Titel = Titel;
        this.Auteur = Auteur;
        Gernes = new ArrayList<>();
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

    public List<Gerne> getGernes() {
        return Gernes;
    }

    public void setGernes(Gerne gerne) {
        this.Gernes.add(gerne);
    }

    @Override
    public String toString() {
        return String.format("Id: %s  ||  Title: %s  ||  Author: %s  ||  Gerne: %s\n", this.id, this.Titel, this.Auteur, this.Gernes);
    }
}
