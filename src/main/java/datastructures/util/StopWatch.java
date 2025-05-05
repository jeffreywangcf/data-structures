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

    /**
     * shared singleton
     */
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
        this.end("time-lapse:", Double.MAX_VALUE);
    }

    /**
     * end stopwatch with a message. print time in green if timelapse is smaller than max time,
     * otherwise print in red.
     *
     * @param message message to display
     * @param maxTime maximum time allowed in double
     */
    public void end(String message, Double maxTime){
        long curTime = System.currentTimeMillis();
        Double timeLapse = (curTime - this.t) / 1000.0;
        String timeLapseStr = String.format("%.3fs%n", timeLapse);
        System.out.printf("%s %s%n", message, CLIColor.colored(timeLapseStr,
                timeLapse < maxTime ? CLIColor.GREEN : CLIColor.RED));
    }
}
