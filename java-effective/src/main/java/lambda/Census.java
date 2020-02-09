package lambda;

import java.util.*;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;

public class Census {

    public static void main(String[] args) {
        comparator();
    }

    public static void lambda() {
        List<Person> people = Arrays.asList(Person.of("kevin", 22, 187, 66, Person.Gender.MALE), Person.of("perter", 23, 175, 77, Person.Gender.MALE));

        //lambdas
        people.sort((a, b) -> Integer.compare(a.getAge(), b.getAge()));
        people.sort((a, b) -> a.getAge() == b.getAge() ? Double.compare(a.getHeight(), b.getHeight()) : Integer.compare(a.getAge(), b.getAge()));
        people.sort(comparingInt(Person::getAge).thenComparingDouble(Person::getHeight));

        Integer[] array = {1, 2, 3, 4, 5};
        Arrays.stream(array).collect(Collectors.groupingBy(x -> x.hashCode())).values().forEach(System.out::print);
        PriorityQueue<Integer> q = new PriorityQueue<>(comparingInt(Integer::intValue).reversed());
    }

    public static void comparator() {
        Person p1 = Person.of("kevin", 22, 187, 66, Person.Gender.MALE);
        Person p2 = Person.of("perter", 22, 175, 77, Person.Gender.FEMALE);

        ToIntFunction<Person> toAge = p -> p.getAge();
        Function<Person, Double> toHeight = p -> p.getHeight();
        Comparator<Person> ageComparator = (a, b) -> Integer.compare(a.getAge(), b.getAge());
        Comparator<Person> heightComparator = (a, b) -> Double.compare(a.getHeight(), b.getHeight());
        Comparator<Person> weightComparator = (a, b) -> Double.compare(a.getWeight(), b.getWeight());

        System.out.println(p1.compare(p2, ageComparator.thenComparing(heightComparator).thenComparing(weightComparator)));
        System.out.println(p1.compare(p2, comparingInt(Person::getAge).thenComparingDouble(Person::getHeight).thenComparingDouble(Person::getWeight)));
    }

    public static double getAverageHeight(List<Person> people) {
        return people.stream().mapToDouble(Person::getHeight).average().getAsDouble();
    }

    public static double getKidsAverageHeight(List<Person> people) {
        return people.stream().filter(Person::isKid).mapToDouble(Person::getHeight).average().getAsDouble();
    }
    
    public static void filter(List<Person> people) {
        people.stream().filter(Census::isQualifyInArmy).count();
    }
    
    public static boolean isQualifyInArmy (Person p) {
        return p.getAge() < 25 && p.getHeight() > 170 && p.getWeight() < 100;
    }
}
