package com.bassim.algo_exercice.sorted;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class mergeJoinFork {
    public static void main(String[] args) {
        List<Integer> list = List.of(5, 4, 0, 300, 6, 3, 2, 13, 12, 11, 100, 200);
        List<Integer> listSorted;


        ForkJoinPool pool = new ForkJoinPool(5);
        MergeSort mergeSort = new MergeSort(list);
        listSorted = (List<Integer>) pool.invoke(mergeSort);


        System.out.println(listSorted);
    }

    public static class Result {
        long count;
        List<Long> sortedList;
        Result(long count, List<Long> sortedList) {
            this.count = count;
            this.sortedList = sortedList;
        }
    }

    public static class MergeSort<T extends Comparable<T>> extends RecursiveTask<List<T>> {

        private List<T> list;

        public MergeSort(List<T> list) {
            this.list = list;
        }

        @Override
        protected List<T> compute() {
            if (list.size() == 1) return list;
            List<T> leftList = list.subList(0, list.size() / 2);
            List<T> rightList = list.subList(list.size() / 2, list.size());

            MergeSort left = new MergeSort(leftList);
            MergeSort right = new MergeSort(rightList);

            right.fork();
            leftList = left.compute();
            rightList = (List<T>) right.join();

            return merge(leftList, rightList,list);
        }

        private List<T> merge(List<T> leftList, List<T> rightList, List<T> list) {
            if (list.size() <= 1) return list;
            List<T> mergedList = new ArrayList<>();
            int i = 0, j = 0;
            while (i < leftList.size() && j < rightList.size()) {
                if (leftList.get(i).compareTo(rightList.get(j)) <= 0 ) {
                    mergedList.add(leftList.get(i++));
                }else {
                    mergedList.add(rightList.get(j++));
                }
            }
            while (i < leftList.size()) {
                mergedList.add(leftList.get(i++));
            }
            while (j < rightList.size()) {
                mergedList.add(rightList.get(j++));
            }
            return mergedList;
        }
    }
}
