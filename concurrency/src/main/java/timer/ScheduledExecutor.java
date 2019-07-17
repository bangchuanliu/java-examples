package timer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutor {

    private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    
    public static void main(String[] args) {

        Runnable runnable = () -> {
            System.out.println(sdf.format(new Date()) + " time task run");
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        
        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
        
        service.scheduleWithFixedDelay(runnable, 3, 1, TimeUnit.SECONDS);
//        service.scheduleAtFixedRate(runnable, 3, 1, TimeUnit.SECONDS);
    }
}
