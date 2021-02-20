package com.jinsong.interview;

import javax.security.sasl.SaslServer;
import java.util.ArrayList;

public class PoisionBottle {

    static String leftPadString(String str,int len,char c){



        int strlen = str.length();

        if(strlen>=len){
            return str;
        }

        int padLen = len - strlen;

        StringBuffer sb = new StringBuffer();
        for(int i = 0 ;i<padLen;i++){
            sb.append(c);
        }
        sb.append(str);
        return sb.toString();
    }

    public static void main(String[] args) {

        int numOfBottle = 1000;

        ArrayList<String>[] group = new ArrayList[10];

        for(int i = 0 ;i<10;i++){
            group[i] = new ArrayList<>();
        }


        for(int i = 1 ;i<=numOfBottle;i++){

            String bottleByte = leftPadString(Integer.toBinaryString(i),10,'0');
            //System.out.println(bottleByte);
            for(int j = 0;j<10;j++){

                if(bottleByte.charAt(j) == '0'){
                    group[j].add(bottleByte);
                }
            }

        }

//        for(Integer i = 0 ;i<10;i++){
//            final Integer a = i;
//            group[i].forEach((s) -> {System.out.println("group "+a.toString()+"-> "+s);});
//        }

        for(Integer i = 0 ;i<10;i++){
            System.out.println("group "+i.toString()+"-> size:"+group[i].size());
            group[i].forEach((s) -> {System.out.print(Integer.valueOf(s,2)+",");});
            System.out.println("");
            System.out.println("===");
        }


    }
}
