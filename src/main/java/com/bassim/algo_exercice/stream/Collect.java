package com.bassim.algo_exercice.stream;

import org.apache.logging.log4j.util.Strings;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingLong;

public class Collect {
    public static void main(String[] args) {

        Map<String, Long> matchingCharactersMap =
                Pattern.compile(",")
                        .splitAsStream("do,do,re,re,re,mi,fa,sol,re,la,si")
                        .collect(groupingBy(
                                Function.identity(), // a function that always returns its input argument=> clé = la chaîne elle-même
                                TreeMap::new,//type de map (triée alphabétiquement)
                                summingLong(String::length) // somme des longueurs
                        ));

        System.out.println(matchingCharactersMap);
        //-------------  custom collector

        List<String> list = List.of("fa", "sol", "re", "la", "si");

        var s = toStringCustom(list);
        System.out.println(s);

        //__________________
        List<String> list3 = List.of("aaa", "jhjhj", "jklk", "uuuu", "aaa");
        Set<String> set = list3.stream()
                .collect(Collectors.toCollection(TreeSet::new));


        set.forEach(System.out::println);


        //------------
        Map<String, Integer> map = list3.stream()
                .collect(Collectors.toMap(
                                v -> v,
                                v -> v.length(),
                                (a, b) -> a + b, // ça merge les 2 "aaa"  et le lenght devient 3 +3 =6
                                HashMap::new
                        )
                );
        map.forEach((k, v) -> System.out.println(k + ": " + v));

        //------------

        String s1 = list3.stream()
                .collect(Collectors.joining(" - ", "{", "}"));

        System.out.println(s1);

        //-----------

        var res = list3.stream()
                .collect(Collectors.teeing(
                        Collectors.joining(),
                        Collectors.counting(),
                        (a, b) -> Map.of(a, b)
                ));
        System.out.println(res);

        //----------


        int res1 = list3.stream()
                .collect(
                        Collectors.summingInt(String::length)
                );

        int res2 = list3.stream()
                .mapToInt(String::length)
                .sum();
        // res1 = res2
        System.out.println(res1 == res2);

        int res3 = list3.stream()
                .collect(Collectors.mapping(
                        String::length,
                        Collectors.reducing(0, (a, b) -> a + b)// downstream , il recoit de laFunction d'avant et collect
                ));

        System.out.println(res3);
        //-----------
        IntSummaryStatistics intSummaryStatistics = list3.stream()
                .mapToInt(String::length)
                .summaryStatistics();

        System.out.println(intSummaryStatistics.getAverage());

        IntSummaryStatistics summaryStatistics = list3.stream()
                .collect(Collectors.summarizingInt(String::length));

        System.out.println(summaryStatistics);// ça donne : IntSummaryStatistics{count=5, sum=19, min=3, average=3,800000, max=5}

    }

    private static String toStringCustom(List<String> list){
        return list.stream().collect(Collector.of(
                ()-> new StringJoiner("|"),
                (j, r) -> j.add(r),
                StringJoiner::merge,
                StringJoiner::toString
        ));
    }
}
