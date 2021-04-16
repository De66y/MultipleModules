package lambdaAndStream.opdr1;

public class ApplePrinter implements AppleConsumer{

    @Override
    public void accept(Apple apple) {
        System.out.println(apple.getMerk() + " " + apple.getGewicht());
    }
}
