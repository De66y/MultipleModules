package nl.marktplaats.gedeeld.domeinmodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.marktplaats.gedeeld.domeinmodel.helper.IProduct;
import nl.marktplaats.gedeeld.domeinmodel.helper.ProductCategorie;
import nl.marktplaats.gedeeld.domeinmodel.helper.SoortArtikel;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@NamedQueries({
        @NamedQuery(name= "ProductEntity.geefAlleProducten", query= "SELECT e FROM Product e")
})
public class Product implements IProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private SoortArtikel soortArtikel;
    @Enumerated(EnumType.STRING)
    private ProductCategorie productCategorie;
    private String naam;
    private double prijs;
    private String beschrijving;
    @ManyToMany
    @Builder.Default
    private List<Bezorgwijze> bezorgopties = new ArrayList<>();
    @OneToOne
    private Gebruiker gebruiker;

    @Override public ProductCategorie getProductCategorie() {
        return productCategorie;
    }
    @Override public void setProductCategorie(ProductCategorie productCategorie) {
        this.productCategorie = productCategorie;
    }

    @Override public int getId() {
        return id;
    }

    @Override public SoortArtikel getSoortArtikel() {
        return soortArtikel;
    }

    @Override public String getNaam() {
        return naam;
    }
    @Override public void setNaam(String naam) {
        this.naam = naam;
    }

    @Override public double getPrijst() {
        return prijs;
    }
    @Override public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    @Override public String getBeschrijving() {
        return beschrijving;
    }
    @Override public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    @Override public List<Bezorgwijze> getBezorgopties() {
        return bezorgopties;
    }

    @Override public void setBezorgopties(List<Bezorgwijze> bezorgopties) {
        this.bezorgopties = bezorgopties;
    }

    public Gebruiker getGebruiker() {
        return gebruiker;
    }
    public void setGebruiker(Gebruiker gebruiker) {
        this.gebruiker = gebruiker;
    }
}
