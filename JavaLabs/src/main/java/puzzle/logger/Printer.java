package puzzle.logger;

public class Printer {
    private final PrintlnLogger PRINTLNLOGGER;

    public Printer() {
        this.PRINTLNLOGGER = new PrintlnLogger();
    }

    public void printsomeLogs() {
        PRINTLNLOGGER.print("Println in methode");

    }


}
