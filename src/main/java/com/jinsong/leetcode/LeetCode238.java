package com.jinsong.leetcode;

public class LeetCode238 {
    //除自身以外数组的乘积
    public static int[] productExceptSelf(int[] nums) {

        int nlen = nums.length;

        int[] result = new int[nlen];

        int[] a1 = new int[nlen];
        int[] a2 = new int[nlen];
        a1[0] = 1;
        a2[nlen-1] = 1;

        for(int i = 0 ; i<nlen ;i++){
            if(i<nlen-1) {
                a1[i + 1] = a1[i] * nums[i];
            }

            int j = nlen - 1 - i;
            if(j-1>=0) {
                a2[j - 1] = a2[j] * nums[j];
            }

        }
        for(int i=0 ;i<nlen;i++){
            result[i] = a1[i]*a2[i];
        }

        return result;
    }
    public static void main(String[] args){
        int[] a = {1,2,3,4};
        int[] b = productExceptSelf(a);
        for(int i=0;i<b.length;i++) {
            System.out.print(b[i]);
        }
    }

}
