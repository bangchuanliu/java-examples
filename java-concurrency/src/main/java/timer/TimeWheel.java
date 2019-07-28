package timer;

public class TimeWheel {
    
    public static void main(String[] args){
        
    }
    
    
    
    
    
    static class TimeWheelTask implements Runnable {

        @Override
        public void run() {
            System.out.println("execute task");
        }
    }
}
