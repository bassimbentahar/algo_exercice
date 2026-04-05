package com.bassim.algo_exercice.exo.singleton.thread;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSumsTarget {

    public static void main(String[] args) {
        int[] sums = new int[]{2, 7, 11, 15};
        Arrays.stream(twoSum(sums, 9)).forEach(System.out::println);

    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {

            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);

        }
        return new int[]{};
    }


}
