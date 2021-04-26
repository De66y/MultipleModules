package nl.marktplaats.gedeeld.domeinmodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.marktplaats.gedeeld.domeinhelper.IProduct;
import nl.marktplaats.gedeeld.domeinhelper.ProductCategorie;
import nl.marktplaats.gedeeld.domeinhelper.SoortArtikel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@NamedQueries({
        @NamedQuery(name= "ProductEntity.geefAlleProducten", query= "SELECT e FROM Product e WHERE e.id=:id")
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
    @OneToMany
    private List<Bezorgwijze> bezorgopties;
    @OneToOne
    private Gebruiker gebruiker;

    public Product(ProductCategorie productCategorie, String naam, double prijs, String beschrijving) {
        this.productCategorie = productCategorie;
        this.naam = naam;
        this.prijs = prijs;
        this.beschrijving = beschrijving;
        this.bezorgopties = new ArrayList<>();
        this.soortArtikel = SoortArtikel.PRODUCT;
    }

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
