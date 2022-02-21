package com.jinsong.leetcode;

public class LeetCode1143_ac {
    public static  int longestCommonSubsequence(String text1, String text2) {

        int[][] dp = new int[text1.length()+1][text2.length()+1];

        //表示text1[0~i] text2[0~j]的最大子序列长度

        for(int i = 0 ;i<text1.length()+1;i++){
            dp[i][0]=0;

        }
        for(int i = 0 ;i<text2.length()+1;i++){
            dp[0][i]=0;

        }


        for(int i=0;i<text1.length();i++){

            for(int j=0;j<text2.length();j++){

                if(text1.charAt(i)==text2.charAt(j)){
                    dp[i+1][j+1]=dp[i][j]+1;
                }
                else{
                    dp[i+1][j+1]=java.lang.Math.max(dp[i][j+1],dp[i+1][j]);
                }

            }

        }
        return dp[text1.length()][text2.length()];

    }

    public static void main(String[] args){

        String s1 = "pmjghexybyrgzczy";
        String s2 = "hafcdqbgncrcbihkd";
        int a = longestCommonSubsequence(s1,s2);
        System.out.println(a);

    }
}
