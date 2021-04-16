package lambdaAndStream.opdr1;

import java.util.List;

public class AppleService {
    void consumeApples(List<Apple> appleList, AppleConsumer appleConsumer) {
        for (Apple apple : appleList) {
            appleConsumer.accept(apple);
            System.out.println("Hap weg");
        }
    }
}
