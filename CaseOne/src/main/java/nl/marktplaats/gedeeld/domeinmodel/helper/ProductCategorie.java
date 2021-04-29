package nl.marktplaats.gedeeld.domeinmodel.helper;

public enum ProductCategorie {
    BOEKEN (1),
    DIERBENODIGDHEDEN (2),
    DUIKBENODIGDHEDEN (3),
    HIKINGGEAR (4),
    OVERIG (5);

    private int id;

    ProductCategorie(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
