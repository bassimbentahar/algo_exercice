package com.bassim.algo_exercice.fibo;

public class Facteurs {
    public static void main(String[] args) {
        new Facteurs().printFacteurs(18);
    }

    private void printFacteurs(int n) {
        int d = 2;
        while (n != 1) {
            if (n % d == 0) { // si le reste de la division est 0 => ce nombre le divise
                System.out.println(d);
                n = n / d;
            } else {
                d++;
            }
        }
        System.out.println(",,,,,,,,,,,");
        int[] list = {1, 2, 3, 4, 5, 6, 7, 8};
        twoPointer(list);

    }

    private boolean nomPremiers(int n) {
        int cpt = 0;
        for (int d = 1; d <= n; d++) {
            if (n % d == 0) cpt++;
        }
        return cpt == 2;

    }

    private void twoPointer(int[] list) {

        int slowPointer = 0;
        int fastPointer = 0;

        while ( fastPointer < list.length && slowPointer < list.length ) {
            slowPointer++;
            fastPointer+=2;
        }
        System.out.println(list[slowPointer]);
    }
}
