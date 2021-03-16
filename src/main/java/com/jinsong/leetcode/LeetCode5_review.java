package com.jinsong.leetcode;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class LeetCode5_review {

    public static  String longestPalindrome(String s) {

        char[] s1 = s.toCharArray();
        char[] s2 = new char[s.length()];

        int[] dp = new int[s1.length];

        int[] eindex =  new int[s1.length];
        int[] sindex =  new int[s1.length];

        int maxSindex = 0;
        int maxEindex = 0;

        int maxlen = 0;

        for(int i = s1.length - 1 ;i>=0;i--){
            s2[s1.length-1-i] = s1[i];
            dp[i] = 0;
            eindex[i] = -1;
            sindex[i] = -1;
        }

        for(int i = 0;i<s1.length;i++){
            for(int j = 0;j<s2.length;j++){
                if(s1[i] == s2[j]){
                    if(i-1<0 || j-1<0){
                        dp[i] = 1;
                        sindex[i]=i;
                        eindex[i]=i;
                        maxlen = dp[i]>maxlen?dp[i]:maxlen;

                    }
                    else if(s1[i-1] == s2[j-1]){
                        dp[i] = dp[i-1]+1;
                        sindex[i]=sindex[i-1];
                        eindex[i]=i;
                        if( dp[i]>maxlen) {
                            maxlen =  dp[i] ;
                            maxSindex = sindex[i];
                            maxEindex = eindex[i];
                        }
                    }else{
                        dp[i] = 1;
                    }
                }
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        for(int i =maxSindex;i<=maxEindex;i++){
            stringBuffer.append(s1[i]);
        }
        System.out.println(maxlen);
        return stringBuffer.toString();

    }

    public static void  main(String[] args){
        String input ="babad";

        System.out.println(longestPalindrome(input));
    }

}
