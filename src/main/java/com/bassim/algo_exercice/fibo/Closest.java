package com.bassim.algo_exercice.fibo;

import java.util.*;

public class Closest {

    public static void main(String[] args) {

        int[] list = new int[]{-1, -1, 0, 1, 1, 2};
        System.out.println(closest(list, 2));
        //-1, -1, -1 ,0, 1, 2
        //2, -1, -1 ,0,1,3
        //-1,-1,0,1,2,3
    }

    public static int closest(int[] nums, int target) {
        Arrays.sort(nums);
        int closest = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < nums.length - 1; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == target) {
                    return sum;
                }

                if (Math.abs(sum - target) < Math.abs(closest - target)) {
                    closest = sum;
                }

                //classique de la technique two-pointer après tri.
                if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return closest;
    }
}
