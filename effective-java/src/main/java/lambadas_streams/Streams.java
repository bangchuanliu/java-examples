package lambadas_streams;

import java.util.Arrays;
import java.util.List;

public class Streams {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 3, 5, 11, 2, 4);
        System.out.println(list.stream().count());
        list.stream().filter(num -> num % 2 == 1).mapToInt(Integer::intValue).average().ifPresent(System.out::print);
    }
}
