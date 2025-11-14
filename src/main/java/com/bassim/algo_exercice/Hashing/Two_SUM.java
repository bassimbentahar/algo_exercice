package com.bassim.algo_exercice.Hashing;

import java.util.*;

public class Two_SUM {
    private static final long MAX_TARGET = 10000;
    private static final long MIN_TARGET = -10000;

    public static void main(String[] args) {
        List<Long> list = TestDataFactory.toListLongs("algo1-programming_prob-2sum.txt");
        HashSet<Long> map = new HashSet<>(list);

        // Thread 1 – version naïve
        Thread thread1 = new Thread(() -> {
            long start = System.nanoTime();
            calculate2_sums(list, map);
            long end = System.nanoTime();
            System.out.println("Temps d’exécution calculate2_sums: " + (end - start) / 1_000_000 + " ms");
        });

        // Thread 2 – version optimisée
        Thread thread2 = new Thread(() -> {
            long start = System.nanoTime();
            calculate2_sumsOptimisation(list, map);
            long end = System.nanoTime();
            System.out.println("Temps d’exécution calculate2_sumsOptimisation: " + (end - start) / 1_000_000 + " ms");
        });

        // Démarrage des deux threads
        thread1.start();
        thread2.start();

        // Attente de la fin des threads
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void calculate2_sums(List<Long> list, HashSet<Long> map) {
        Set<Long> nbNums = new HashSet<>();

        for (long number : list) {
            for (long target = MIN_TARGET; target <= MAX_TARGET; target++) {
                long complement = target - number;
                if (complement != number && map.contains(complement)) {
                    nbNums.add(target);
                }
            }
        }
        System.out.println("Résultat calculate2_sums: " + nbNums.size());
    }

    private static void calculate2_sumsOptimisation(List<Long> list, HashSet<Long> map) {
        Set<Long> nbNums = new HashSet<>();

        for (long target = MIN_TARGET; target <= MAX_TARGET; target++) {
            for (long x : list) {
                long y = target - x;
                if (y != x && map.contains(y)) {
                    nbNums.add(target);
                    break;
                }
            }
        }
        System.out.println("Résultat calculate2_sumsOptimisation: " + nbNums.size());
    }
}
