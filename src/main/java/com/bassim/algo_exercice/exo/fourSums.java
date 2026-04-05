package com.bassim.algo_exercice.exo;

import java.util.*;

public class fourSums {

    public static void main(String[] args) {
        fourSum(new int[]{1000000000, 1000000000, 1000000000, 1000000000}, -294967296)
                .forEach(System.out::println);
        //      -2,-1,0,0,1,2
        //index  0  1 2 3 4 5

    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i <= nums.length - 4; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j <= nums.length - 3; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum > Integer.MAX_VALUE || sum < Integer.MIN_VALUE) {
                        // On ignore ce quadruplet et on déplace les pointeurs
                        if (sum < target) left++;
                        else right--;
                        continue;
                    }
                    if (sum == target) {
                        result.add(List.of(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        left++;
                        right--;
                    } else if (sum < target) left++;
                    else right--;

                }
            }
        }
        return result;
    }
}
