package timer;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 *  drawback: scheduled executor stops when exception occurs
 *  If any execution of the task encounters an exception, subsequent executions are suppressed.Otherwise, the task will only terminate via cancellation or termination of the executor
 */
public class ScheduledExecutorExample {

    private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
        
        // schedule with fix delay
        ScheduledFuture<?> scheduledFuture1 = service.scheduleWithFixedDelay(new RunnableTask("T1"), 1, 2, TimeUnit.SECONDS);
        
        // schedule with fix rate
        ScheduledFuture<?> scheduledFuture2 = service.scheduleAtFixedRate(new RunnableTask("T2"), 3, 1, TimeUnit.SECONDS);
        
        System.out.println(sdf.format(new Date()) + " program starts");
    }
    
    
    static class RunnableTask implements Runnable{
        private String name;
        private int counter = 0;

        public RunnableTask(String name) {
            this.name = name;
        }
        
        @Override
        public void run() {
            System.out.println(sdf.format(new Date()) + " " + name + " runs");
            counter++;
            if (counter == 5) {
                throw new RuntimeException("exception occurs after 5 times");
            }
        }
    }
}
