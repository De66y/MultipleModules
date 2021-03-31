package zelfPrutsen.compare;

public class Main {

    public static void main(String[] args) {
        Double a = 1.23;
        Double b = 1.23;
        System.out.println(a == b);  //Wrapper class, verschillende adressen
        System.out.println(a.equals(b)); //Values gelijk?
        System.out.println("");

        int c = 4;
        int d = 4;
        System.out.println(a == b);
        System.out.println(a.equals(b));
        System.out.println("");

        int cijfer = 20;
        System.out.println(cijfer==20); //Waardes gelijk?
        System.out.println("");
    }
}
