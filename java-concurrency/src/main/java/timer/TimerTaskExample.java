package timer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTaskExample {

    private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    public static void main(String[] args) {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println(sdf.format(new Date()) + " time task run");
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Timer timer = new Timer("timer");
        long delay = 3000L;
        long period = 1000L;

        timer.schedule(timerTask, delay, period);

        System.out.println(sdf.format(new Date()));
    }
}
