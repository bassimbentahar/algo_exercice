package com.bassim.algo_exercice.exo;

import java.util.*;

public class LetterCombinationsPhoneNumber {
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
        letterCombinations("234").forEach(System.out::println);
    }

    public static List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) return new ArrayList<>();
        if (digits.length() == 1) {
            return map.get(STR."\{digits.charAt(0)}");
        }
        int m = digits.length() / 2;
        List<String> left = letterCombinations(digits.substring(0, m));
        List<String> right = letterCombinations(digits.substring(m));

        return combine(left, right);
    }

    private static List<String> combine(List<String> firstList, List<String> secondList) {
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (String s1 : firstList) {
            for (String s2 : secondList) {
                sb.delete(0,sb.length());
                sb.append(s1).append(s2);
                list.add(sb.toString());
            }
        }
        return list;
    }
}



class LetterCombinationsPhoneNumber2 {
    static Map<Character, String> map = Map.of(
            '2', "abc",
            '3', "def",
            '4', "ghi",
            '5', "jkl",
            '6', "mno",
            '7', "pqrs",
            '8', "tuv",
            '9', "wxyz"
    );

    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.isEmpty()) return result;
        backtrack(digits, 0, new StringBuilder(), result);
        return result;
    }

    private static void backtrack(String digits, int index, StringBuilder path, List<String> result) {
        if (index == digits.length()) {
            result.add(path.toString());
            return;
        }
        char digit = digits.charAt(index);
        String letters = map.get(digit);
        for (char c : letters.toCharArray()) {
            path.append(c);
            backtrack(digits, index + 1, path, result);
            path.deleteCharAt(path.length() - 1); // backtrack
        }
    }

    public static void main(String[] args) {
        System.out.println(letterCombinations("234"));
    }
}

class SolutionAutre {
    List<String> result = new ArrayList<>();
    public void solve(int idx, String digits, StringBuilder temp, Map<Character, String> mp) {
        if (idx == digits.length()){
            result.add(temp.toString());
            return;
        }
        char ch = digits.charAt(idx);
        String str = mp.get(ch);
        for(int i = 0;i< str.length();i++){
            temp.append(str.charAt(i));
            solve(idx + 1,digits, temp,mp);
            temp.deleteCharAt(temp.length() - 1);
        }
    }
    public List<String> letterCombinations(String digits) {
        if(digits.length() == 0)
            return result;
        Map<Character, String>  mp = new HashMap<>();
        mp.put('2',"abc");
        mp.put('3',"def");
        mp.put('4',"ghi");
        mp.put('5',"jkl");
        mp.put('6',"mno");
        mp.put('7',"pqrs");
        mp.put('8',"tuv");
        mp.put('9',"wxyz");
        solve(0,digits,new StringBuilder(), mp);
        return result;
    }
}
