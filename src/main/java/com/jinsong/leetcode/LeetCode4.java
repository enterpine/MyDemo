package com.jinsong.leetcode;

public class LeetCode4 {
    static int[] mergeSort(int[] nums1, int[] nums2){
        int len1 = nums1.length;
        int len2 = nums2.length;
        int len = len1 + len2;
        int[] result = new int[len];

        int i = 0;
        int j = 0;
        int r = 0;

        while(i<len1 && j<len2){
            if(nums1[i] > nums2[j]){
                result[r] = nums2[j];
                j++;
                r++;
            }else if (nums1[i] <= nums2[j]){
                result[r] = nums1[i];
                i++;
                r++;
            }
        }
        while(j<len2){
            result[r] = nums2[j];
            j++;
            r++;
        }
        while(i<len1){
            result[r] = nums1[i];
            r++;
            i++;
        }


        return result;
    }
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int []  s = mergeSort(nums1,nums2);
        if(s.length%2 == 1){
            return s[(s.length+1)/2-1];
        }else{
            double t=  (s[(s.length/2)-1]+s[s.length/2]);
            double a = t/2;
            return a ;
        }
    }

    public static void main(String[] args) {


        int[] nums1 = new int[]{1,2};
        int[] nums2 = new int[]{3,4};
        double a  = findMedianSortedArrays(nums1,nums2);
        System.out.println(a);



    }

}
