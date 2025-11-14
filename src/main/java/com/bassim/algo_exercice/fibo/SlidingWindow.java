package com.bassim.algo_exercice.fibo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SlidingWindow {
    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 1, 3, 2};
        int k = 3;

        new SlidingWindow().maxSum(arr, k);

        String s = "abcba";
        k = 2;

        new SlidingWindow().longestWithKDistinctsCharacters(s, 2);

    }

    private void maxSum(int[] list, int k) {
        int maxSum = Arrays.stream(list).limit(k).sum();
        int sum = maxSum;
        for (int i = k; i < list.length; i++) {
            sum = sum - list[i - k] + list[i];
            maxSum = Math.max(maxSum, sum);
        }
        System.out.println(maxSum);
    }


    private void longestWithKDistinctsCharacters(String s, int k) {
        int left = 0;
        int right = 0;
        int bestLeft = 0, bestRight = 0;
        Map<Character, Integer> distincts = new HashMap<>();
        while (right < s.length()) {
            Character c = s.charAt(right);
            distincts.put(c, distincts.getOrDefault(c, 0) + 1);

            while (distincts.size() > k) {
                distincts.put(s.charAt(left), distincts.get(s.charAt(left)) - 1);
                if (distincts.get(s.charAt(left)) == 0) {
                    distincts.remove(s.charAt(left));
                }
                left++;
            }
            if (bestRight - bestLeft < right - left) {
                bestRight = right;
                bestLeft = left;
            }

            right++;
        }

    }

    private void longestWithoutRepeating(String s) {
        int left = 0;
        int right = 0;
        int bestLeft = 0;
        int bestRight = 0;
        //s = "abcabcbb"
        Map<Character, Integer> map = new HashMap<>();
        while (right < s.length()) {
            Character c = s.charAt(right);
            if (!map.containsKey(c)) {
                right++;
            } else {
                if (bestRight - bestLeft < right - left) {
                    bestRight = right;
                    bestLeft = left;
                }
                left = map.get(s.charAt(right)) + 1;
            }
            map.put(c, right);

        }
        String result = s.substring(bestLeft, bestRight + 1);
        System.out.println("Longest substring without repeating = " + result);
        System.out.println("Length = " + (bestRight - bestLeft + 1));
    }


    private void longestWithoutRepeating2(String s) {
        int left = 0;
        int right = 0;
        int bestLeft = 0;
        int bestRight = 0;
        //s = "abcabcbb"
        Map<Character, Integer> map = new HashMap<>();
        while (right < s.length()) {
            Character c = s.charAt(right);
            map.put(c, map.getOrDefault(c, 0) + 1);
            while (map.get(c) > 1) {
                map.put(s.charAt(left), map.get(s.charAt(left)) - 1);
                left++;
            }
            if (bestRight - bestLeft < right - left) {
                bestRight = right;
                bestLeft = left;
            }
            right++;
        }
        String result = s.substring(bestLeft, bestRight + 1);
        System.out.println("Longest substring without repeating = " + result);
        System.out.println("Length = " + (bestRight - bestLeft + 1));
    }

    private void findMaximumAverageSizeK(int[] list, int k) {
        int maxSum = Arrays.stream(list).limit(k).sum();
        for (int i = k; i < list.length; i++) {
            int sum = maxSum + list[i] - list[i - k];
            if (sum > maxSum) {
                maxSum = sum;
            }
        }
        double maxAverage = (double) maxSum / k; // division flottante
        System.out.println("Maximum average of " + k + " consecutive elements = " + maxAverage);
    }

}
