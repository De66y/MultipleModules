package nl.marktplaats.gedeeld.domeinmodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@NamedQueries({
        @NamedQuery(name= "GebruikerEntity.alleGebruikers", query= "SELECT e FROM Gebruiker e"),
        @NamedQuery(name= "GebruikerEntity.zoekEmailadres", query= "SELECT e FROM Gebruiker e WHERE e.emailadres=:emailadres"),
        @NamedQuery(name= "GebruikerEntity.zoekVolledigeGebruiker", query= "SELECT e FROM Gebruiker e WHERE e.emailadres=:gebruikersnaam AND e.wachtwoord=:wachtwoord"),
})
public class Gebruiker {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String emailadres;
    private String wachtwoord;
    private String adres;
    private String akkoordReglement;

    @ManyToMany
    private List<Bezorgwijze> bezorgwijzen;

    @OneToMany (cascade = CascadeType.MERGE)
    @Builder.Default
    private List<Product> producten = new ArrayList<>();

    public Gebruiker(String emailadres, String wachtwoord) {
        this.emailadres = emailadres;
        this.wachtwoord = wachtwoord;
    }

    public void voegProductToe(Product product) {
        this.producten.add(product);
        product.setGebruiker(this);
    }

    //@TODO volgens mij doet hij het
    public void verwijderProduct(Product product) {
        product.setGebruiker(null);
        this.producten.remove(product);

    }
}
