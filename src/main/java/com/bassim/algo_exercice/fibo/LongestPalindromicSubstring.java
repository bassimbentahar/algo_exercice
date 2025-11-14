package com.bassim.algo_exercice.fibo;

public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        String string = "ccc";
        String result = new LongestPalindromicSubstring().longestPalindrome(string);
        System.out.println(result);

    }

    public String longestPalindrome(String s) {
        int max = 1;
        int[] current = new int[3];
        for (int i = 0; i < s.length(); i++) {
            int[] expanded = expandAroundCenter(i, i, s);
            int[] expandedBi = expandAroundCenter(i, i + 1, s);
            if (expanded[0] > expandedBi[0]) {
                if (max < expanded[0]) {
                    max = expanded[0];
                    current = expanded;
                }
            } else {
                if (max < expandedBi[0]) {
                    max = expandedBi[0];
                    current = expandedBi;
                }
            }
        }
        return s.substring(current[1], current[2]+1);
    }

    private int[] expandAroundCenter(int left, int right, String s) {
        while (right < s.length()  && left >= 0 && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        left++;
        right--;
        return new int[]{right + 1 - left, left, right};
    }

    public String longestPalindrome2(String s) {
        var logest = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String sub = s.substring(i, j);
                if (logest.length() < sub.length() && isPalindromic(sub)) {
                    logest = sub;
                }
            }
        }
        return logest;
    }

    private boolean isPalindromic(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


}
