package chapter1.singleton;

public class Elvis2 {

    private static final Elvis2 instance = new Elvis2();

    private Elvis2() {}
    
    public static Elvis2 getInstaince() {
        return instance;
    }
}
