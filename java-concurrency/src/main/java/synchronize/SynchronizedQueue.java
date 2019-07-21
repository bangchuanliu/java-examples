package synchronize;

import java.util.LinkedList;
import java.util.Queue;

public class SynchronizedQueue {
    
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        Thread p = new Thread(new Producer(queue));
        Thread c = new Thread(new Consumer(queue));
        
        p.start();
        c.start();
    }
}
