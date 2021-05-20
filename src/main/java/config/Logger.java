package config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;

@SuppressWarnings({"squid:S106", "squid:S6212"})
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Logger {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BOLD = "\u001B[1m";

    public static void systemInfo(String message, String... entity) {
        printMessage(message, ANSI_PURPLE, entity);
        System.out.println();
    }

    public static void ask(String message) {
        printMessage(message, ANSI_GREEN, "ASK");
    }

    public static void error(String message) {
        printMessage(message, ANSI_RED);
        System.out.println();
    }

    public static void info(String message) {
        printMessage(message, ANSI_BLUE);
        System.out.println();
    }

    private static void printMessage(String message, String color, String... headers) {
        var builder = new StringBuilder();

        addColor(builder, color);
        addTimeStamp(builder);
        addHeader(builder, headers);
        addColor(builder, color);
        addMessage(builder, message);

        System.out.print(builder);
    }

    private static void addColor(StringBuilder builder, String color) {
        builder.append(color);
    }

    private static void addHeader(StringBuilder builder, String... headers) {
        if (headers.length != 0) {
            builder.append(ANSI_BOLD);
            for (String header : headers) {
                builder.append("[");
                builder.append(header);
                builder.append("]");
            }
            builder.append(" ");
            builder.append(ANSI_RESET);
        }
    }

    private static void addMessage(StringBuilder builder, String message) {
        builder.append(message);
        builder.append(ANSI_RESET);
    }

    private static void addTimeStamp(StringBuilder builder) {
        builder.append("[");
        builder.append(calcDate(System.currentTimeMillis()));
        builder.append("] ");
    }

    private static String calcDate(long milliseconds) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date resultDate = new Date(milliseconds);
        return dateFormat.format(resultDate);
    }

    private  static String calcTime(long hour){
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Timer elapsedTime = new Timer(String.valueOf(hour));
        return timeFormat.format(elapsedTime);
    }
}
