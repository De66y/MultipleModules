package nl.marktplaats.gedeeld.domeinmodel;

import nl.marktplaats.gedeeld.domeinhelper.IProduct;
import nl.marktplaats.gedeeld.domeinhelper.ProductCategorie;
import nl.marktplaats.gedeeld.domeinhelper.SoortArtikel;

import java.util.List;

public class Product implements IProduct {

    @Override
    public ProductCategorie getProductCategorie() {
        return null;
    }

    @Override
    public void setProductCategorie(ProductCategorie productCategorie) {

    }

    @Override
    public SoortArtikel getSoortArtikel() {
        return null;
    }

    @Override
    public void setSoortArtikel(SoortArtikel soortArtikel) {

    }

    @Override
    public String getNaam() {
        return null;
    }

    @Override
    public void setNaam(String naam) {

    }

    @Override
    public double getPrijst() {
        return 0;
    }

    @Override
    public void setPrijs(double prijs) {

    }

    @Override
    public String getBeschrijving() {
        return null;
    }

    @Override
    public void setBeschrijving(String beschrijving) {

    }

    @Override
    public List<Bezorgwijze> getBezorgopties() {
        return null;
    }

    @Override
    public void setBezorgopties(Bezorgwijze bezorgwijze) {

    }
}
