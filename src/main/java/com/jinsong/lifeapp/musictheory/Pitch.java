package com.jinsong.lifeapp.musictheory;

import java.util.ArrayList;
import java.util.HashMap;

public class Pitch {

    String currentPitch ="C";
    private int  currentIndex = 0;
    Intervals intervals = null;

    ArrayList<String> pitches = new ArrayList<String>();
    HashMap<String,Integer> index = new HashMap<>();
    HashMap<String,Integer> levels = new HashMap<>();

    public Pitch() {
        intervals = new Intervals();
        pitches.add("C");
        pitches.add("C#/Db");
        pitches.add("D");
        pitches.add("D#/Eb");
        pitches.add("E/Fb");
        pitches.add("F/E#");
        pitches.add("F#/Gb");
        pitches.add("G");
        pitches.add("G#/Ab");
        pitches.add("A");
        pitches.add("A#/Bb");
        pitches.add("B");

        index.put("C",0);
        index.put("C#/Db",1);
        index.put("C#",1);
        index.put("Db",1);
        index.put("D",2);
        index.put("D#/Eb",3);
        index.put("D#",3);
        index.put("Eb",3);
        index.put("E/Fb",4);
        index.put("E",4);
        index.put("Fb",4);
        index.put("F/E#",5);
        index.put("F",5);
        index.put("E#",5);
        index.put("F#/Gb",6);
        index.put("Gb",6);
        index.put("F#",6);
        index.put("G",7);
        index.put("G#/Ab",8);
        index.put("Ab",8);
        index.put("G#",8);
        index.put("A",9);
        index.put("A#/Bb",10);
        index.put("Bb",10);
        index.put("A#",10);
        index.put("B",11);

        levels.put("C",1);
        levels.put("C#",1);
        levels.put("Db",2);
        levels.put("D",2);
        levels.put("D#",2);
        levels.put("Eb",3);
        levels.put("E",3);
        levels.put("E#",3);
        levels.put("Fb",4);
        levels.put("F",4);
        levels.put("F#",4);
        levels.put("Gb",5);
        levels.put("G",5);
        levels.put("G#",5);
        levels.put("Ab",6);
        levels.put("A",6);
        levels.put("A#",6);
        levels.put("Bb",7);
        levels.put("B",7);




    }

    public void setCurrentPitch(String currentPitch) {
        this.currentPitch = currentPitch;
        this.currentIndex = this.index.get(currentPitch);
    }

    public String getCurrentPitch() {
        return currentPitch;
    }


    public void addInterval(String str){

        int numsOfHalf = intervals.getNumsOfHalf(str);
        int numsOfLevel = intervals.getNumsOfLevel(str);
        int preLevel = levels.get(this.currentPitch);

        if(numsOfHalf == -1 ){
            System.out.println("invalid interval to be added");
        }else {

            if (currentIndex + numsOfHalf <= 11) {
                this.currentIndex = currentIndex + numsOfHalf;
            } else {
                this.currentIndex = currentIndex + numsOfHalf - 12;
            }
            this.currentPitch = pitches.get(this.currentIndex);

            if(pitches.get(this.currentIndex).contains("/")){
              String p1 = pitches.get(this.currentIndex).split("/")[0];
              String p2 = pitches.get(this.currentIndex).split("/")[1];
              if(((levels.get(p1) - preLevel)<0?(levels.get(p1)+7 - preLevel):(levels.get(p1) - preLevel)) == numsOfLevel){
                  this.currentPitch = p1;
              }else{
                  this.currentPitch = p2;
              }

            }
        }

    }


}
