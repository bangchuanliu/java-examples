package deadlock;

public class Dinner {
    
    public static void main(String[] args) {
        Chopstick left = new Chopstick();
        Chopstick right = new Chopstick();
        
        ScientistA scientistA = new ScientistA(left,right);
        ScientistA scientistB = new ScientistA(left,right);
        Thread t1 = new Thread(scientistA);
        Thread t2 = new Thread(scientistB);
        
        t1.start();
        t2.start();
    }
}
