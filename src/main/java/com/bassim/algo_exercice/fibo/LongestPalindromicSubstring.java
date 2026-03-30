package com.bassim.algo_exercice.fibo;

public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        String string = "aabaa";
        String result = new LongestPalindromicSubstring().longestPalindrome3(string);
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
        return s.substring(current[1], current[2] + 1);
    }

    private int[] expandAroundCenter(int left, int right, String s) {
        while (right < s.length() && left >= 0 && s.charAt(left) == s.charAt(right)) {
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

    public String longestPalindrome3(String s) {
        int max = 0;
        String currentMax="";
        String maxS="";

        for (int i = 0; i < s.length(); i++) {
            int sh1 = expandFromCenter(s, i, i);
            int sh2 = expandFromCenter(s, i, i + 1);
            int shift = Math.max(sh1, sh2);
            //aaaa
            int start = i-shift+1;
            int end =  i + shift + (shift == sh2 ? 1 : 0);;

            currentMax = s.substring(start, end);

            if(maxS.length()<currentMax.length()) maxS=currentMax;
        }
        return maxS;
    }

    private int expandFromCenter(String s, int left, int right) {
        int shift = 0;
        while (right < s.length() && left >= 0 && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
            shift++;
        }
        return shift;
    }

    private boolean isP(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }


}
