package lambda;

@FunctionalInterface
public interface MyFunction<T, U, V, R> {

    R transform(T t, U u, V v);
 
}