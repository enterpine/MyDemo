package com.jinsong.leetcode;

import static java.lang.Math.max;

public class LeetCode1143 {

    public static  int dp(String text1,String text2,int maxlen){


        System.out.println("Text1:"+text1+" Text2:"+text2+" maxlen:"+maxlen);
        if(text1.charAt(0) == text2.charAt(0)){

            String ttext1 = text1.substring(1,text1.length());
            String ttext2= text2.substring(1,text2.length());
            maxlen = maxlen + 1;

            if(ttext1.length()>0 && ttext2.length()>0) {
                return dp(ttext1, ttext2, maxlen);
            }


        }

        else{

            if(text1.length()>0 && text2.length()>0) {

                String ttext1 = text1.substring( 1, text1.length());
                String ttext2 = text2.substring(0, text2.length());


                String ttext11 = text1.substring(0, text1.length());
                String ttext22 = text2.substring(1, text2.length());


                if(ttext1.length() == 0  && ttext22.length()>0){
                    return dp(ttext11, ttext22, maxlen);
                }
                if(ttext22.length() == 0 && ttext1.length() > 0 ){
                    return dp(ttext1, ttext2, maxlen);
                }
                if(ttext22.length() > 0 && ttext1.length() > 0) {
                    return max(dp(ttext11, ttext22, maxlen), dp(ttext1, ttext2, maxlen));
                }
            }
        }

        return maxlen;
    }

    public static  int longestCommonSubsequence(String text1, String text2) {
        int a = dp(text1,text2,0);
        return a;
    }

    public static void main(String[] args){

//        String s1 = "abcde";
////        String s2 = "ace";

        String s1 = "pmjghexybyrgzczy";
        String s2 ="hafcdqbgncrcbihkd";
        int a = longestCommonSubsequence(s1,s2);
        System.out.println(a);
    }

}
