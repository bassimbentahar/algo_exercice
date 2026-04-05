package com.bassim.algo_exercice.exo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsPhoneNumberEx {
    static Map<String, List<String>> map = new HashMap<>();

    static {
        map.put("2", List.of("a", "b", "c"));
        map.put("3", List.of("d", "e", "f"));
        map.put("4", List.of("g", "h", "i"));
        map.put("5", List.of("j", "k", "l"));
        map.put("6", List.of("m", "n", "o"));
        map.put("7", List.of("p", "q", "r", "s"));
        map.put("8", List.of("t", "u", "v"));
        map.put("9", List.of("w", "x", "y", "z"));
    }

    public static void main(String[] args) {
        letterCombinations("23").forEach(System.out::println);
    }

    public static List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) return List.of();
        int length = digits.length();
        if (length <= 1) return map.get(digits);

        String left = digits.substring(0, length / 2);
        String right = digits.substring(length / 2);

        return combine(letterCombinations(left), letterCombinations(right));
    }

    private static List<String> combine(List<String> left, List<String> right) {
        return left.stream()
                .flatMap(l -> right.stream().map(r -> l + r))
                .toList();
    }
}
