package com.bassim.algo_exercice.exo;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        int left = 0;
        Set<Character> uniqueChar = new HashSet<>();

        for (int right = 0; right < s.length(); right++) {

            while (uniqueChar.contains(s.charAt(right))){
                uniqueChar.remove(s.charAt(left));
                left++;
            }
            uniqueChar.add(s.charAt(right));

            max = Math.max(max, right - left + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        int max = new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("pwwkew");
        System.out.println(max);
    }
}
