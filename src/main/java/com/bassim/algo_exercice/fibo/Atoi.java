package com.bassim.algo_exercice.fibo;

public class Atoi {

    public static void main(String[] args) {
        System.out.println(myAtoi2("   -042"));
    }

    public static int myAtoi2(String s) {
        if (s == null || s.isEmpty()) return 0;
        s=s.trim();
        if (s.isEmpty()) return 0;

        int sign = 1;
        int i = 0;
        if (s.charAt(0) == '-') {
            sign = -1;
            i++;
        } else if (s.charAt(i)=='+') {
            i++;
        }

        int result = 0;
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';

            if (result > (Integer.MAX_VALUE - digit)/10) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            result = result * 10 + digit;

            i++;
        }

        return result*sign;
    }

    public static int myAtoi(String s) {
        if (s.isEmpty()) return 0;

        s = s.trim();
        char signe = '+';
        if (s.charAt(0) == '+' || s.charAt(0) == '-') {
            signe = s.charAt(0);
            s = s.substring(1);
        }
        for (int i = 0; i < s.length() && s.charAt(i) == '0'; i++) {
            s = s.substring(1);
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            sb.append(s.charAt(i));
            i++;
        }
        if (sb.length() == 0) return 0;
        sb.insert(0, signe);

        try {
            return Integer.parseInt(sb.toString());
        } catch (NumberFormatException e) {
            // Dépassement → bornes
            return signe == '-' ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }
    }
}
