package config;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

@SuppressWarnings({"squid:S6212", "squid:S6213"})
public class LogFormatter extends Formatter {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    @Override
    public String format(LogRecord record) {
        StringBuilder builder = new StringBuilder();
        builder.append(setColorByLogType(record));

        addTimeStamp(record, builder);
        addLevelMessage(record, builder);
        addMessage(record, builder);

        return builder.toString();
    }

    private void addMessage(LogRecord record, StringBuilder builder) {
        builder.append(" - ");
        builder.append(record.getMessage());

        Object[] params = record.getParameters();

        if (params != null) {
            builder.append("\t");
            for (int i = 0; i < params.length; i++) {
                builder.append(params[i]);
                if (i < params.length - 1) builder.append(", ");
            }
        }

        builder.append(ANSI_RESET);
        builder.append("\n");
    }

    private void addLevelMessage(LogRecord record, StringBuilder builder) {
        builder.append(" [");
        builder.append(setHeaderByLogType(record));
        builder.append("]");
    }

    private void addTimeStamp(LogRecord record, StringBuilder builder) {
        builder.append("[");
        builder.append(calcDate(record.getMillis()));
        builder.append("]");
    }

    private String calcDate(long milliseconds) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date resultDate = new Date(milliseconds);
        return dateFormat.format(resultDate);
    }

    private String setColorByLogType(LogRecord record) {
        return switch (record.getLevel().toString()) {
            case "INFO" -> ANSI_GREEN;
            case "WARNING" -> ANSI_PURPLE;
            case "SEVERE" -> ANSI_BLUE;
            default -> ANSI_RESET;
        };
    }

    private String setHeaderByLogType(LogRecord record) {
        return switch (record.getLevel().getName()) {
            case "INFO" -> "QUESTION";
            case "WARNING" -> "PROCESS";
            case "SEVERE" -> "IMPORTANT";
            default -> ANSI_RESET;
        };
    }
}
