package timer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 *  drawback: Timer stops when exception occurs
 */
public class TimerExample {

    private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    private static int i = 0;

    public static void main(String[] args) {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println(sdf.format(new Date()) + " task runs");
                i++;
                if (i == 5) {
                    throw new RuntimeException("exception occurs after 5 times");
                }
            }
        };

        Timer timer = new Timer("timer");
        long delay = 100L;
        long period = 1000L;

        // timer stops after 5 times because of exception
        timer.schedule(timerTask, delay, period);
    }
}
