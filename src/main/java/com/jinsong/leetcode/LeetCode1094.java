package com.jinsong.leetcode;


import java.util.Hashtable;
import java.util.Map;

public class LeetCode1094 {
    public static boolean carPooling(int[][] trips, int capacity) {
        boolean r = true;
        java.util.Map<Integer,Integer> ht = new java.util.Hashtable<>();

        for(int i = 0 ;i<trips.length;i++){

            int passengers = trips[i][0];

            if(passengers>capacity){return false;}

            else {
                for (int j = trips[i][1]; j < trips[i][2]; j++) {
                    if (!ht.containsKey(j)) {
                        ht.put(j, passengers);
                    }else{
                        int tmp = ht.get(j) + passengers;
                        ht.put(j,tmp);
                        if(tmp>capacity){return false;}
                    }


                }
            }
        }

        return r;
    }
    public static void main(String[] args){
        int[][] trips = {{2,1,5},{3,5,7}};

        int[][] trips2 ={{7,5,7},{8,1,9},{10,2,6},{4,7,8},{2,1,3}};

                
        int capacity = 3;
        int capacity2 = 26;
        boolean a = carPooling(trips,capacity);
        boolean b = carPooling(trips2,capacity2);
        System.out.println(a);
        System.out.println(b);
    }
}
