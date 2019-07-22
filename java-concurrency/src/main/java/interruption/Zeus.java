package interruption;

import java.util.concurrent.TimeUnit;

/**
 * Thread.currentThread.interrupt() to set interrupt signal
 */
public class Zeus {
    
    public static void main(String[] args) throws InterruptedException {
        Sisyphean sisyphean = new Sisyphean();
        Thread t = new Thread(() -> sisyphean.rollStoneOnCheckInterrupt());
        t.start();
        TimeUnit.MILLISECONDS.sleep(10);
        t.interrupt();
    }
}
