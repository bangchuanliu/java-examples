package lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class CompareLockAndCAS {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        int count = 0;
        AtomicInteger cnt = new AtomicInteger(0);
        long start = System.currentTimeMillis();
        while (count < 100000000) {
            lock.lock();
            count++;
            lock.unlock();
        }
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();

        while (cnt.get() < 100000000) {
            cnt.incrementAndGet();
        }
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        count = 0;
        while (count < 100000000) {
            count++;
        }
        System.out.println(System.currentTimeMillis() - start);
    }
}
