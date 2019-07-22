package synchronize;

public class Coordinator {

    public static void main(String[] args) {
        TaskQueue queue = new TaskQueue(5);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        Thread p = new Thread(producer);
        Thread c = new Thread(consumer);
        p.start();
        c.start();
    }
}
