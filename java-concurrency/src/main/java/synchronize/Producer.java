package synchronize;

import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Producer implements Runnable {

    private final int SIZE = 3;
    private int count = 0;
    private final Queue<Integer> queue;

    public Producer(Queue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (;;) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (queue) {
                if (queue.size() > SIZE) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int number = count++;
                queue.add(number);
                System.out.println("Producer product a number " + number);
            }
            notifyAll();
        }
    }
}
