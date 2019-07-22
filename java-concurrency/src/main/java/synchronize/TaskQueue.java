package synchronize;

import java.util.LinkedList;
import java.util.Queue;

public class TaskQueue {

    private Queue<Task> queue;
    private int capacity;

    public TaskQueue(int capacity) {
        this.queue = new LinkedList<>();
        this.capacity = capacity;
    }

    public synchronized Task take() throws InterruptedException {
        while (queue.isEmpty()) {
            System.out.println("queue is empty, wait here");
            wait();
        }
        Task task = queue.poll();
        notifyAll();
        return task;
    }


    public synchronized void put(Task task) throws InterruptedException {
        while (queue.size() >= capacity) {
            System.out.println("queue is full, wait here");
            wait();
        }
        queue.add(task);
        notifyAll();
    }
}
