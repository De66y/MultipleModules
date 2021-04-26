package nl.marktplaats.gedeeld.domeinhelper;

import nl.marktplaats.gedeeld.domeinmodel.Bezorgwijze;

import java.util.List;

public interface IArtikel {
    int getId();

    SoortArtikel getSoortArtikel();

    String getNaam();
    void setNaam(String naam);

    double getPrijst();
    void setPrijs(double prijs);

    String getBeschrijving();
    void setBeschrijving(String beschrijving);

    List<Bezorgwijze> getBezorgopties();
    void setBezorgopties(List<Bezorgwijze> bezorgopties);
}
