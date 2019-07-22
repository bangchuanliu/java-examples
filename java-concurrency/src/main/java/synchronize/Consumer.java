package synchronize;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable{

    private TaskQueue taskQueue;

    public Consumer(TaskQueue taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        for (; ; ) {
            try {
                TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
            } catch (InterruptedException e) {
                System.out.println("Interrupted on consuming task");
            }

            try {
                Task task = taskQueue.take();
                task.execute();
            } catch (InterruptedException e) {
                // set interrupt signal to true
                Thread.currentThread().interrupt();
            }
        }
    }
}
