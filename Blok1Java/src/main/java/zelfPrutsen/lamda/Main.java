package zelfPrutsen.lamda;

import java.util.function.Function;
import java.util.function.IntFunction;

public class Main {
    public static void main(String[] args) {
        IntFunction<Integer> g2 = a -> a + 1;
        System.out.println(g2.apply(56));
    }
}
