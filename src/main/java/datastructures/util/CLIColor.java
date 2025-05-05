package datastructures.util;

/**
 * represents an enumeration for CLI Color
 */
public enum CLIColor{

    /**
     * reset color coding
     */
    RESET("\u001B[0m"),

    /**
     * red coding
     */
    RED("\u001B[31m"),

    /**
     * green coding
     */
    GREEN("\u001B[32m"),

    /**
     * yellow coding
     */
    YELLOW("\u001B[33m"),

    /**
     * blue coding
     */
    BLUE("\u001B[34m"),

    /**
     * white coding
     */
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
