package sychronized;

public class Counter {

    private static int count = 0;

    public static void increase() {
        count++;
        System.out.println(count);
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                increase();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                increase();
            }
        });
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                increase();
            }
        });
        Thread t4 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                increase();
            }
        });
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
