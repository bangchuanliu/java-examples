package deadlock;

public class ScientistA implements Runnable {

    private Chopstick left;
    private Chopstick right;

    public ScientistA(Chopstick left, Chopstick right) {
        this.left = left;
        this.right = right;
    }
    
    @Override
    public void run() {
        synchronized (right) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (left) {
                System.out.println("start eat ...");
            }
        }
    }
}
