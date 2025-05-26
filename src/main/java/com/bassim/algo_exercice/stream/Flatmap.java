package com.bassim.algo_exercice.stream;

import java.util.List;
import java.util.Spliterator;

public class Flatmap {
    public static void main(String[] args) {
        List<List<Integer>> lists = List.of(
                List.of(1,2,3,4),
                List.of(4,5,6),
                List.of(7,8)
        );

        lists.stream()
                .map(List::stream)
                .distinct();
        for(Spliterator s=lists.spliterator();
        s.tryAdvance(System.out::println););



    }
}
