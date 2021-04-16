package lambdaAndStream.opdr1;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        List<Apple> appleList = new ArrayList<>();
        appleList.add(new Apple("Pink Lady", 156));
        appleList.add(new Apple ("Granny Smith", 203));
        appleList.add(new Apple("Jonagold", 132));

        new AppleService().consumeApples(appleList, new ApplePrinter());
        new AppleService().consumeApples(appleList, new AppleConsumer() {
            @Override
            public void accept(Apple apple) {

            }
        });

        System.out.println();

    }


}
