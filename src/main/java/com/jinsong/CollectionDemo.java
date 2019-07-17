package com.jinsong;

import scala.Int;

import java.util.*;

public class CollectionDemo {


    public static void main(String[] args) {


        //Collection

        //List
        ArrayList<String> al = new ArrayList<>();
        LinkedList<String> ll = new LinkedList<>();
        Vector<String> v = new Vector<>();

        //Set
        HashSet<String> hs = new HashSet<>();
        LinkedHashSet<String> lhs = new LinkedHashSet<>();
        TreeSet<String> ts = new TreeSet<>();

        //queue
        PriorityQueue<String> pq = new PriorityQueue<>();

        //Map
        Hashtable<String, Int> ht = new Hashtable<>();
        LinkedHashMap<String, Int> lhm = new LinkedHashMap<>();
        HashMap<String, Int> hm = new HashMap<>();
        TreeMap<String, Int> tm = new TreeMap<>();


        al.add("a");
        al.add("b");
        al.add("c");
        for (String a : al){
            System.out.println(a);
        }
        System.out.println(Weekday.MON.name());


    }


}
enum Weekday {
    SUN, MON, TUE, WED, THU, FRI, SAT;
}
