package com.bassim.algo_exercice.QuickSort.simple;

import com.bassim.algo_exercice.QuickSort.simple.utils.PivotChoice;
import com.bassim.algo_exercice.QuickSort.simple.utils.TestDataFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static com.bassim.algo_exercice.QuickSort.simple.utils.PivotChoice.*;

public class QuickSortAlgo {
    private static int totalComparisons = 0;
    private static final String sINPUT_FILE =
            "quickSortList.txt";
    private static final PivotChoice PIVOT_CHOICE = MEDIAN_OF_THREE;
    public static void main(String[] args) {
        List<Long> longList =
                Objects.requireNonNull(TestDataFactory.getInput(sINPUT_FILE, System.lineSeparator()))
                        .stream()
                        .map(Long::parseLong)
                        .toList();
        List<Long> list = new ArrayList<>(longList);
        quickSort(list, 0, list.size() - 1);
        list.forEach(System.out::println);
        System.out.println("---------");
        System.out.println(totalComparisons);
    }

    private static void quickSort(List<Long> arr, int left, int right) {
        if (left >= right) return;
        totalComparisons += (right - left);

        int pivotIndex = getPivot(arr, left, right);
        swap(arr, left, pivotIndex); // mettre le pivot en première position (comme l'attend la partition)
        int partitionIndex = partitionAndGetLimitOut(arr, left, right);

        quickSort(arr, left, partitionIndex - 1);
        quickSort(arr, partitionIndex + 1, right);
    }

    private static int partitionAndGetLimitOut(List<Long> arr, int left, int right) {
        long pivot = arr.get(left);
        int i = left + 1; // on commence à comparer à partir de left +1
        // on suppose que tt ce qui est à gauche est ok

        for (int j = i; j <= right; j++) {
            if (arr.get(j) < pivot) {
                swap(arr, j, i); // on change la position de l'élèment plus grand
                // on remplace l'élément plus petit que le pivot la position actuelle de i qui
                // représente le premier élément plus grand que le pivot
                i++; // on incrément le i à droite, qui représente l'ancienne position  du pivot
                // en fait i avance si on trouve un élément plus petit

            }
        }
        swap(arr, left, i-1);
        // on met dans le i car tout ce  qui est avant le i est plus petit que le pivot
        return i -1;
    }

    private static int getPivot(List<Long> arr, int left, int right) {
        return  switch (PIVOT_CHOICE) {
            case FIRST_ELEMENT -> left;
            case LAST_ELEMENT -> right;
            case RANDOM -> getRandomPivot(left, right);
            case MEDIAN_OF_THREE -> getMedianOfThreePivot(arr, left, right);
        };
    }

    private static int getMedianOfThreePivot(List<Long> arr, int left, int right) {
        int middle = left + (right - left) / 2; // fonctionne pour pair et impair

        long a = arr.get(left);
        long b = arr.get(middle);
        long c = arr.get(right);

        // Retourner l’index correspondant à la médiane des valeurs (a, b, c)
        if ((a - b) * (c - a) >= 0) return left;        // a est la médiane
        else if ((b - a) * (c - b) >= 0) return middle; // b est la médiane
        else return right;                              // c est la médiane
    }


    private static int getRandomPivot(int left, int right) {
        Random random = new Random();
        return random.nextInt(right - left + 1) + left;
    }


    private static void swap(List<Long> arr, Integer i, Integer j) {
        Long temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }
}
