package com.bassim.algo_exercice.selection;

import java.util.ArrayList;
import java.util.List;

public class Selection {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 323, 4, 32, 6, 44, 8, 9, 10);

        List<Integer> sorted = sort(list);
        int element = sorted.get(5);
        sorted.forEach(System.out::println);
        System.out.println(element-1);
    }

    private static List<Integer> sort(List<Integer> list) {
        if (list.size() == 1) return list;

        List<Integer> left = sort(list.subList(0, list.size()/2));
        List<Integer> right = sort(list.subList(list.size()/2, list.size()));

        return merge(left, right);
    }

    private static List<Integer> merge(List<Integer> left, List<Integer> right) {
        if (left.isEmpty() && right.isEmpty()) return new ArrayList<>();

        List<Integer> merged  = new ArrayList<>(left.size() + right.size());
        int i = 0, j = 0;
        while (i < left.size() && j < right.size()) {
            if(left.get(i) < right.get(j)) {
                merged.add(left.get(i++));
            }else {
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
