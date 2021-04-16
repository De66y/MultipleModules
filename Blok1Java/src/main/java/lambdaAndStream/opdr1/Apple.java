package lambdaAndStream.opdr1;

public class Apple {
    private String merk;
    private int gewicht;

    public Apple(String merk, int gewicht) {
        this.merk = merk;
        this.gewicht = gewicht;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public int getGewicht() {
        return gewicht;
    }

    public void setGewicht(int gewicht) {
        this.gewicht = gewicht;
    }
}
