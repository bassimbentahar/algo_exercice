package com.bassim.algo_exercice.Spliterator;

import java.util.List;
import java.util.Spliterator;

public class SpliteratorExercie {
    public static void main(String[] args) {
        var quote = List.of("this","above","all","to", "thine", "own");
        Spliterator<String> secondHalf = quote.spliterator();
        Spliterator<String> firstHalf= secondHalf.trySplit();
        secondHalf.forEachRemaining(System.out::println);
        System.out.println("-----");
        firstHalf.forEachRemaining(System.out::println);
        firstHalf.tryAdvance(System.out::println);
        firstHalf.tryAdvance(System.out::println);
        firstHalf.tryAdvance(System.out::println);
        System.out.println("---------");
        var quote2 = List.of("this","above","all","to", "thine", "own");
        for(Spliterator<String> s = quote2.spliterator();s.tryAdvance(System.out::println);) continue;

    }
}
