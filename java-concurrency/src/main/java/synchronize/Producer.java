package synchronize;

import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Producer implements Runnable {

    private TaskQueue taskQueue;
    private static int taskId = 0;

    public Producer(TaskQueue taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        for (; ; ) {
            try {
                TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
            } catch (InterruptedException e) {
                System.out.println("Interrupted on producing task");
            }

            Task task = new Task(taskId++);
            try {
                System.out.println(Thread.currentThread().getName() + " produce a task " + task.getTaskId());
                taskQueue.put(task);
            } catch (InterruptedException e) {
                // set interrupt signal to true
                Thread.currentThread().interrupt();
            }
        }
    }
}
