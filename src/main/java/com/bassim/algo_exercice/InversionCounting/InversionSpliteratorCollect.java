package com.bassim.algo_exercice.InversionCounting;


import com.bassim.algo_exercice.search.simpleSearchStream.utils.TestDataFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class InversionSpliteratorCollect {

    private static final String sINPUT_FILE =
            "IntegerArray.txt";

    public static void main(String[] args) {

        List<Long> longList =
                Objects.requireNonNull(TestDataFactory.getInput(sINPUT_FILE, System.lineSeparator()))
                        .stream()
                        .map(Long::parseLong)
                        .toList();

        ForkJoinPool forkJoinPool = new ForkJoinPool(5);
        Inversion inversionCounting = new Inversion(longList);
        Result result = forkJoinPool.invoke(inversionCounting);
        System.out.println(result.count);


    }

    private static class Result {
    long count;
    List<Long> sortedList;

        Result(long count, List<Long> sortedList) {
            this.count = count;
            this.sortedList = sortedList;
        }
    }

    private static class Inversion extends RecursiveTask<Result> {
        List<Long> list;

        Inversion(List<Long> sortedList) {
            this.list = sortedList;
        }

        @Override
        protected Result compute() {
            if (list.size() == 1) { return new Result(0, list); }
            List<Long> leftList = list.subList(0, list.size() / 2);
            List<Long> rightList = list.subList(list.size() / 2, list.size());

            Inversion inversionLeft = new Inversion(leftList);
            Inversion inversionRight = new Inversion(rightList);

            inversionRight.fork();

            return countInversionAndMerge(inversionLeft.compute(),inversionRight.join());
        }
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


    // resultat attendu: 2407905288
}
