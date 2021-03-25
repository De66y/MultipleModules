package logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InfoLogger implements ILogger{
    private final Logger INFOLOGGER;

    public InfoLogger() {
        this.INFOLOGGER = LogManager.getLogger("infoLogger");
    }

    @Override
    public Logger getLogger() {
        return INFOLOGGER;
    }
}
