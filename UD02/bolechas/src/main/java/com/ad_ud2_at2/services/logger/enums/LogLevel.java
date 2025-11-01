package com.ad_ud2_at2.services.logger.enums;
 
 /**
     * Enumeration of predefined log levels with associated color schemes.
     * 
     * <p>Each log level has a predefined foreground and background color combination
     * that provides visual distinction between different types of log messages.
     */
    public enum LogLevel {
        /** Debug level - Cyan text on black background. */
        DEBUG(Colors.CYAN, Colors.BG_BLACK),
        
        /** Info level - Bright white text on blue background. */
        INFO(Colors.BRIGHT_WHITE, Colors.BG_BLUE),
        
        /** Success level - Bright white text on green background. */
        SUCCESS(Colors.BRIGHT_WHITE, Colors.BG_GREEN),
        
        /** Warning level - Black text on yellow background. */
        WARNING(Colors.BLACK, Colors.BG_YELLOW),
        
        /** Error level - Bright red text on red background. */
        ERROR(Colors.BRIGHT_RED, Colors.BG_RED),
        
        /** Critical level - Bright yellow text on orange background. */
        CRITICAL(Colors.ORANGE, Colors.BG_ORANGE_3),
        
        /** Network level - Bright purple text on bright purple background. */
        NETWORK(Colors.WHITE, Colors.BG_BRIGHT_PURPLE),
        
        /** Query level - Purple text on black background. */
        QUERY(Colors.PURPLE, Colors.BG_BLACK);
        
        /** The foreground color for this log level. */
        private final Colors foreground;
        
        /** The background color for this log level. */
        private final Colors background;
        
        /**
         * Constructs a LogLevel with the specified color scheme.
         * 
         * @param foreground the foreground color
         * @param background the background color
         */
        LogLevel(Colors foreground, Colors background) {
            this.foreground = foreground;
            this.background = background;
        }
        
        /**
         * Returns the foreground color for this log level.
         * 
         * @return the foreground color
         */
        public Colors getForeground() {
            return foreground;
        }
        
        /**
         * Returns the background color for this log level.
         * 
         * @return the background color
         */
        public Colors getBackground() {
            return background;
        }
    }
