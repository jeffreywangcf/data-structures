package datastructures.util;

/**
 * represents a stopwatch singleton main.java.util class
 */
public class StopWatch{
    private long t;

    /**
     * singleton class constructor should not be visible
     */
    private StopWatch(){
    }

    public static StopWatch shared = new StopWatch();

    /**
     * start stopwatch
     */
    public void begin(){
        this.t = System.currentTimeMillis();
    }

    /**
     * end stopwatch
     */
    public void end(){
        this.end("");
    }

    /**
     * end stopwatch with a message
     *
     * @param message message to display
     */
    public void end(String message){
        long curTime = System.currentTimeMillis();
        System.out.printf("[%s] time-lapse: %.3fs%n", message, (curTime - this.t) / 1000.0);
    }
}
