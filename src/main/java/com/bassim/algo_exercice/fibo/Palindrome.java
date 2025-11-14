package com.bassim.algo_exercice.fibo;

public class Palindrome {

    public static void main(String[] args) {
        System.out.println(isPalindrome2(111));
    }

    public static boolean isPalindrome(int x) {
        String s = String.valueOf(x);
        if (s.length() == 1) return true;
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    public static boolean isPalindrome2(int x) {
        if (x < 0) return false;
        int nbDigits = 1;
        int y = x;
        while (y >= 10) {
            nbDigits = nbDigits * 10;
            y = y / 10;
        }
        int res = x;
        while (res > 0) {
            int right = res % 10;
            int left = res / nbDigits;
            if (right != left) return false;


            // Supprimer le premier et dernier chiffre
            res = (res - left * nbDigits - right) / 10;
            //res = (res % nbDigits)/10

            nbDigits /= 100;
        }
        return true;
    }
}
