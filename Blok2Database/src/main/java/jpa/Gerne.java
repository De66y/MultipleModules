package jpa;

public enum Gerne {
    FANTASY(1, "Fantasy"),
    ROMANCE(2, "Romance"),
    ACTIE(3, "Actie");

    private int id;
    private String omschrijving;

    Gerne(int id, String omschrijving) {
        this.id = id;
        this.omschrijving = omschrijving;
    }

    public int getId() {
        return id;
    }
    public String getOmschrijving() {
        return omschrijving;
    }

}
