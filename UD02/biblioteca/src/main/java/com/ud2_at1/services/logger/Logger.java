package com.ud2_at1.services.logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.ud2_at1.services.logger.enums.Colors;
import com.ud2_at1.services.logger.enums.LogLevel;

/* IMPORTANT this should be done using logcat, better solution than this. */

/**
 * A comprehensive logging utility class with ANSI color support for terminal
 * output.
 * 
 * <p>
 * This class provides various logging levels with predefined color schemes,
 * custom color logging methods, and special formatting utilities like rainbow
 * text
 * and progress bars. All methods are static for easy access throughout the
 * application.
 * 
 * <p>
 * Features include:
 * <ul>
 * <li>Multiple log levels (DEBUG, INFO, SUCCESS, WARNING, ERROR, CRITICAL)</li>
 * <li>Timestamp support (configurable)</li>
 * <li>Color support (configurable)</li>
 * <li>Custom color combinations</li>
 * <li>Special effects (rainbow text, progress bars)</li>
 * <li>Utility methods (separators, headers)</li>
 * </ul>
 * 
 * <p>
 * Example usage:
 * 
 * <pre>{@code
 * Logger.info("Application started");
 * Logger.error("An error occurred");
 * Logger.log("Custom colored text", Colors.RED, Colors.BG_YELLOW);
 * Logger.rainbow("Colorful message!");
 * }</pre>
 * 
 * @author GitHub Copilot & JoseRubicco 21
 * @version 1.0
 * @since 1.0
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
     * Debug messages are typically used for detailed diagnostic information.
     * 
     * @param message the debug message to log
     */
    public static void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    /**
     * Logs a database query message.
     * Query messages are used for logging SQL queries and database operations.
     * 
     * @param message the query message to log
     */
    public static void query(String message) {
        log(LogLevel.QUERY, message);
    }

    /**
     * Logs an informational message.
     * Info messages are used for general information about application flow.
     * 
     * @param message the informational message to log
     */
    public static void info(String message) {
        log(LogLevel.INFO, message);
    }

    /**
     * Logs a success message.
     * Success messages indicate successful completion of operations.
     * 
     * @param message the success message to log
     */
    public static void success(String message) {
        log(LogLevel.SUCCESS, message);
    }

    /**
     * Logs a warning message.
     * Warning messages indicate potential issues that don't prevent execution.
     * 
     * @param message the warning message to log
     */
    public static void warning(String message) {
        log(LogLevel.WARNING, message);
    }

    /**
     * Logs an error message.
     * Error messages indicate problems that may affect application functionality.
     * 
     * @param message the error message to log
     */
    public static void error(String message) {
        log(LogLevel.ERROR, message);
    }

    /**
     * Logs a critical message.
     * Critical messages indicate severe problems that may cause application
     * failure.
     * 
     * @param message the critical message to log
     */
    public static void critical(String message) {
        log(LogLevel.CRITICAL, message);
    }

    /**
     * Logs a network-related message.
     * Network messages are used for logging network operations and events.
     * 
     * @param message the network message to log
     */
    public static void network(String message) {
        log(LogLevel.NETWORK, message);
    }

    /**
     * Logs a message with custom foreground color.
     * 
     * @param message    the message to log
     * @param foreground the foreground color to use
     */
    public static void info(String message, Colors foreground) {
        print(message, foreground, null, null);
    }

    /**
     * Logs a message with custom foreground and background colors.
     * 
     * @param message    the message to log
     * @param foreground the foreground color to use
     * @param background the background color to use
     */
    public static void log(String message, Colors foreground, Colors background) {
        print(message, foreground, background, null);
    }

    /**
     * Logs a message with custom foreground color, background color, and text
     * style.
     * 
     * @param message    the message to log
     * @param foreground the foreground color to use
     * @param background the background color to use
     * @param style      the text style to apply (bold, italic, etc.)
     */
    public static void log(String message, Colors foreground, Colors background, Colors style) {
        print(message, foreground, background, style);
    }

    /**
     * Logs a message with the specified log level.
     * This is the main logging method that formats the message with timestamp,
     * log level tag, and appropriate colors.
     * 
     * @param level   the log level
     * @param message the message to log
     */
    public static void log(LogLevel level, String message) {
        String timestamp = enableTimestamp
                ? Colors.BRIGHT_BLACK.getCode() + "[" + LocalDateTime.now().format(TIME_FORMATTER) + "] "
                        + Colors.RESET.getCode()
                : "";

        String levelTag = enableColors
                ? level.getBackground().getCode() + level.getForeground().getCode() + Colors.BOLD.getCode() +
                        " " + level.name() + " " + Colors.RESET.getCode() + " "
                : "[" + level.name() + "] ";

        String coloredMessage = enableColors ? level.getForeground().getCode() + message + Colors.RESET.getCode()
                : message;

        System.out.println(timestamp + levelTag + coloredMessage);
    }

    /**
     * Internal utility method for printing messages with custom formatting.
     * 
     * @param message    the message to print
     * @param foreground the foreground color (can be null)
     * @param background the background color (can be null)
     * @param style      the text style (can be null)
     */
    private static void print(String message, Colors foreground, Colors background, Colors style) {
        if (!enableColors) {
            System.out.println(message);
            return;
        }

        StringBuilder output = new StringBuilder();

        if (background != null) {
            output.append(background.getCode());
        }
        if (foreground != null) {
            output.append(foreground.getCode());
        }
        if (style != null) {
            output.append(style.getCode());
        }

        output.append(message).append(Colors.RESET.getCode());

        System.out.println(output.toString());
    }

    /**
     * Prints a message with rainbow colors.
     * Each character (excluding spaces) is colored with a different color
     * cycling through red, yellow, green, cyan, blue, and purple.
     * 
     * @param message the message to display in rainbow colors
     */
    public static void rainbow(String message) {
        Colors[] rainbowColors = {
                Colors.RED, Colors.YELLOW, Colors.GREEN,
                Colors.CYAN, Colors.BLUE, Colors.PURPLE
        };

        if (!enableColors) {
            System.out.println(message);
            return;
        }

        StringBuilder rainbow = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (c != ' ') {
                Colors color = rainbowColors[i % rainbowColors.length];
                rainbow.append(color.getCode()).append(c);
            } else {
                rainbow.append(c);
            }
        }
        rainbow.append(Colors.RESET.getCode());
        System.out.println(rainbow.toString());
    }

    /**
     * Displays a progress bar in the terminal.
     * The progress bar shows the current progress as a visual bar with percentage.
     * When current equals total, a newline is printed to complete the progress
     * display.
     * 
     * @param current the current progress value
     * @param total   the total/maximum progress value
     * @param width   the width of the progress bar in characters
     */
    public static void progressBar(int current, int total, int width) {
        double percentage = (double) current / total;
        int filled = (int) (percentage * width);
        int empty = width - filled;

        StringBuilder bar = new StringBuilder();
        bar.append(Colors.BRIGHT_WHITE.getCode()).append("[");
        bar.append(Colors.BG_GREEN.getCode()).append(" ".repeat(filled));
        bar.append(Colors.BG_BLACK.getCode()).append(" ".repeat(empty));
        bar.append(Colors.BRIGHT_WHITE.getCode()).append("] ");
        bar.append(String.format("%.1f%%", percentage * 100));
        bar.append(Colors.RESET.getCode());

        System.out.print("\r" + bar.toString());
        if (current == total) {
            System.out.println();
        }
    }

    /**
     * Enables or disables timestamp display in log messages.
     * 
     * @param enable true to enable timestamps, false to disable
     */
    public static void setEnableTimestamp(boolean enable) {
        enableTimestamp = enable;
    }

    /**
     * Enables or disables color output in log messages.
     * When disabled, all output will be plain text without ANSI codes.
     * 
     * @param enable true to enable colors, false to disable
     */
    public static void setEnableColors(boolean enable) {
        enableColors = enable;
    }

    /**
     * Prints a separator line using default settings.
     * Uses dashes ("-") with a length of 50 characters.
     */
    public static void separator() {
        separator("-", 50);
    }

    /**
     * Prints a separator line with custom character and length.
     * 
     * @param character the character to repeat for the separator
     * @param length    the number of times to repeat the character
     */
    public static void separator(String character, int length) {
        info(character.repeat(length), Colors.BRIGHT_BLACK);
    }

    /**
     * Prints a formatted header with the specified title.
     * The header includes separator lines above and below the title,
     * and the title is displayed in uppercase with special formatting.
     * 
     * @param title the title text for the header
     */
    public static void header(String title) {
        separator("=", 50);
        log(" " + title.toUpperCase() + " ", Colors.BRIGHT_WHITE, Colors.BG_BLUE, Colors.BOLD);
        separator("=", 50);
    }
}