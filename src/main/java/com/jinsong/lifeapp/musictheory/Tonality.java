package com.jinsong.lifeapp.musictheory;

import scala.math.Ordering;

import java.util.ArrayList;
import java.util.List;

public class Tonality {

    Pitch pitch = new Pitch();

    List<String> list = new ArrayList<>();


    public Tonality(){

    }

    public List<String> getTonality(String[] mode,String start){

        list.clear();
        StringBuffer stringBuffer = new StringBuffer();

        pitch.setCurrentPitch(start);
        list.add(pitch.getCurrentPitch());

        for(String str :mode){
            pitch.addInterval(str);
            list.add(pitch.getCurrentPitch());
        }

        return list;
    }

    public static void main(String[] args) {

        Tonality tonality = new Tonality();

        tonality.getTonality(ModesCollect.BulesMinor,"C").forEach(x->System.out.print(x+"\t"));
        System.out.println();
        tonality.getTonality(ModesCollect.BulesMajor,"C").forEach(x->System.out.print(x+"\t"));

    }

}
