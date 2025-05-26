package com.bassim.algo_exercice.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Execice1 {
    public static void main(String[] args) {
        List<String> list = List.of("abcd","efgh","ijkl");

        list.stream()
                .mapToInt(String::length)
                .sum();

        /**
         ne fonctione pas car map retourne un Stream d'object
         list.stream()
         .map(String::length)
         .sum();

         */

        List<String> list2 = List.of("ab2c2d","ef4g5h","i1j6kl");

        list2.stream()
                .map(Execice1::extractDigitsGrouped)
                .flatMap(List::stream)
                .forEach(System.out::println);

        System.out.println("------------");
        Stream.of(1, 2, 5,4,7,9,0,3)
                .sorted(Comparator.comparingInt(a -> a))
                .forEach(System.out::println);





    }

    private static List<Integer> extractDigits(String input) {
        return input.chars()
                .filter(Character::isDigit)
                .map(Character::getNumericValue)
                .boxed().collect(toList());

    }

    private static List<Integer> extractDigitsGrouped(String input) {
        List<Integer> result = new ArrayList<>();
        Matcher matcher = Pattern.compile("\\d+").matcher(input);
        while (matcher.find()) {
            result.add(Integer.parseInt(matcher.group()));
        }
        return result;
    }
}
