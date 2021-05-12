package config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Logger;

@SuppressWarnings("squid:S6212")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomLogger {
    public static Logger getLogger(Class<?> clazz) {
        Logger logger = Logger.getLogger(clazz.getName());
        logger.setUseParentHandlers(false);

        ConsoleHandler handler = new ConsoleHandler();
        Formatter formatter = new LogFormatter();

        handler.setFormatter(formatter);
        logger.addHandler(handler);

        return logger;
    }
}
