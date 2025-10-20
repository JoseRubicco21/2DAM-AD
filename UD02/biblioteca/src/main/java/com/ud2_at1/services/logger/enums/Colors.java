package com.ud2_at1.services.logger.enums;

/**
 * Enumeration of ANSI color codes for terminal output formatting.
 * 
 * <p>This enum provides a comprehensive set of ANSI escape codes for:
 * <ul>
 *   <li>Foreground colors (regular and bright variants)</li>
 *   <li>Background colors (regular and bright variants)</li>
 *   <li>256-color palette with numbered variations</li>
 *   <li>Text formatting styles (bold, italic, underline, etc.)</li>
 *   <li>Cursor control and screen clearing</li>
 * </ul>
 * 
 * <p>Example usage:
 * <pre>{@code
 * System.out.println(Colors.RED.getCode() + "Red text" + Colors.RESET.getCode());
 * System.out.println(Colors.ORANGE_3.getCode() + "Medium orange text" + Colors.RESET.getCode());
 * System.out.println(Colors.BG_BLUE_2.getCode() + Colors.WHITE.getCode() + "White on medium blue" + Colors.RESET.getCode());
 * }</pre>
 * 
 * @author GitHub Copilot
 * @version 2.0
 * @since 1.0
 */
public enum Colors {
    
    // Reset and control codes
    
    /**
     * Resets all formatting to terminal defaults.
     * Should be used at the end of colored text to prevent color bleeding.
     */
    RESET("\u001B[0m"),
    
    /**
     * Clears the entire terminal screen.
     */
    CLEAR_SCREEN("\u001B[2J"),
    
    /**
     * Moves cursor to the home position (top-left corner).
     */
    CURSOR_HOME("\u001B[H"),
    
    // Basic foreground colors (legacy support)
    
    /**
     * Black foreground color.
     */
    BLACK("\u001B[30m"),
    
    /**
     * Red foreground color.
     */
    RED("\u001B[31m"),
    
    /**
     * Green foreground color.
     */
    GREEN("\u001B[32m"),
    
    /**
     * Yellow foreground color.
     */
    YELLOW("\u001B[33m"),
    
    /**
     * Blue foreground color.
     */
    BLUE("\u001B[34m"),
    
    /**
     * Purple (magenta) foreground color.
     */
    PURPLE("\u001B[35m"),
    
    /**
     * Cyan foreground color.
     */
    CYAN("\u001B[36m"),
    
    /**
     * White foreground color.
     */
    WHITE("\u001B[37m"),
    
    // Bright foreground colors (legacy support)
    
    /**
     * Bright black (dark gray) foreground color.
     */
    BRIGHT_BLACK("\u001B[90m"),
    
    /**
     * Bright red foreground color.
     */
    BRIGHT_RED("\u001B[91m"),
    
    /**
     * Bright green foreground color.
     */
    BRIGHT_GREEN("\u001B[92m"),
    
    /**
     * Bright yellow foreground color.
     */
    BRIGHT_YELLOW("\u001B[93m"),
    
    /**
     * Bright blue foreground color.
     */
    BRIGHT_BLUE("\u001B[94m"),
    
    /**
     * Bright purple (bright magenta) foreground color.
     */
    BRIGHT_PURPLE("\u001B[95m"),
    
    /**
     * Bright cyan foreground color.
     */
    BRIGHT_CYAN("\u001B[96m"),
    
    /**
     * Bright white foreground color.
     */
    BRIGHT_WHITE("\u001B[97m"),
    
    // RED variations (brightest to darkest)
    
    /**
     * Brightest red variation.
     */
    RED_1("\u001B[38;5;196m"),
    
    /**
     * Very bright red variation.
     */
    RED_2("\u001B[38;5;202m"),
    
    /**
     * Bright red variation.
     */
    RED_3("\u001B[38;5;160m"),
    
    /**
     * Medium red variation.
     */
    RED_4("\u001B[38;5;124m"),
    
    /**
     * Dark red variation.
     */
    RED_5("\u001B[38;5;88m"),
    
    /**
     * Darkest red variation.
     */
    RED_6("\u001B[38;5;52m"),
    
    // ORANGE variations (brightest to darkest)
    
    /**
     * Brightest orange variation.
     */
    ORANGE_1("\u001B[38;5;214m"),
    
    /**
     * Very bright orange variation.
     */
    ORANGE_2("\u001B[38;5;208m"),
    
    /**
     * Bright orange variation.
     */
    ORANGE_3("\u001B[38;5;172m"),
    
    /**
     * Medium orange variation.
     */
    ORANGE_4("\u001B[38;5;166m"),
    
    /**
     * Dark orange variation.
     */
    ORANGE_5("\u001B[38;5;130m"),
    
    /**
     * Darkest orange variation.
     */
    ORANGE_6("\u001B[38;5;94m"),
    
    // YELLOW variations (brightest to darkest)
    
    /**
     * Brightest yellow variation.
     */
    YELLOW_1("\u001B[38;5;226m"),
    
    /**
     * Very bright yellow variation.
     */
    YELLOW_2("\u001B[38;5;220m"),
    
    /**
     * Bright yellow variation.
     */
    YELLOW_3("\u001B[38;5;184m"),
    
    /**
     * Medium yellow variation.
     */
    YELLOW_4("\u001B[38;5;178m"),
    
    /**
     * Dark yellow variation.
     */
    YELLOW_5("\u001B[38;5;142m"),
    
    /**
     * Darkest yellow variation.
     */
    YELLOW_6("\u001B[38;5;100m"),
    
    // GREEN variations (brightest to darkest)
    
    /**
     * Brightest green variation.
     */
    GREEN_1("\u001B[38;5;46m"),
    
    /**
     * Very bright green variation.
     */
    GREEN_2("\u001B[38;5;40m"),
    
    /**
     * Bright green variation.
     */
    GREEN_3("\u001B[38;5;34m"),
    
    /**
     * Medium green variation.
     */
    GREEN_4("\u001B[38;5;28m"),
    
    /**
     * Dark green variation.
     */
    GREEN_5("\u001B[38;5;22m"),
    
    /**
     * Darkest green variation.
     */
    GREEN_6("\u001B[38;5;16m"),
    
    // CYAN variations (brightest to darkest)
    
    /**
     * Brightest cyan variation.
     */
    CYAN_1("\u001B[38;5;51m"),
    
    /**
     * Very bright cyan variation.
     */
    CYAN_2("\u001B[38;5;45m"),
    
    /**
     * Bright cyan variation.
     */
    CYAN_3("\u001B[38;5;39m"),
    
    /**
     * Medium cyan variation.
     */
    CYAN_4("\u001B[38;5;33m"),
    
    /**
     * Dark cyan variation.
     */
    CYAN_5("\u001B[38;5;27m"),
    
    /**
     * Darkest cyan variation.
     */
    CYAN_6("\u001B[38;5;21m"),
    
    // BLUE variations (brightest to darkest)
    
    /**
     * Brightest blue variation.
     */
    BLUE_1("\u001B[38;5;69m"),
    
    /**
     * Very bright blue variation.
     */
    BLUE_2("\u001B[38;5;63m"),
    
    /**
     * Bright blue variation.
     */
    BLUE_3("\u001B[38;5;57m"),
    
    /**
     * Medium blue variation.
     */
    BLUE_4("\u001B[38;5;21m"),
    
    /**
     * Dark blue variation.
     */
    BLUE_5("\u001B[38;5;19m"),
    
    /**
     * Darkest blue variation.
     */
    BLUE_6("\u001B[38;5;17m"),
    
    // PURPLE variations (brightest to darkest)
    
    /**
     * Brightest purple variation.
     */
    PURPLE_1("\u001B[38;5;201m"),
    
    /**
     * Very bright purple variation.
     */
    PURPLE_2("\u001B[38;5;165m"),
    
    /**
     * Bright purple variation.
     */
    PURPLE_3("\u001B[38;5;129m"),
    
    /**
     * Medium purple variation.
     */
    PURPLE_4("\u001B[38;5;93m"),
    
    /**
     * Dark purple variation.
     */
    PURPLE_5("\u001B[38;5;57m"),
    
    /**
     * Darkest purple variation.
     */
    PURPLE_6("\u001B[38;5;53m"),
    
    // PINK variations (brightest to darkest)
    
    /**
     * Brightest pink variation.
     */
    PINK_1("\u001B[38;5;207m"),
    
    /**
     * Very bright pink variation.
     */
    PINK_2("\u001B[38;5;205m"),
    
    /**
     * Bright pink variation.
     */
    PINK_3("\u001B[38;5;199m"),
    
    /**
     * Medium pink variation.
     */
    PINK_4("\u001B[38;5;163m"),
    
    /**
     * Dark pink variation.
     */
    PINK_5("\u001B[38;5;127m"),
    
    /**
     * Darkest pink variation.
     */
    PINK_6("\u001B[38;5;91m"),
    
    // GRAY variations (brightest to darkest)
    
    /**
     * Brightest gray variation.
     */
    GRAY_1("\u001B[38;5;255m"),
    
    /**
     * Very bright gray variation.
     */
    GRAY_2("\u001B[38;5;250m"),
    
    /**
     * Bright gray variation.
     */
    GRAY_3("\u001B[38;5;245m"),
    
    /**
     * Medium gray variation.
     */
    GRAY_4("\u001B[38;5;240m"),
    
    /**
     * Dark gray variation.
     */
    GRAY_5("\u001B[38;5;235m"),
    
    /**
     * Darkest gray variation.
     */
    GRAY_6("\u001B[38;5;232m"),
    
    // BACKGROUND COLOR variations
    
    // RED background variations
    
    /**
     * Brightest red background variation.
     */
    BG_RED_1("\u001B[48;5;196m"),
    
    /**
     * Very bright red background variation.
     */
    BG_RED_2("\u001B[48;5;202m"),
    
    /**
     * Bright red background variation.
     */
    BG_RED_3("\u001B[48;5;160m"),
    
    /**
     * Medium red background variation.
     */
    BG_RED_4("\u001B[48;5;124m"),
    
    /**
     * Dark red background variation.
     */
    BG_RED_5("\u001B[48;5;88m"),
    
    /**
     * Darkest red background variation.
     */
    BG_RED_6("\u001B[48;5;52m"),
    
    // ORANGE background variations
    
    /**
     * Brightest orange background variation.
     */
    BG_ORANGE_1("\u001B[48;5;214m"),
    
    /**
     * Very bright orange background variation.
     */
    BG_ORANGE_2("\u001B[48;5;208m"),
    
    /**
     * Bright orange background variation.
     */
    BG_ORANGE_3("\u001B[48;5;172m"),
    
    /**
     * Medium orange background variation.
     */
    BG_ORANGE_4("\u001B[48;5;166m"),
    
    /**
     * Dark orange background variation.
     */
    BG_ORANGE_5("\u001B[48;5;130m"),
    
    /**
     * Darkest orange background variation.
     */
    BG_ORANGE_6("\u001B[48;5;94m"),
    
    // YELLOW background variations
    
    /**
     * Brightest yellow background variation.
     */
    BG_YELLOW_1("\u001B[48;5;226m"),
    
    /**
     * Very bright yellow background variation.
     */
    BG_YELLOW_2("\u001B[48;5;220m"),
    
    /**
     * Bright yellow background variation.
     */
    BG_YELLOW_3("\u001B[48;5;184m"),
    
    /**
     * Medium yellow background variation.
     */
    BG_YELLOW_4("\u001B[48;5;178m"),
    
    /**
     * Dark yellow background variation.
     */
    BG_YELLOW_5("\u001B[48;5;142m"),
    
    /**
     * Darkest yellow background variation.
     */
    BG_YELLOW_6("\u001B[48;5;100m"),
    
    // GREEN background variations
    
    /**
     * Brightest green background variation.
     */
    BG_GREEN_1("\u001B[48;5;46m"),
    
    /**
     * Very bright green background variation.
     */
    BG_GREEN_2("\u001B[48;5;40m"),
    
    /**
     * Bright green background variation.
     */
    BG_GREEN_3("\u001B[48;5;34m"),
    
    /**
     * Medium green background variation.
     */
    BG_GREEN_4("\u001B[48;5;28m"),
    
    /**
     * Dark green background variation.
     */
    BG_GREEN_5("\u001B[48;5;22m"),
    
    /**
     * Darkest green background variation.
     */
    BG_GREEN_6("\u001B[48;5;16m"),
    
    // CYAN background variations
    
    /**
     * Brightest cyan background variation.
     */
    BG_CYAN_1("\u001B[48;5;51m"),
    
    /**
     * Very bright cyan background variation.
     */
    BG_CYAN_2("\u001B[48;5;45m"),
    
    /**
     * Bright cyan background variation.
     */
    BG_CYAN_3("\u001B[48;5;39m"),
    
    /**
     * Medium cyan background variation.
     */
    BG_CYAN_4("\u001B[48;5;33m"),
    
    /**
     * Dark cyan background variation.
     */
    BG_CYAN_5("\u001B[48;5;27m"),
    
    /**
     * Darkest cyan background variation.
     */
    BG_CYAN_6("\u001B[48;5;21m"),
    
    // BLUE background variations
    
    /**
     * Brightest blue background variation.
     */
    BG_BLUE_1("\u001B[48;5;69m"),
    
    /**
     * Very bright blue background variation.
     */
    BG_BLUE_2("\u001B[48;5;63m"),
    
    /**
     * Bright blue background variation.
     */
    BG_BLUE_3("\u001B[48;5;57m"),
    
    /**
     * Medium blue background variation.
     */
    BG_BLUE_4("\u001B[48;5;21m"),
    
    /**
     * Dark blue background variation.
     */
    BG_BLUE_5("\u001B[48;5;19m"),
    
    /**
     * Darkest blue background variation.
     */
    BG_BLUE_6("\u001B[48;5;17m"),
    
    // PURPLE background variations
    
    /**
     * Brightest purple background variation.
     */
    BG_PURPLE_1("\u001B[48;5;201m"),
    
    /**
     * Very bright purple background variation.
     */
    BG_PURPLE_2("\u001B[48;5;165m"),
    
    /**
     * Bright purple background variation.
     */
    BG_PURPLE_3("\u001B[48;5;129m"),
    
    /**
     * Medium purple background variation.
     */
    BG_PURPLE_4("\u001B[48;5;93m"),
    
    /**
     * Dark purple background variation.
     */
    BG_PURPLE_5("\u001B[48;5;57m"),
    
    /**
     * Darkest purple background variation.
     */
    BG_PURPLE_6("\u001B[48;5;53m"),
    
    // PINK background variations
    
    /**
     * Brightest pink background variation.
     */
    BG_PINK_1("\u001B[48;5;207m"),
    
    /**
     * Very bright pink background variation.
     */
    BG_PINK_2("\u001B[48;5;205m"),
    
    /**
     * Bright pink background variation.
     */
    BG_PINK_3("\u001B[48;5;199m"),
    
    /**
     * Medium pink background variation.
     */
    BG_PINK_4("\u001B[48;5;163m"),
    
    /**
     * Dark pink background variation.
     */
    BG_PINK_5("\u001B[48;5;127m"),
    
    /**
     * Darkest pink background variation.
     */
    BG_PINK_6("\u001B[48;5;91m"),
    
    // GRAY background variations
    
    /**
     * Brightest gray background variation.
     */
    BG_GRAY_1("\u001B[48;5;255m"),
    
    /**
     * Very bright gray background variation.
     */
    BG_GRAY_2("\u001B[48;5;250m"),
    
    /**
     * Bright gray background variation.
     */
    BG_GRAY_3("\u001B[48;5;245m"),
    
    /**
     * Medium gray background variation.
     */
    BG_GRAY_4("\u001B[48;5;240m"),
    
    /**
     * Dark gray background variation.
     */
    BG_GRAY_5("\u001B[48;5;235m"),
    
    /**
     * Darkest gray background variation.
     */
    BG_GRAY_6("\u001B[48;5;232m"),
    
    // Legacy background colors
    
    /**
     * Black background color.
     */
    BG_BLACK("\u001B[40m"),
    
    /**
     * Red background color.
     */
    BG_RED("\u001B[41m"),
    
    /**
     * Green background color.
     */
    BG_GREEN("\u001B[42m"),
    
    /**
     * Yellow background color.
     */
    BG_YELLOW("\u001B[43m"),
    
    /**
     * Blue background color.
     */
    BG_BLUE("\u001B[44m"),
    
    /**
     * Purple (magenta) background color.
     */
    BG_PURPLE("\u001B[45m"),
    
    /**
     * Cyan background color.
     */
    BG_CYAN("\u001B[46m"),
    
    /**
     * White background color.
     */
    BG_WHITE("\u001B[47m"),
    
    // Bright background colors
    
    /**
     * Bright black (dark gray) background color.
     */
    BG_BRIGHT_BLACK("\u001B[100m"),
    
    /**
     * Bright red background color.
     */
    BG_BRIGHT_RED("\u001B[101m"),
    
    /**
     * Bright green background color.
     */
    BG_BRIGHT_GREEN("\u001B[102m"),
    
    /**
     * Bright yellow background color.
     */
    BG_BRIGHT_YELLOW("\u001B[103m"),
    
    /**
     * Bright blue background color.
     */
    BG_BRIGHT_BLUE("\u001B[104m"),
    
    /**
     * Bright purple (bright magenta) background color.
     */
    BG_BRIGHT_PURPLE("\u001B[105m"),
    
    /**
     * Bright cyan background color.
     */
    BG_BRIGHT_CYAN("\u001B[106m"),
    
    /**
     * Bright white background color.
     */
    BG_BRIGHT_WHITE("\u001B[107m"),
    
    // Text styles
    
    /**
     * Bold text formatting.
     */
    BOLD("\u001B[1m"),
    
    /**
     * Dim (faint) text formatting.
     */
    DIM("\u001B[2m"),
    
    /**
     * Italic text formatting.
     * Note: Not supported by all terminals.
     */
    ITALIC("\u001B[3m"),
    
    /**
     * Underlined text formatting.
     */
    UNDERLINE("\u001B[4m"),
    
    /**
     * Blinking text formatting.
     * Note: Not supported by all terminals.
     */
    BLINK("\u001B[5m"),
    
    /**
     * Reverse video formatting (swaps foreground and background colors).
     */
    REVERSE("\u001B[7m"),
    
    /**
     * Hidden (invisible) text formatting.
     */
    HIDDEN("\u001B[8m"),
    
    /**
     * Strikethrough text formatting.
     * Note: Not supported by all terminals.
     */
    STRIKETHROUGH("\u001B[9m"),
    
    // Legacy support for ORANGE
    
    /**
     * Orange foreground color (legacy support).
     * Equivalent to ORANGE_2.
     */
    ORANGE("\u001B[38;5;208m");
    
    /** The ANSI escape code string for this color/style. */
    private final String code;
    
    /**
     * Constructs a Colors enum constant with the specified ANSI escape code.
     * 
     * @param code the ANSI escape code string
     */
    Colors(String code) {
        this.code = code;
    }
    
    /**
     * Returns the ANSI escape code string for this color or formatting option.
     * 
     * @return the ANSI escape code as a string
     */
    public String getCode() {
        return code;
    }
    
    /**
     * Returns a string representation of this Colors enum constant.
     * This is equivalent to calling {@link #getCode()}.
     * 
     * @return the ANSI escape code as a string
     */
    @Override
    public String toString() {
        return code;
    }
}
