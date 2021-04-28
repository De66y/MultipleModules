package nl.marktplaats.gedeeld.domeinhelper;

public enum ProductCategorie {
    BOEKEN (1),
    DIERBENODIGDHEDEN (2),
    DUIKBENODIGDHEDEN (3),
    HIKINGGEAR (4);

    private int id;

    ProductCategorie(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
