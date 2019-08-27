package com.jinsong.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class LeetCode3 {
    public static int lengthOfLongestSubstring(String s) {
        int nlen = s.length();
        int maxlen = 0;
        Queue<Character> queue = new LinkedList<>();
        for(int i=0;i<nlen;i++){
            int si = 0;
            Character tmp = s.charAt(i);

            if(!queue.contains(tmp)){
                queue.add(tmp);
                si = queue.size();

            }else{
                while(queue.poll()!=tmp){}
                queue.add(tmp);
                si = queue.size();
            }
            maxlen = Math.max(maxlen,si);
        }
        return maxlen;
    }
    public static void main(String[] args){
        String str="ynyo";
        int ans = lengthOfLongestSubstring(str);
        System.out.println(ans);
    }
}
