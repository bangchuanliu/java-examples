package lambadas_streams;

import java.util.function.Function;

public class Executor {

    public <T, U, V, R> R execute(MyFunction<T, U, V, R> myFunction, T t, U u, V v) {
        System.out.println(Thread.currentThread().getName());
        return myFunction.transform(t, u, v);
    }

    public <T, R> R doExecute(Function<T, R> function, T t) {
        return function.apply(t);
    }

    public static void main(String[] args) {
        Executor executor = new Executor();
        executor.execute((a, b, c) -> new Person(a, b, c), "kevin", 22, 123);
        Person person = new Person("jack", 22, 111);
        System.out.println(executor.doExecute((Person p) -> p.getAge() / 2, person));
    }
}
