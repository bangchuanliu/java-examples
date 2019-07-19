package lambadas_streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 3, 5, 11, 2, 4);
        System.out.println(list.stream().count());
        list.stream().filter(num -> num % 2 == 1).mapToInt(Integer::intValue).average().ifPresent(System.out::println);

        
        List<Integer> list2 = Arrays.asList(1, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3);
        list2.stream().collect(Collectors.groupingBy(x -> x)).values().stream().sorted(Comparator.comparing(Collection::size, Comparator.reverseOrder())).limit(2).forEach(System.out::print);
        System.out.println();
        list2.stream().collect(Collectors.groupingBy(x -> x % 2 == 1)).forEach((x, y) -> {System.out.println(x);System.out.println(y);});
        System.out.println();

        // get longest string
        System.out.println(Stream.of("foo", "test", "a").reduce((w1, w2) -> w1.length() > w2.length() ? w1 : w2).orElse(""));

        // sort in reversed order
        Stream.of("foo", "test", "a").sorted(Comparator.comparingInt(String::length).reversed());
        
        
    }
}
