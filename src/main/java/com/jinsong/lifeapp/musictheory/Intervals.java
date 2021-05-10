package com.jinsong.lifeapp.musictheory;

import java.util.HashMap;
import java.util.Map;

public class Intervals {

    //min2 maj2 min3 maj3 per4 aug4 per5
    Map<String,Integer> intervalsOfhalf = new HashMap<>();
    Map<String,Integer> intervalsOfLevel = new HashMap<>();

    public Intervals(){
        intervalsOfhalf.put("min2",1);
        intervalsOfhalf.put("maj2",2);
        intervalsOfhalf.put("aug2",3);
        intervalsOfhalf.put("min3",3);
        intervalsOfhalf.put("maj3",4);
        intervalsOfhalf.put("per4",5);
        intervalsOfhalf.put("aug4",6);
        intervalsOfhalf.put("per5",7);

        intervalsOfLevel.put("min2",2);
        intervalsOfLevel.put("maj2",2);
        intervalsOfLevel.put("aug2",2);
        intervalsOfLevel.put("min3",3);
        intervalsOfLevel.put("maj3",3);
        intervalsOfLevel.put("per4",4);
        intervalsOfLevel.put("aug4",4);
        intervalsOfLevel.put("per5",5);
    }

    public int getNumsOfHalf(String str){
        return intervalsOfhalf.getOrDefault(str,-1);
    }

    public int getNumsOfLevel(String str){
        return intervalsOfLevel.getOrDefault(str,-1);
    }



}
