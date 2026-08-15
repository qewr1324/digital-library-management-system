package ir.nas.util;

public final class ColorCMD
{
    // Forground Color
    private static final String RESET = "\u001B[0m";
    private static final String BLACK = "\u001B[30m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";
    private static final String WHITE = "\u001B[37m";

    // Forground Color (Bright)
    private static final String BRIGHT_BLACK = "\u001B[90m";
    private static final String BRIGHT_RED = "\u001B[91m";
    private static final String BRIGHT_GREEN = "\u001B[92m";
    private static final String BRIGHT_YELLOW = "\u001B[93m";
    private static final String BRIGHT_BLUE = "\u001B[94m";
    private static final String BRIGHT_PURPLE = "\u001B[95m";
    private static final String BRIGHT_CYAN = "\u001B[96m";
    private static final String BRIGHT_WHITE = "\u001B[97m";

    // Background Color
    private static final String BG_DEFAULT = "\u001B[49m";
    private static final String BG_BLACK = "\u001B[40m";
    private static final String BG_RED = "\u001B[41m";
    private static final String BG_GREEN = "\u001B[42m";
    private static final String BG_YELLOW = "\u001B[43m";
    private static final String BG_BLUE = "\u001B[44m";
    private static final String BG_PURPLE = "\u001B[45m";
    private static final String BG_CYAN = "\u001B[46m";
    private static final String BG_WHITE = "\u001B[47m";

    // Font Style
    private static final String BOLD = "\u001B[1m";
    private static final String ITALIC = "\u001B[3m";
    private static final String UNDERLINE = "\u001B[4m";
    private static final String REVERSED = "\u001B[7m";

    public static final String repositoryError(final String message)
    {
        return BG_CYAN.concat(message).concat(BG_DEFAULT);
    }

    public static final String serviceError(final String message)
    {
        return BG_BLUE.concat(message).concat(BG_DEFAULT);
    }

    public static final String modelError(final String message)
    {
        return BG_YELLOW.concat(message).concat(BG_DEFAULT);
    }

    public static final String dbError(final String message)
    {
        return BG_WHITE.concat(BLACK).concat(message).concat(RESET).concat(BG_DEFAULT);
    }

    public static final String validationError(final String message)
    {
        return BG_RED.concat(message).concat(BG_DEFAULT);
    }

    public static final String log(final String message)
    {
        return BG_GREEN.concat(message).concat(BG_DEFAULT);
    }
}