package zelfPrutsen.collw3resource;

import java.util.ArrayList;
import java.util.List;

public class ArrayLijst {
    public static void main(String[] args) {
        List<String> lijst = new ArrayList<>();
        lijst.add("Groen");
        lijst.add("Blauw");

        for (String ha : lijst) {
            System.out.println(ha);
    }
}
