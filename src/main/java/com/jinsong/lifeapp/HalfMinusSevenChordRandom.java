package com.jinsong.lifeapp;
import java.util.ArrayList;
import java.util.List;

public class HalfMinusSevenChordRandom {
    public static void main(String[] args) throws  Exception {

        List<String> chords = new ArrayList<>();

        chords.add("A");
        chords.add("B");
        chords.add("C");
        chords.add("D");
        chords.add("E");
        chords.add("F");
        chords.add("G");

        Integer counter = 1;

        while(1 == 1){
            int a = (int)(Math.random()*100)%7;
            String root = chords.get(a);
           System.out.print(root+"m7-5");
            System.out.println("\t\t\t\t"+counter);
            Thread.sleep(4000);
            counter++;
        }
    }
}