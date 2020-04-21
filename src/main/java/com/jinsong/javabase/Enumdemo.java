package com.jinsong.javabase;

import scala.Int;

import java.util.*;

public class Enumdemo {
    public static void main(String[] args) {
        String a = Weekday.SUN.dayname;
    }
}

enum Weekday {
    SUN(7,"sunday"), MON(1,"monday");
    final int weeknum;
    final String dayname;
    Weekday (int day,String dayname){
        this.weeknum=day;
        this.dayname = dayname;
    }
}
