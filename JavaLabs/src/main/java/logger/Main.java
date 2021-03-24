package logger;

import org.slf4j.LoggerFactory;

public class Main {

    public static void main(String[] args) {
        LoggerApp logger = new LoggerApp(LoggerFactory.getLogger(LoggerApp.class));
        logger.printsomeLogs("Joehoej");
    }
}
