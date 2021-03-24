package com.jinsong.leetcode;

import scala.Int;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class LeetCode3_Quick {
//    public static HashMap<Character, Integer>   ResetStatus(){
//
//        HashMap<Character, Integer> hashMap = new HashMap<>();
//        for(char a='a'; a<='z';a++){
//            hashMap.put(a,0);
//        }
//        for(char a='A'; a<='Z';a++){
//            hashMap.put(a,0);
//        }
//        for(char a='0'; a<='9';a++){
//            hashMap.put(a,0);
//        }
//        hashMap.put('_',0);
//        hashMap.put(' ',0);
//        return hashMap;
//
//    }
    public static int lengthOfLongestSubstring(String s) {

        int maxLen = 0;

        int len = s.length();

        for(int i = 0 ;i<len;i++){
            HashMap<Character, Integer> hashMap = new HashMap<>();
            char head_tmp = s.charAt(i);
            int len_tmp = 1;
            hashMap.put(head_tmp,1);

            for(int j = i+1;j<len;j++){

                char tail_tmp = s.charAt(j);


                if(!hashMap.containsKey(tail_tmp)){
                    len_tmp++;
                }else{
                    j=len;
                }
                hashMap.put(tail_tmp,1);

            }
            maxLen = len_tmp>maxLen?len_tmp:maxLen;
        }

        return maxLen;
    }
    public static void main(String[] args){
        String str=" ";
        //System.out.println(str.charAt(0));
        int ans = lengthOfLongestSubstring(str);
        System.out.println(ans);
    }
}
