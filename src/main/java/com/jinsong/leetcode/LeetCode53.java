package com.jinsong.leetcode;



public class LeetCode53 {
    public static int maxSubArray(int[] nums) {
        int result = 0;
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        result = dp[0];
        for(int i=1 ; i<nums.length;i++){
            dp[i] = java.lang.Math.max(nums[i],dp[i-1]+nums[i]);
            result = dp[i]>result?dp[i]:result;
        }
        return result;
    }
    public static void main(String[] args){
        int[] nums = {-1};
        System.out.println(maxSubArray(nums));
    }
}
