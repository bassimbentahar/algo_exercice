package com.bassim.algo_exercice.selection;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomSelection {

    private static final int iThPosition = 9;

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 323, 4, 32, 6, 44, 8, 9, 10);

        int selected = select(list, 0, list.size() - 1, iThPosition);
        System.out.println(list.get(selected));
    }

    private static int select(List<Integer> list, int left, int right, int i) {
        if (left >= right) {return right;}

        int indexedPivot = getPivot(left, right);
        swap(list, indexedPivot, left);
        int pivotPosition = partitionAndGetPivotPosition(list, left, right);// la position du pivot

        int k = pivotPosition - left + 1; // rang du pivot dans le sous-tableau (1-based)
            // moins left car on veut dans le sous tableau et +1 pour avoir la position
        if(k == i) {return pivotPosition;}
        else if(k < i) {
            pivotPosition = select(list, pivotPosition + 1, right, i - k);
            // le i represente la position générale si on enlève le k, qui est la position
            // du pivot dans le tableau actuel
        }else if (k > i){
            pivotPosition = select(list, left, pivotPosition - 1, i);
        }

        return pivotPosition;
    }

    private static int partitionAndGetPivotPosition(List<Integer> arr, int left, int right) {
        int pivot = arr.get(left);
        int i = left + 1;

        for (int j = i; j <= right; j++) {
            int candidate = arr.get(j);
            if (candidate < pivot) {
                swap(arr, i, j);
                i++;
            }
        }

        swap(arr, left, i-1);
        return i-1;
    }

    private static int getPivot(int left, int right) {
            return new Random().nextInt(right - left + 1) + left;
    }

    private static void swap(List<Integer> arr, Integer i, Integer j) {
        Integer temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }

}
