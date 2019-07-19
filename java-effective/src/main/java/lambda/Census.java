package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;

public class Census {

    public static void main(String[] args) {
        List<Person> list = init();

        //lambdas
        Collections.sort(list, (a, b) -> a.getAge() == b.getAge() ? Double.compare(a.getHeight(), b.getHeight()) : Integer.compare(a.getAge(), b.getAge()));
        list.sort((a, b) -> a.getAge() == b.getAge() ? Double.compare(a.getHeight(), b.getHeight()) : Integer.compare(a.getAge(), b.getAge()));
        list.sort(comparingInt((Person p) -> p.getAge()).thenComparingDouble((Person p) -> p.getHeight()));
        
        //method reference
        list.sort(comparingInt(Person::getAge).thenComparingDouble(Person::getHeight));


        comparator();
    }
    
    
    public static void comparator() {
        Integer a = new Integer(0);
        Integer b = new Integer(0);
        Integer c = new Integer(1);
        Integer d = new Integer(2);
        Integer e = new Integer(3);
        
        Integer[] array = {a,b,c,d,e};
        Arrays.stream(array).collect(Collectors.groupingBy(x -> x.hashCode())).values().forEach(System.out::print);

        PriorityQueue<Integer> q = new PriorityQueue<>(Comparator.comparingInt(Integer::intValue).reversed());
    }


    public static void sortByAgeHeight(List<Person> people) {
        people.sort((a, b) -> a.getAge() == b.getAge() ? Double.compare(a.getHeight(), b.getHeight()) : Integer.compare(a.getAge(), b.getAge()));
    }
    
    public static double getAverageHeight(List<Person> people) {
        return people.stream().mapToDouble(Person::getHeight).average().getAsDouble();
    }

    public static double getKidsAverageHeight(List<Person> people) {
        return people.stream().filter(p -> p.getAge() <= 12).mapToDouble(Person::getHeight).average().getAsDouble();
    }
    
    
    public static List<Person> init() {
        List<Person> list = new ArrayList<>();
        Person p1 = new Person("kevin", 22, 187);
        Person p2 = new Person("perter", 23, 175);
        list.add(p1);
        list.add(p2);
        
        return list;
    }
}
