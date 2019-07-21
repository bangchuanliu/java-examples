package chapter1.staticmethod;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;

import static chapter1.staticmethod.Card.JACK;
import static chapter1.staticmethod.Card.KING;
import static chapter1.staticmethod.Card.QUEEN;

public class Main {
    
    public static void main(String[] args) {
        Set<Card> enumSet = EnumSet.of(JACK, QUEEN,KING);
        System.out.println(Arrays.toString(enumSet.toArray()));

    }
}
