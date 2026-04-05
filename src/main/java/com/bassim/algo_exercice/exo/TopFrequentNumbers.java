package com.bassim.algo_exercice.exo;

import java.util.*;
import java.util.stream.Collectors;

public class TopFrequentNumbers {

    public List<Map.Entry<Integer, Long>> topFrequent(List<Integer> numbers) {

        return numbers.stream()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()))
                .entrySet().stream()
                .sorted((a,b)->Long.compare(b.getValue(),a.getValue()))
                .toList();
    }


    public static void main(String[] args) {
        TopFrequentNumbers tf = new TopFrequentNumbers();
        List<Integer> input = Arrays.asList(1, 1, 2, 3, 3, 3, 4, 4, 4, 4);
        List<Map.Entry<Integer, Long>> result = tf.topFrequent(input);
        System.out.println(result);
    }
}
