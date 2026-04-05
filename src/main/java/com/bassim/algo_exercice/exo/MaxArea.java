package com.bassim.algo_exercice.exo;

public class MaxArea {
    public static void main(String[] args) {

    }
    public static int maxArea(int[] height) {
        int left=0;
        int right =height.length-1;

        int max=0;
        while (left<right){
            int current = (right-left)* Math.min(height[left],height[right]);

            max = Math.max(max,current);

            if(height[left]<height[right]){
                left++;
            } else {
                right--;
            }
        }
        return max;
    }
}
