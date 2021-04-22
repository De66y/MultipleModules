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
    private List<Genre> genres;

    public Boek(String Titel, String Auteur) {
        this.Titel = Titel;
        this.Auteur = Auteur;
        genres = new ArrayList<>();
    }


    public List<Genre> getGenres() {
        return genres;
    }
    public void setGenres(Genre genre) {
        this.genres.add(genre);
    }

    @Override
    public String toString() {
        return String.format("Id: %s  ||  Title: %s  ||  Author: %s  \n", this.id, this.Titel, this.Auteur);
    }
}
