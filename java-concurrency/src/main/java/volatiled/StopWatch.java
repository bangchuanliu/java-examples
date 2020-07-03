package volatiled;

import java.util.concurrent.TimeUnit;

/**
 * volatile keyword forces CPU always reads data from memory instead of L1 cache
 */
public class StopWatch {

    // avoid compiler reordering of program 
    private static volatile boolean stopRequested;

    public static void main(String[] args) throws InterruptedException {
        new Thread(StopWatch::increase).start();
        TimeUnit.SECONDS.sleep(5);
        stopRequested = true;
    }

    /**
     *  if no volatile keyword on stopRequested, code will be reordered
     * @Code if (!stopRequested) {
     *     while (true) {
     *         i++;
     *     }
     * }
     *  it would be endless even stopRequested is set to true later
     * 
     */
    public static void increase() {
        int i = 0;
        while (!stopRequested) {
            i++;
            System.out.println(i);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
