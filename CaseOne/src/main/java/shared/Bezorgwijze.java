package shared;

public enum Bezorgwijze {
    AFHALEN_MAGAZIJN(1, "Afhalen magazijn"),
    THUIS_AFHALEN_BIJ_VERKOPER(2, "Thuis afhalen bij verkoper"),
    VERSTUREN(3, "Versturen"),
    VERSTUREN_ONDER_REMBOURS(4, "Versturen onder rembours");

    private int id;
    private String omschrijving;

    Bezorgwijze(int id, String omschrijving) {
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
