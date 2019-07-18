package sychronized;

import java.util.concurrent.TimeUnit;

public class StopThread {

    private static volatile boolean stopRequested;

    public static synchronized void requestStop() {
        stopRequested = true;
    }

    public static synchronized boolean stopRequested() {
        return stopRequested;
    }

    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(() -> {
            int i = 0;
            while (!stopRequested) {
                i++;
            }
        });
        t.start();

        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
//        requestStop();
    }
}
