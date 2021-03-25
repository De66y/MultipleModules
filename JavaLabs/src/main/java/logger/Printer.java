package logger;

public class Printer {
    private final InfoLogger logger;

    public Printer() {
        this.logger = new InfoLogger();
    }

    public void printsomeLogs() {
        logger.getLogger().info("Test123");
        logger.getLogger().debug("debuglog");
    }


}
