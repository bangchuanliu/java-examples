package futures;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class CompleteFutures {
    private static Random ran = new Random();

    public static void main(String[] args) {
        try {
//            singleFuture();
            multiFutures();
            System.out.println("A.....");
            Thread.sleep(200);
            System.out.println("B.....");
            Thread.sleep(200);
            System.out.println("C.....");
            Thread.sleep(200);
            System.out.println("D.....");
            Thread.sleep(200);
            System.out.println("E.....");
            Thread.sleep(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void multiFutures() throws Exception {
        // thenCompose, with order
        CompletableFuture<Integer> f1 = supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });

        CompletableFuture<Integer> f2 = f1.thenCompose(value -> {
            return supplyAsync(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return value + 2;
            });
        });
        final long start = System.currentTimeMillis();
        f2.thenAccept(result -> {
            System.out.println("thenCompose example : time lapsed should be 150: " + (System.currentTimeMillis() - start));
            System.out.println("thenCompose example :  1 + 2 = " + result);
        });

        // thenCombine, independent tasks
        CompletableFuture<Integer> f3 = supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });

        CompletableFuture<Integer> f4 = supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 2;
        });
        CompletableFuture<Integer> f44 = f3.thenCombine(f4, (v1, v2) -> v1 + v2);
        final long start2 = System.currentTimeMillis();
        f44.thenAccept(result -> {
            System.out.println("thenCombine example : time lapsed should be 300 : " + (System.currentTimeMillis() - start2));
            System.out.println("thenCombine example : 1 + 2 = " + result);
        });

        // combine multiple futures
        CompletableFuture<Integer> f5 = supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });
        CompletableFuture<Integer> f6 = supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 2;
        });
        CompletableFuture<Integer> f7 = supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 3;
        });
        final long start3 = System.currentTimeMillis();
        CompletableFuture<Integer> f8 = CompletableFuture.allOf(f5, f6, f7).thenApply(v -> {
            return Stream.of(f5, f6, f7).map(CompletableFuture::join).reduce(Integer::sum).get();
        });
        f8.thenAccept(result -> {
            System.out.println("multi futures example : time lapsed should be 600 : " + (System.currentTimeMillis() - start3));
            System.out.println("multi futures example : 1 + 2 + 3 = " + result);
        });
    }

    public static void singleFuture() throws Exception {
        // blocking call
        CompletableFuture f1 = new CompletableFuture();
        try {
            f1.get(1, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("---------------------------------------------------------------------------------");

        // Run async, no return value
        CompletableFuture<Void> f2 = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(ran.nextInt(500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("runAsync example, no return value");
        });
        f2.get(10, TimeUnit.SECONDS);
        System.out.println("---------------------------------------------------------------------------------");

        // supply async, return value
        CompletableFuture<Integer> f3 = supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(ran.nextInt(500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int val = ran.nextInt(100);
            System.out.println("supplyAsync example, return value is " + val);
            return val;
        });

        f3.get(10, TimeUnit.SECONDS);
        System.out.println("---------------------------------------------------------------------------------");

        // theApply, thenAccept, thenRun
        // theApplyAsync, thenAcceptAsync, thenRunAsync
        CompletableFuture<Integer> f4 = supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(ran.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return ran.nextInt(100);
        });
        f4.thenApply(value -> value + 10).thenAccept(value -> System.out.println("final result is " + value));
        f4.thenRun(() -> System.out.println("It does not take any value, just run"));
    }
}
