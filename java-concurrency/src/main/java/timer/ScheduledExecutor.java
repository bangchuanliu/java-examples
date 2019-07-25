package timer;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutor {

    private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    
    public static void main(String[] args) {
        Duration duration = Duration.ofSeconds(1);
        
        Runnable runnable = () -> {
            System.out.println(sdf.format(new Date()) + " time task run");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        
        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
        
        // schedule with fix delay
        service.scheduleWithFixedDelay(runnable, 3, 1, TimeUnit.SECONDS);
        
        // schedule with fix rate
        service.scheduleAtFixedRate(runnable, 3, 1, TimeUnit.SECONDS);
    }
}
