package list;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;

public class BlockingList {
    public static void main(String[] args) throws InterruptedException {
        arrayBlockingQueue();

//        disruptor();
    }

    public static void arrayBlockingQueue() {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10000000);
        long start = System.currentTimeMillis();
        new Thread(() -> {
            int j = 0;
            while (queue.offer(j)) {
                j++;
            }
            System.out.println(System.currentTimeMillis() - start);
        }).start();

        new Thread(() -> {
            while (!queue.isEmpty()) {
                queue.poll();
            }
        }).start();
    }

    public static void disruptor() throws InterruptedException {
        ThreadFactory threadFactory = r -> new Thread(r, "simpleThread");
        EventFactory<Element> factory = () -> new Element();
        EventHandler<Element> handler = (element, sequence, endOfBatch) -> System.out.println("Element: " + element.get());

        BlockingWaitStrategy strategy = new BlockingWaitStrategy();
        int bufferSize = 16;

        Disruptor<Element> disruptor = new Disruptor(factory, bufferSize, threadFactory, ProducerType.SINGLE, strategy);

        disruptor.handleEventsWith(handler);

        disruptor.start();

        RingBuffer<Element> ringBuffer = disruptor.getRingBuffer();

        for (int l = 0; true; l++) {
            long sequence = ringBuffer.next();
            try {
                Element event = ringBuffer.get(sequence);
                event.set(l);
            } finally {
                ringBuffer.publish(sequence);
            }
            Thread.sleep(10);
        }
    }
}

class Element {

    private int value;

    public int get() {
        return value;
    }

    public void set(int value) {
        this.value = value;
    }
} 
    
    

