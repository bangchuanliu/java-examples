package parseq;

import com.linkedin.parseq.Engine;
import com.linkedin.parseq.EngineBuilder;
import com.linkedin.parseq.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class DataHub {
    public static void main(String[] args) throws InterruptedException {
        final int numCores = Runtime.getRuntime().availableProcessors();
        final ExecutorService taskScheduler = Executors.newFixedThreadPool(numCores + 1);
        final ScheduledExecutorService timerScheduler = Executors.newSingleThreadScheduledExecutor();

        final Engine engine = new EngineBuilder()
                .setTaskExecutor(taskScheduler)
                .setTimerScheduler(timerScheduler)
                .build();

        engine.run(Task.par(createTask1(), createTask2(),createTask3()));
    }


    public static Task<String> createTask1() throws InterruptedException {
        Thread.sleep(1000);
        return Task.value("task1").andThen(System.out::println);
    }

    public static Task<String> createTask2() throws InterruptedException {
        Thread.sleep(2000);
        return Task.value("task2").map(value -> "this is " + value).andThen(System.out::println);
    }

    public static Task<String> createTask3() throws InterruptedException {
        Thread.sleep(5000);
        return Task.value("task3").map(value -> value.charAt(0)+"").andThen(System.out::println);
    }
}
