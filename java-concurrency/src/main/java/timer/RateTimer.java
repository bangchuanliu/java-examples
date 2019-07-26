package timer;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import static java.time.Instant.now;

public class RateTimer {

    private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    public static void main(String[] args) throws InterruptedException {
        DelayQueue<RateTask> queue = new DelayQueue<RateTask>();

//        for (int i = 0; i < 10; i++) {
//            queue.add(new RateTask("o1", Instant.now().toEpochMilli() + 10 * 1000));
//        }
//        TimeUnit.SECONDS.sleep(1);
        queue.add(new RateTask("o2", Instant.now().toEpochMilli() + 5 * 1000));
        System.out.println(sdf.format(new Date()) + " program starts");
        executeTask(queue);
    }

    public static void executeTask(DelayQueue<RateTask> queue) {
        new Thread(() -> {
            while (!queue.isEmpty()) {
                try {
                    queue.take().run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    static class RateTask implements Runnable, Delayed {
        private String orderId;
        private long scheduledTime;

        public RateTask(String orderId, long scheduledTime) {
            this.orderId = orderId;
            this.scheduledTime = scheduledTime;
        }

        @Override
        public void run() {
            System.out.println(sdf.format(new Date()) + " " + orderId + " 5 star");
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(scheduledTime - now().toEpochMilli(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return Long.compare(this.getDelay(TimeUnit.HOURS), o.getDelay(TimeUnit.HOURS));
        }
    }
}
