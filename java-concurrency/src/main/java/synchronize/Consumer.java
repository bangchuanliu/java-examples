package synchronize;

import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {

    private final Queue<Integer> queue;

    public Consumer(Queue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (;;) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (queue) {
                if (queue.isEmpty()) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int number = queue.poll();
                System.out.println("Consumer consume a number " + number);
            }
            notifyAll();
        }
    }
}
