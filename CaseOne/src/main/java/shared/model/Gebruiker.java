package shared.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import shared.Bezorgwijze;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@TODO bezorgwijzen terug introduceren
@Entity
@Data @Builder
@AllArgsConstructor @NoArgsConstructor
@NamedQueries({
        @NamedQuery(name= "GebruikerEntity.zoekallen", query= "SELECT g FROM Gebruiker g"),
        @NamedQuery(name= "GebruikerEntity.zoekVolledigeGebruiker", query= "SELECT g FROM Gebruiker g WHERE g.gebruikersnaam=:gebruikersnaam AND g.wachtwoord=:wachtwoord")

})
public class Gebruiker implements IGebruiker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String gebruikersnaam;
    private String wachtwoord;
    private String adres;
    //private List<Bezorgwijze> bezorgwijzen;

    public Gebruiker(String gebruikersnaam, String wachtwoord) {
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }
    public String getWachtwoord() {
        return wachtwoord;
    }
    /*public List<Bezorgwijze> getBezorgwijzen() {
        return bezorgwijzen;
    }*/

    @Override
    public String toString() {
        return String.format("Id: %s  ||  Gebruikersnaam: %s  ||  Wachtwoord: %s  ||  Adres: %s", this.id, this.gebruikersnaam, this.wachtwoord, this.adres);
    }
}
