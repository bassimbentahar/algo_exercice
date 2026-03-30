package com.bassim.algo_exercice.fibo;

public class ReversInteger {


    public static void main(String[] args) {

        System.out.println(reverse(-123));
    }

    public static int reverse(int i) {

        long rev = 0;
        while (i != 0) {
            int pop = i % 10;

            i = i / 10;

            rev = rev * 10 + pop;
        }

        if (rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE) return 0;
        return (int) rev;
    }

    public int reverse2(int x) {
        long rev = 0;

        while (x != 0) {
            int reste = x % 10;
            x = x / 10;
            rev = rev * 10 + reste;
        }
        if (rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE) return 0;
        return (int) rev;
    }
}
