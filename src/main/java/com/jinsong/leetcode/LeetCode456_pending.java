package com.jinsong.leetcode;

import scala.Int;

import java.util.Map;
import java.util.TreeMap;

public class LeetCode456_pending {

    public static boolean find132pattern_nlogn(int[] nums) {
        if(nums.length<3){return false;}

        int[] min_list =new int [nums.length];
        Map<Integer,Integer> treeMap = new TreeMap<>();
        for(int i = 1;i<nums.length;i++){
            min_list[i] = Integer.MAX_VALUE;
            treeMap.put(nums[i], i);
        }
        min_list[0] = nums[0];
        for(int i = 1;i<nums.length;i++){
            min_list[i]=Math.min(nums[i-1],min_list[i-1]);
        }
        for(int i=0;i<min_list.length;i++){
            System.out.print(min_list[i]);
        }

        for(int j=1;j<nums.length;j++){
            if(nums[j]>min_list[j]){
                for(int k = j+1;k<nums.length;k++){
                    if(nums[k]>min_list[j] && nums[k]<nums[j]){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean find132pattern(int[] nums) {
        if(nums.length<3){return false;}

        int[] min_list =new int [nums.length];
        for(int i = 0;i<nums.length;i++){
            min_list[i] = Integer.MAX_VALUE;
        }
        min_list[0] = nums[0];
        for(int i = 1;i<nums.length;i++){
            min_list[i]=Math.min(nums[i-1],min_list[i-1]);
        }
        for(int i=0;i<min_list.length;i++){
            System.out.print(min_list[i]);
        }

        for(int j=1;j<nums.length;j++){
            if(nums[j]>min_list[j]){
                for(int k = j+1;k<nums.length;k++){
                    if(nums[k]>min_list[j] && nums[k]<nums[j]){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums =new int[]{3,5,0,3,4};


        TreeMap<Integer,Integer> treeMap = new TreeMap<>();
        for(int i =0;i<nums.length;i++){
            treeMap.put(nums[i],i);
        }
        for(Map.Entry<Integer,Integer> entry :treeMap.entrySet()){
            System.out.println(entry.getKey().toString()+"->"+entry.getValue().toString());
        }
        System.out.println(treeMap.ceilingKey(3));


        //System.out.println(find132pattern(nums));
    }
}
