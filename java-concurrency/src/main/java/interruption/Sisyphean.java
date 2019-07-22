package interruption;

public class Sisyphean {

    /**
     * explicitly check interrupt signal
     * if not, interrupt signal would be no impact on program
     */
    public void rollStoneOnCheckInterrupt() {
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Sisyphus is saved");
                break;
            } else {
                System.out.println("roll stone to hill");
            }
        }
    }

    /**
     *  program will run forever even there is interrupt signal from other thread
     */
    public void rollStoneForever() {
        while (true) {
            System.out.println("roll stone to hill");
        }
    }


    /**
     * Accept interrupt signal in JDK function
     */
    public void rollStone() {
        while (true) {
            System.out.println("roll stone to hill");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Sisyphus is saved");
                Thread.currentThread().interrupt();
            }
        }
    }
}
