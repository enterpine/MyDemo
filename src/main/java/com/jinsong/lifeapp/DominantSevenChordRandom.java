package com.jinsong.lifeapp;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DominantSevenChordRandom {
    public static void main(String[] args) throws  Exception {

        List<String> chords = new ArrayList<>();

        chords.add("B");
        chords.add("C");
        chords.add("D");

        chords.add("G");

        Integer counter = 1;

        while(1 == 1){
            int a = (int)(Math.random()*100)%4;
            String root = chords.get(a);

            int r1 = (int)(Math.random()*100)%2;
            int r2 = (int)(Math.random()*100)%2;

            if(r1 == 0 && r2 ==0){
                System.out.print(root);
            }else if(r1 == 1 && r2 == 1){
                System.out.print(root+"m");
            }else if(r1==0&& r2==1){
                System.out.print(root+"7");
            }else{
                System.out.print(root+"m7");
            }
            System.out.println("\t\t\t\t"+counter);
            Thread.sleep(4000);
            counter++;
        }
    }
}