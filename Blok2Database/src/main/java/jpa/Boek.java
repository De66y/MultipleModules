package jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @OneToMany
    private List<Gerne> gerneLijst;

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

    public List<Gerne> getGerneLijst() {
        return gerneLijst;
    }

    public void setGerneLijst(Gerne gerne) {
        this.gerneLijst.add(gerne);
    }

    @Override
    public String toString() {
        return String.format("Id: %s  ||  Title: %s  ||  Author: %s  ||  Gerne: %s\n", this.id, this.Titel, this.Auteur, this.gerneLijst);
    }
}
