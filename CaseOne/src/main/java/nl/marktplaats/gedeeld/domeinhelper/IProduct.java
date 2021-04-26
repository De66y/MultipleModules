package nl.marktplaats.gedeeld.domeinhelper;

public interface IProduct extends IArtikel {
    ProductCategorie getProductCategorie();
    void setProductCategorie(ProductCategorie productCategorie);


}
