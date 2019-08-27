package com.jinsong.leetcode;
public class LeetCode152 {

    public static int maxProduct1(int[] nums) {
        //问题是，如果之前有负数且绝对值较大的值，那么再乘负数可以更大，这种算法不会涉及此情况

        int nlen = nums.length;

        int maxa = Integer.MIN_VALUE;

        int maxp = nums[0];//记录当前子串计算的最大值
        for(int i=1;i<nlen;i++){
            //num[i]是否加入到前面的计算结果
            int tmp1 = maxp * nums[i];
            //如果乘num[i]后更大，则maxp为乘num[i]后的值；否则maxp为nums[i],重新开启一个子字符串的连续计算
            maxp = Math.max(tmp1,nums[i]);

            maxa = Math.max(maxp,maxa);//记录历史上出现的最大值
        }


        return maxa;
    }

    public static int maxProduct2(int[] nums) {

        int nlen = nums.length;

        int maxa = Integer.MIN_VALUE;


        int maxp = 1;//记录当前子串计算的最大值
        int minp = 1;

        for(int i=0;i<nlen;i++){

            //num[i]是否加入到前面的计算结果
            int tmp1 = maxp * nums[i];
            int tmp2 = minp * nums[i];//这个乘出来可能成为最大值

            //
            int tmp3 = Math.max(tmp1,tmp2);
            int tmp4 = Math.min(tmp1,tmp2);

            //如果乘num[i]后更大，则maxp为乘num[i]后的值；否则maxp为nums[i],重新开启一个子字符串的连续计算
            maxp = Math.max(tmp3,nums[i]);
            minp = Math.min(tmp4,nums[i]);
            System.out.print(maxp);
            System.out.println(minp);

            maxa = Math.max(maxa,maxp);//记录历史上出现的最大值
        }


        return maxa;
    }


    public static void main(String[] args){
        int[] nums = {-2,2,-3};
        int[] nums2 = {-2,2,-3,-4};
        int a = maxProduct1(nums);
        int a2 = maxProduct2(nums2);
        System.out.println(a);
        System.out.println(a2);
    }

}
