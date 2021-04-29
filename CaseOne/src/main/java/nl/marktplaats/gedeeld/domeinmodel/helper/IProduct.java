package nl.marktplaats.gedeeld.domeinmodel.helper;

public interface IProduct extends IArtikel {
    ProductCategorie getProductCategorie();
    void setProductCategorie(ProductCategorie productCategorie);


}
