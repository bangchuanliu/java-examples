package chapter1.singleton;

public enum SingletonEnum {

    INSTANCE;

    int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


    public static void main(String[] args) {
        SingletonEnum singletonEnum = SingletonEnum.INSTANCE;
        singletonEnum.setValue(10);

        System.out.println(singletonEnum.getValue());
    }
}
