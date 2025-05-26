package com.bassim.algo_exercice.stream;

import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Stream;

public class Reduce {

    public static void main(String[] args) {

        int sum = Stream.of("do", "re", "mi", "fa", "sol", "la", "si")
                .mapToInt(String::length)
                .reduce(0, Integer::sum);
//                .reduce(0, (a, b)-> a + b);

        System.out.println(sum);

        Optional<String> concat = Stream.of("do", "re", "mi", "fa", "sol", "la", "si")
                .reduce((a, b)-> a +" "+ b);

        System.out.println("concat: " + concat.get());

    }
}
