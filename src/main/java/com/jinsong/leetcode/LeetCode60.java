package com.jinsong.leetcode;

import java.util.LinkedList;

public class LeetCode60 {

    public static String getPermutation(int n, int k) {

        long[] factorial = new long[n-1];
        long result_tmp = 1;
        for(int i =0;i<n-1;i++){
            int element = i+1;
            result_tmp = result_tmp * element;
            factorial[i] = result_tmp;
        }

        int[]  midResult = new int[n];
        midResult[n-1]=1;
        getNHead(factorial,n,k,1,midResult,0);

        return convertToResult(midResult,n);
    }

    public static void main(String[] args) {
        int n = 4;
        int k = 9;
        System.out.println(getPermutation(n,k));
    }

    static String convertToResult(int[] midResult,int n){
        StringBuffer stringBuffer = new StringBuffer();
        LinkedList<Integer> linkedList = new LinkedList<>();

        for(int i=1;i<=n;i++){
            linkedList.add(i);
        }

        for(int i=0;i<n;i++){
                Integer a = linkedList.get(midResult[i] - 1);//第i小的数
                linkedList.remove(midResult[i] - 1);
                stringBuffer.append(a.toString());
        }
        return  stringBuffer.toString();

    }
    static void getNHead(long[] factorial,int n,int k,int i,int[] midResult,long tempSum){

        if(n-1-i<0){
            return;
        }
        long factorialN = factorial[n-1-i];//n-i的阶乘
        int mRange = n-i+1;
        long tempSumN_pre= tempSum;

        for(int j = mRange;j>=1;j--){
            tempSum = tempSumN_pre+(j-1)*factorialN;
            long temp = tempSum+1;
            if(temp <= k ){
                midResult[i-1] = j;
                break;
            }
        }
        i++;
        getNHead(factorial,n,k,i,midResult,tempSum);
    }
}
