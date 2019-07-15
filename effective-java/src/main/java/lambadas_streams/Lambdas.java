package lambadas_streams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Comparator.comparingInt;

public class Lambdas {

    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        Person p1 = new Person("kevin", 22, 187);
        Person p2 = new Person("perter", 23, 175);
        list.add(p1);
        list.add(p2);

        //lambdas
        Collections.sort(list, (a, b) -> a.getAge() == b.getAge() ? Double.compare(a.getHeight(), b.getHeight()) : Integer.compare(a.getAge(), b.getAge()));
        list.sort((a, b) -> a.getAge() == b.getAge() ? Double.compare(a.getHeight(), b.getHeight()) : Integer.compare(a.getAge(), b.getAge()));
        list.sort(comparingInt((Person p) -> p.getAge()).thenComparingDouble((Person p) -> p.getHeight()));
        
        //method reference
        list.sort(comparingInt(Person::getAge).thenComparingDouble(Person::getHeight));
    }
}
