package puzzle.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ATM {

    private final Logger LOGGER = LoggerFactory.getLogger(ATM.class);

    public void print() {
        LOGGER.debug("Debug test");
        LOGGER.info("Info test");
        LOGGER.warn("Warning test");
        LOGGER.error("Error test");
    }
}
