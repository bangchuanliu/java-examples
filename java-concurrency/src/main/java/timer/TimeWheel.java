package timer;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TimeWheel {

    private static Set<TimeWheelTask>[] sets = new Set[10];
    private static volatile int count = 0;

    public static void main(String[] args) {
        for (int i = 0; i < sets.length; i++) {
            Set<TimeWheelTask> set = new HashSet<>();
            sets[i] = set;
        }

        new Thread(TimeWheel::produceTask).start();
        new Thread(TimeWheel::consumeTask).start();
    }

    public static void consumeTask() {
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Long slot = System.currentTimeMillis() % 10;
            Set<TimeWheelTask> set = sets[slot.intValue()];

            for (TimeWheelTask task : set) {
                task.run();
            }
            sets[slot.intValue()] = new HashSet<>();
        }
    }

    public static void produceTask() {
        while (count < 10) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;

            TimeWheelTask task = new TimeWheelTask("T" + count);
            int slot = count % 10;
            Set<TimeWheelTask> set = sets[slot];
            set.add(task);
        }
    }


    static class TimeWheelTask implements Runnable {

        private String name;

        public TimeWheelTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("execute task: " + name);
        }
    }
}
