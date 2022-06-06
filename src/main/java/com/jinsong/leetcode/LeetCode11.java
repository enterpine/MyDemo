package com.jinsong.leetcode;

public class LeetCode11 {

    public static int maxArea(int[] height) {
        int len = height.length;
        int leftIndex = 0;
        int rightIndex = len - 1;

        int capcity = (rightIndex - leftIndex) * Math.min(height[leftIndex],height[rightIndex]);

        while(leftIndex < rightIndex) {
            int leftIndex_t = leftIndex + 1;
            int capcity_l = (rightIndex - leftIndex_t) * Math.min(height[leftIndex_t], height[rightIndex]);

            int rightIndex_t = rightIndex - 1;
            int capcity_r = (rightIndex_t - leftIndex_t) * Math.min(height[leftIndex_t], height[rightIndex_t]);

            if (capcity_l > capcity_r) {
                leftIndex++;
            } else if (capcity_l < capcity_r) {
                rightIndex--;
            }
        }

        return 0 ;

    }

    public static void main(String[] args) {
        int []  a = new int[] {1,2,3,4,5,6};
        System.out.println(maxArea(a));
    }
}
