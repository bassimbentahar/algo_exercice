package com.bassim.algo_exercice.fibo;

import java.util.HashMap;
import java.util.Map;

// sliding window avec HashMap
public class LongestSubstring {

    public static void main(String[] args) {
        int lg = new LongestSubstring().lengthOfLongestSubstring("abba");
        System.out.println(lg);
    }

    public int lengthOfLongestSubstring(String s) {
        if (s.isEmpty()) return 0;
        if (s.length() == 1) return 1;

        int max = 0;
        Map<Character, Integer> lastDoublon = new HashMap<>();
        int start = 0;

        for (int i = 0; i < s.length(); i++) {
            char letter = s.charAt(i);

            if (lastDoublon.containsKey(letter) && lastDoublon.get(letter) >= start) {
                start = lastDoublon.get(letter) + 1;
            }
            lastDoublon.put(letter, i);

            max = Math.max(i - start + 1, max);
        }
        return max;
    }
}
