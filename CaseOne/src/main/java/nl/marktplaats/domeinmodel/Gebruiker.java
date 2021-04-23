package nl.marktplaats.domeinmodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@NamedQueries({
        @NamedQuery(name= "GebruikerEntity.alleGebruikers", query= "SELECT e FROM Gebruiker e"),
        @NamedQuery(name= "GebruikerEntity.zoekVolledigeGebruiker", query= "SELECT g FROM Gebruiker g WHERE g.emailadres=:gebruikersnaam AND g.wachtwoord=:wachtwoord")
})
public class Gebruiker {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String emailadres;
    private String wachtwoord;

    public Gebruiker(String emailadres, String wachtwoord) {
        this.emailadres = emailadres;
        this.wachtwoord = wachtwoord;
    }
}
