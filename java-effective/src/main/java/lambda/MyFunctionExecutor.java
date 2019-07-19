package lambda;

import java.util.function.Function;

public class MyFunctionExecutor {

    public <T, U, V, R> R execute(MyFunction<T, U, V, R> myFunction, T t, U u, V v) {
        System.out.println(Thread.currentThread().getName());
        return myFunction.transform(t, u, v);
    }

    public <T, R> R doExecute(Function<T, R> function, T t) {
        return function.apply(t);
    }

    public static void main(String[] args) {
        MyFunctionExecutor myFunctionExecutor = new MyFunctionExecutor();
        myFunctionExecutor.execute((a, b, c) -> new Person(a, b, c), "kevin", 22, 123);
        Person person = new Person("jack", 22, 111);
        System.out.println(myFunctionExecutor.doExecute((Person p) -> p.getAge() / 2, person));
    }
}
