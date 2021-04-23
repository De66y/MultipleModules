package nl.marktplaats.gedeeld.domeinmodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NamedQueries({
        @NamedQuery(name= "BezorgwijzeEntity.alleBezorgwijzen", query= "SELECT e FROM Bezorgwijze e"),
        @NamedQuery(name= "GebruikerEntity.zoekBezorgwijze", query= "SELECT e FROM Bezorgwijze e WHERE e.omschrijving=:omschrijving")
})
public class Bezorgwijze {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String omschrijving;

    public Bezorgwijze(String omschrijving) {
        this.omschrijving = omschrijving;
    }
}
