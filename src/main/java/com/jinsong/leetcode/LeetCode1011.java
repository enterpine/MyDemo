package com.jinsong.leetcode;

public class LeetCode1011 {

    public int shipWithinDays(int[] weights, int D) {
        int load = 0 ;
        int total_weight = 0;
        for(int i = 0 ; i< weights.length;i++){
            total_weight += weights[i];
        }
        load = total_weight;

        int[] days_weight = new int[D];//每天运送的重量
        int[] indexes =new int[D-1]; //分天用的角标

        int init_weight = 0;//初始前D-1天的总重

        for(int i=0;i<D-1;i++){
            indexes[i] = i;
            days_weight[i] = weights[i];
            init_weight+=weights[i];
        }
        days_weight[D-1] = total_weight - init_weight;//第D天的总重

        //记录所需最小的重量load，为D中的最小值
        //找到这D天中，最重的那一天，第j天
        //移动第j天的光标向右，如果无法移动或者，移动后load增加，则返回load为答案







        return 0 ;
    }

    public static void main(String[] args) {
        LeetCode1011 leetCode1011 = new LeetCode1011();
        int[] pack = new int[]{1,2,3,4,5,6,7,8,9,10};
        int days = 5;
        System.out.println(leetCode1011.shipWithinDays(pack,days));

    }
}
