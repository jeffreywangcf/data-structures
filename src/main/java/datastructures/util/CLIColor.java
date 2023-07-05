package datastructures.util;

/**
 * represents an enumeration for CLI Color
 */
public enum CLIColor{
    RESET("\u001B[0m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    WHITE("\u001B[37m");

    private final String code;

    /**
     * default constructor for CLIColor
     *
     * @param code code
     */
    private CLIColor(String code){
        this.code = code;
    }

    /**
     * color a text with given color
     *
     * @param text  text to color
     * @param color color
     * @return colored text
     */
    public static String colored(String text, CLIColor color){
        return color.code + text + RESET.code;
    }
}
