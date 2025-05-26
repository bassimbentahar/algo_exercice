package com.bassim.algo_exercice.InversionCounting;

import java.util.ArrayList;
import java.util.List;

public class InversionCounting {
    public static void main(String[] args) {
            List<Long> list = List.of(6L, 5L, 4L, 3L, 2L, 1L);

        System.out.println(count(list).count);
    }

    private static class Result {
        long count;
        List<Long> sortedList;
        Result(long count, List<Long> sortedList) {
            this.count = count;
            this.sortedList = sortedList;
        }
    }


    private static Result count(List<Long> list) {
        int n = list.size();
        if (n == 1) return new Result(0L, list);


        var leftList = list.subList(0, n / 2);
        var rightList = list.subList(n / 2, n);

        Result left = count(leftList);
        Result right = count(rightList);

        return countInversionAndMerge(left, right);



    }
    private static Result countInversionAndMerge(Result leftResult, Result rightResult) {
        long count = leftResult.count + rightResult.count;
        List<Long> left = leftResult.sortedList;
        List<Long> right = rightResult.sortedList;
        List<Long> sorted = new ArrayList<>();
        int i = 0;
        int j = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i) <= right.get(j)) {
                sorted.add(left.get(i++));
            } else {
                sorted.add(right.get(j++));
                count += left.size() - i;
            }
        }
        while (i < left.size()) { sorted.add(left.get(i++)); }
        while (j < right.size()) { sorted.add(right.get(j++)); }

        return new Result(count, sorted);
    }
}
