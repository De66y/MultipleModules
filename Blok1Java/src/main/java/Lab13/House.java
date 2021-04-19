package Lab13;

public class House<T extends Human> {
    private T owner;

    @Override
    public String toString() {
        return String.format("This house is owned by [%s] and is says %s.",owner, owner.greet());
    }
}
