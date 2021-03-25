package logger;

import org.apache.logging.log4j.Logger;

public class AllLogger implements ILogger{
    private final Logger ALLLOGGER;

    public AllLogger(Logger ALLLOGGER) {
        this.ALLLOGGER = ALLLOGGER;
    }

    @Override
    public Logger getLogger() {
        return ALLLOGGER;
    }
}
