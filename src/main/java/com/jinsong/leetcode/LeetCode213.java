package com.jinsong.leetcode;

public class LeetCode213 {

    public int rob(int[] nums) {

        if(nums.length == 0){
            return 0;
        } else if(nums.length == 1){
            return nums[0];
        } else if(nums.length == 2){
            return Math.max(nums[0],nums[1]);
        }

        int max = 0;

        int[] dpIncludeFirst = new int[nums.length];
        dpIncludeFirst[0] = nums[0];
        dpIncludeFirst[1] = Math.max(nums[1],nums[0]);

        int[] dpExcludeFirst = new int[nums.length];
        dpExcludeFirst[0] = 0;
        dpExcludeFirst[1] = nums[1];

        for(int i = 2;i<nums.length;i++){
            int steal = nums[i] + dpIncludeFirst[i - 2];
            int unsteal = dpIncludeFirst[i - 1];

            int stealEx = nums[i] + dpExcludeFirst[i-2];
            int unstealEx = dpExcludeFirst[i-1];

            if(i<nums.length-1) {

                dpIncludeFirst[i] = Math.max(steal, unsteal);
                dpExcludeFirst[i] = Math.max(stealEx, unstealEx);

                max = Math.max(dpIncludeFirst[i], max);

            }else {
                dpIncludeFirst[i] = unsteal;
                dpExcludeFirst[i] = Math.max(stealEx, unstealEx);

                max = Math.max(Math.max(dpIncludeFirst[i], max),dpExcludeFirst[i]);
            }

        }
        return max;
    }

    public static void main(String[] args) {

        LeetCode213 leetCode213 = new LeetCode213();


        int[] input = new int[]{4,1,2,7,5,3,1};
        int result = leetCode213.rob(input);

        System.out.println(result);
    }
}
