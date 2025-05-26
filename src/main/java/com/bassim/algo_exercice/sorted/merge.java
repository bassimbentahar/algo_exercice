package com.bassim.algo_exercice.sorted;

import java.util.ArrayList;
import java.util.List;

public class merge {
    public static void main(String[] args) {
        List<Integer> list = List.of(5,4,0,6,3,2,13,12,11);

        List<Integer> listSorted = sort(list);

        System.out.println(listSorted);
    }

    private static List<Integer> sort(List<Integer> list) {
        if (list.size() == 1) return list;

        List<Integer> left = sort(list.subList(0, list.size() / 2));
        List<Integer> right = sort(list.subList(list.size() / 2, list.size()));

        return merge(left, right, list);
    }

    private static List<Integer> merge(List<Integer> left, List<Integer> right, List<Integer> list) {
        if(list.size() == 1) return list ;

        List<Integer> merged  = new ArrayList<>(list.size());
        int i = 0, j = 0;

       /* [2,4] [3,5]
        [2] i=> 1
        [2,3] j=> 1
        [2,3,4] i=> 2*/

        while ( i < left.size() && j < right.size()) {
            if (left.get(i) <= right.get(j)) {
                merged.add(left.get(i++));
            } else {
                merged.add(right.get(j++));
            }
        }
            while (i < left.size()) {
                merged.add(left.get(i++));
            }

            while (j < right.size()) {
                merged.add(right.get(j++));
            }

        return merged;
    }
}
