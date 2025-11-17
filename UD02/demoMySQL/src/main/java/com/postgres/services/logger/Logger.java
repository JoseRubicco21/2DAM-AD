package com.postgres.services.logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.postgres.services.logger.enums.Colors;
import com.postgres.services.logger.enums.LogLevel;


/**
 * A comprehensive logging utility class with ANSI color support for terminal output.
 */
public class Logger {

    /** Date and time formatter for timestamps. */
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    /** Flag to control timestamp display in log messages. */
    private static boolean enableTimestamp = true;

    /** Flag to control color output in log messages. */
    private static boolean enableColors = true;

    /**
     * Logs a debug message.
     */
    public static void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    /**
     * Logs a database query message.
     */
    public static void query(String message) {
        log(LogLevel.QUERY, message);
    }

    /**
     * Logs an informational message.
     */
    public static void info(String message) {
        log(LogLevel.INFO, message);
    }

    /**
     * Logs a success message.
     */
    public static void success(String message) {
        log(LogLevel.SUCCESS, message);
    }

    /**
     * Logs a warning message.
     */
    public static void warning(String message) {
        log(LogLevel.WARNING, message);
    }

    /**
     * Logs an error message.
     */
    public static void error(String message) {
        log(LogLevel.ERROR, message);
    }

    /**
     * Logs a critical message.
     */
    public static void critical(String message) {
        log(LogLevel.CRITICAL, message);
    }

    /**
     * Logs a network-related message.
     */
    public static void network(String message) {
        log(LogLevel.NETWORK, message);
    }

    /**
     * Logs a message with custom foreground and background colors.
     */
    public static void log(String message, Colors foreground, Colors background) {
        print(message, foreground, background);
    }

    /**
     * Logs a message with the specified log level.
     */
    public static void log(LogLevel level, String message) {
        String timestamp = enableTimestamp
                ? Colors.BRIGHT_BLACK.getCode() + "[" + LocalDateTime.now().format(TIME_FORMATTER) + "] "
                        + Colors.RESET.getCode()
                : "";

        String levelTag = enableColors
                ? level.getBackground().getCode() + level.getForeground().getCode() +
                        " " + level.name() + " " + Colors.RESET.getCode() + " "
                : "[" + level.name() + "] ";

        String coloredMessage = enableColors ? level.getForeground().getCode() + message + Colors.RESET.getCode()
                : message;

        System.out.println(timestamp + levelTag + coloredMessage);
    }

    /**
     * Internal utility method for printing messages with custom formatting.
     */
    private static void print(String message, Colors foreground, Colors background) {
        StringBuilder output = new StringBuilder();

        if (enableTimestamp) {
            output.append(Colors.BRIGHT_BLACK.getCode())
                    .append("[").append(LocalDateTime.now().format(TIME_FORMATTER)).append("] ")
                    .append(Colors.RESET.getCode());
        }

        if (enableColors) {
            if (background != null) {
                output.append(background.getCode());
            }
            if (foreground != null) {
                output.append(foreground.getCode());
            }
        }

        output.append(message);

        if (enableColors) {
            output.append(Colors.RESET.getCode());
        }

        System.out.println(output.toString());
    }

    /**
     * Enables or disables timestamp display.
     */
    public static void setTimestampEnabled(boolean enabled) {
        enableTimestamp = enabled;
    }

    /**
     * Enables or disables color output.
     */
    public static void setColorsEnabled(boolean enabled) {
        enableColors = enabled;
    }
}
