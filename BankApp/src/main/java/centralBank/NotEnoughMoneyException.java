package centralBank;

public class NotEnoughMoneyException extends RuntimeException {

    public NotEnoughMoneyException() {
        super("Er staat niet genoeg op uw bankrekening om deze transactie uit te voeren");
    }
}
