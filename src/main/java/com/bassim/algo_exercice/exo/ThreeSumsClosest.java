package com.bassim.algo_exercice.exo;

import java.util.Arrays;

public class ThreeSumsClosest {

    public static void main(String[] args) {
        int[] list = new int[]{-1, 1, 1, 0, 2, -4};
    }

    public int threeSumClosest(int[] nums, int target) {

        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[2];
        int diff = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int firstNumb = nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = firstNumb + nums[left] + nums[right];
                int abs = Math.abs(sum - target);
                if (abs < diff) {
                    diff = abs;
                    res = sum;
                }

                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    return sum;
                }
            }
        }
        return res;
    }
}
