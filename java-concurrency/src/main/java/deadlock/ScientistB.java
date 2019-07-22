package deadlock;

public class ScientistB implements Runnable {
    private Chopstick left;
    private Chopstick right;

    public ScientistB(Chopstick left, Chopstick right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        synchronized (left) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (right) {
                System.out.println("start eat ...");
            }
        }
    }
}
