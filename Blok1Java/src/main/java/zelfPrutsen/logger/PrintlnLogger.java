package zelfPrutsen.logger;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PrintlnLogger {
    private final Logger PRINTLNLOGGER;

    public PrintlnLogger() {
        this.PRINTLNLOGGER = LogManager.getLogger("hi");
    }

    public Logger getPRINTLNLOGGER() {
        return PRINTLNLOGGER;
    }

    public void print(String text) {
        getPRINTLNLOGGER().log(Level.forName("PRINTLN", 90), text);
    }
}

