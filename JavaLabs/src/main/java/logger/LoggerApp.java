package logger;

import org.slf4j.Logger;

public class LoggerApp {
    private Logger logger;

    public LoggerApp(Logger logger) {
        this.logger = logger;
    }

    public void printsomeLogs(String string) {
        logger.info(string);
        logger.debug("debugstring");

    }


}
