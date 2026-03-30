package com.bassim.algo_exercice.fibo;

import java.util.*;

public class ThreeSums {

    public static void main(String[] args) {
        int[] list = new int[]{-1, -1, 0, 1, 1, 2};
        threeSum(list).forEach(System.out::println);
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        Arrays.sort(nums); // Tri préalable pour faciliter la recherche

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length - 2; i++) {
            map.put(nums[i], i);
            int left = i + 1;
            int right = left + 1;
            while (right < nums.length) {
                if (map.containsKey(Math.negateExact(nums[left] + nums[right]))) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                }
                right++;
            }
        }
        return new ArrayList<>(result);
    }

    public static List<List<Integer>> threeSum2(int[] nums) {
        //-1, -1, 0, 1, 1, 3
        Set<List<Integer>> result = new HashSet<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // sauter doublons pour i

            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                } else if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }

        }
        return new ArrayList<>(result);
    }

    public List<List<Integer>> threeSum3(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i -1]) continue;// si on fait i+1 on ne va pas traiter tout les tripletavec -1

            int fixed = nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = fixed + nums[left] + nums[right];
                if (sum == 0) {
                    while (left<right && nums[left]==nums[left+1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    res.add(List.of(fixed, left, right));
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return new ArrayList<>(res);
    }
}
