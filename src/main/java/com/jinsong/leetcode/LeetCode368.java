package com.jinsong.leetcode;

import java.util.LinkedList;
import java.util.List;

public class LeetCode368 {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        
        int len = nums.length;

        int maxLen;
        int[] dpMax = new int[len];
        int[] dpLen = new int[len];


        
        dpMax[0] = nums[0];
        dpLen[0] = nums[0];

        maxLen = 1;

        List<Integer> result = new LinkedList<Integer>();
        for(int i = 1 ; i<len ;i++){
            int num_a = nums[i];
            int num_b = dpMax[i-1];

            int flag = 0; //falg == 0 -> 当前数不可以加入；；flag == 1 当前数可以加入
            if(num_b >= num_a && num_b%num_a == 0){
                flag = 1;
            }else if(num_b < num_a && num_a%num_b == 0){
                flag = 1;
            }

            if(flag == 1){
                dpMax[i] = Math.max(num_a,num_b);


                result.add(num_a);
            }else{
                
            }

        }

        return result;
    }
    public static void main(String[] args) {
        LeetCode368 leetCode368 = new LeetCode368();
        int[] nums = new int[]{1,2,3,4};
        List<Integer> result = leetCode368.largestDivisibleSubset(nums);
        result.forEach(a->System.out.println(a));
    }
}
