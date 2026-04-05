package com.bassim.algo_exercice.exo;

import java.util.TreeMap;


public class RomanToInteger {

    static TreeMap<Character, Integer> romans = new TreeMap<>();

    static {
        romans.put('I', 1);
        romans.put('V', 5);
        romans.put('X', 10);
        romans.put('L', 50);
        romans.put('C', 100);
        romans.put('D', 500);
        romans.put('M', 1000);
    }

    public static int romanToInt(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            int value = romans.get(s.charAt(i));
            if (i + 1 < s.length() && value < romans.get(s.charAt(i + 1))) {
                res -= value;
            } else {
                res += value;
            }
        }
        return res;
    }

    public static int romanToInt2(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {

            int value = romans.get(s.charAt(i));

            if (i + 1 < s.length() && value< romans.get(s.charAt(i+1))){
                res-=value;
            }else{
                res+=value;
            }
        }
        return res;
    }
}

