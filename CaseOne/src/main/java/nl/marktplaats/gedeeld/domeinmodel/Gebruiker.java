package nl.marktplaats.gedeeld.domeinmodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;


@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@NamedQueries({
        @NamedQuery(name= "GebruikerEntity.alleGebruikers", query= "SELECT e FROM Gebruiker e"),
        @NamedQuery(name= "GebruikerEntity.zoekEmailadres", query= "SELECT e FROM Gebruiker e WHERE e.emailadres=:emailadres"),
        @NamedQuery(name= "GebruikerEntity.zoekVolledigeGebruiker", query= "SELECT e FROM Gebruiker e WHERE e.emailadres=:gebruikersnaam AND e.wachtwoord=:wachtwoord")
})
public class Gebruiker {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String emailadres;
    private String wachtwoord;
    private String adres;
    private String akkoordReglement;

    @OneToMany
    private List<Bezorgwijze> bezorgwijzen;

    public Gebruiker(String emailadres, String wachtwoord) {
        this.emailadres = emailadres;
        this.wachtwoord = wachtwoord;
    }

    public void addBezorgwijze (Bezorgwijze bezorgwijze) {
        this.bezorgwijzen.add(bezorgwijze);
    }
}
