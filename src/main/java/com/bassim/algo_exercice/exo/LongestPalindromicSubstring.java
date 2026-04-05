package com.bassim.algo_exercice.exo;

public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        String string = "aabaa";
        String result = new LongestPalindromicSubstring().longestPalindrome3(string);
        System.out.println(result);

    }

    /***
     *  Dynamic programming
     * @param s String
     * @return String
     */
    public String longestPalindromeDP(String s) {
        int n = s.length();
        int maxLength = 1;
        int start = 0;
        boolean[][] db = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            db[i][i] = true;
        }

        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                db[i][i + 1] = true;
                maxLength = 2;
                start = i;
            }
        }

        for (int len = 3; len <= n; len++) {
            for (int i = 0; i < n - len; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j) && db[i + 1][j - 1]) {
                    db[i][j] = true;
                    maxLength = 3;
                    start = i;
                }
            }
        }
        return s.substring(start, start + maxLength);
    }

    //a|a|a|a
    //0|1|2|3
    public String longestPalindrome(String s) {
        String maxS = "";
        String currentMax = "";
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandFCenter(i, i, s);
            int len2 = expandFCenter(i, i + 1, s);

            int len = Math.max(len1, len2);
            int start = i - (len - 1) / 2;
            int end = i + len / 2;

            maxS = s.substring(start, end + 1);
            // garantit que seul le palindrome le plus long rencontré jusqu’ici sera conservé.

            if (maxS.length() > currentMax.length()) {
                currentMax = maxS;
            }
        }
        return currentMax;
    }

    private int expandFCenter(int left, int right, String s) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            right++;
            left--;
        }
        return right - left - 1;
    }


    public String longestPalindrome3(String s) {
        int max = 0;
        String currentMax = "";
        String maxS = "";

        for (int i = 0; i < s.length(); i++) {
            int sh1 = expandFromCenter(s, i, i);
            int sh2 = expandFromCenter(s, i, i + 1);
            int shift = Math.max(sh1, sh2);

            int start = i - shift + 1;
            int end = i + shift + (shift == sh2 ? 1 : 0);

            currentMax = s.substring(start, end);

            if (maxS.length() < currentMax.length()) maxS = currentMax;
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
}
