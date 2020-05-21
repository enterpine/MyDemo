package com.jinsong.javabase;

import java.util.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

public class CollectionDemo {

    private static void listdemo(){
        //get(index),set(index,value),listIterator()
        List<String> al = new ArrayList<>();
        System.out.println(al.size());
        al.add("a");
        al.add("d");
        al.add("a");
        al.add("b");
        al.add("c");
        al.remove("a");
        System.out.println(al);
        al.set(2,"e");
        System.out.println(al);

        Iterator<String> it = al.iterator();
        while(it.hasNext()){
        }
        ListIterator<String> lit = al.listIterator();
        lit.hasPrevious();

        List<String> ll = new LinkedList<>();
        ll.add("ll1");
        ll.add("ll2");
        ll.add("ll3");
        Iterator<String> it2 = ll.listIterator();
        ((ListIterator<String>) it2).hasPrevious();

        Vector<String> v = new Vector<>();
        v.add("a");
        Enumeration<String> e = v.elements();
        while(e.hasMoreElements()){
            e.nextElement();
        }
        //ArrayList 和 LinkedList: 1、get方法效率不同 2、arraylist的add后长度为10。之后add方法需要增加长度时每次1.5倍增加长度，浪费空间 3、本质不同
        //ArrayList 和 Vector ：1、Vector默认构造10长度,增长度翻倍或自定义，取较大值 2、Vector线程同步 ，是线程安全的
        //对自定义对象使用remove方法，需要实现equals()方法来判断是否相同。
    }
    private static void setdemo(){
        //没有get()方法,没有重复元素
        //判断重复元素，用hashCode()先看是否编码相同，如果相同再用equals();
        //都没有做线程同步

        HashSet<String> hs = new HashSet<>();//无序
//        public HashSet() {
//            map = new HashMap<>();
//        }

        hs.add("hs1");
        hs.add("hs2");
        hs.add("hs3");
        hs.forEach((str)->System.out.println(str));

        TreeSet<String> ts = new TreeSet<>();//自动排序使用treeMap实现，使用Comparable排序
//        public TreeSet() {
//            this(new TreeMap<E,Object>());
//        }
        ts.add("ts3");
        ts.add("ts2");
        ts.add("ts1");
        ts.forEach((str)->System.out.println(str));


        LinkedHashSet<String> lhs = new LinkedHashSet<>();//录入顺序
        lhs.add("lhs2");
        lhs.add("lhs3");
        lhs.add("lhs1");
        for (String lh : lhs) {
            System.out.println(lh);
        }

    }
    private static void mapdemo(){
        Hashtable<String, Integer> ht = new Hashtable<>();//synchronized,  keys 无序

        HashMap<String, Integer> hm = new HashMap<>(); //无线程同步， keys无序

        TreeMap<String, Integer> tm = new TreeMap<>(); //无线程同步， keys自然序

        LinkedHashMap<String, Integer> lhm = new LinkedHashMap<>(); //无线程同步， 输入顺序


        //hashtable
        ht.put("ht1",1);
        ht.put("ht2",2);
        ht.put("ht3",3);
        Iterator<Integer> hti = ht.values().iterator();
        while(hti.hasNext()){
            System.out.print(hti.next());
        }
        ht.values().forEach(a->{System.out.print(a);});
        ht.entrySet().forEach(a->{
            System.out.print(a.getKey());
            System.out.print(a.getValue());
        });

        //hashmap
        hm.put("ht1",1);
        hm.put("ht2",2);
        hm.put("ht3",3);
        Iterator<Integer> hmi = hm.values().iterator();
        while(hmi.hasNext()){
            System.out.print(hmi.next());
        }

        //treemap
        tm.put("ht1",1);
        tm.put("ht2",2);
        tm.put("ht3",3);
        tm.put("ht4",4);
        tm.put("ht5",5);
        Iterator<Integer> tmi = tm.values().iterator();
        while(tmi.hasNext()){
            System.out.print(tmi.next());
        }

        //LinkedHashMap
        lhm.put("ht1",99);
        lhm.put("ht2",2);
        lhm.put("ht3",5);
        lhm.put("ht4",3);
        lhm.put("ht5",9);
        Iterator<Integer> lhmi = lhm.values().iterator();
        while(lhmi.hasNext()){
            System.out.print(lhmi.next());
        }







    }
    public static void main(String[] args) {


        //1、Collection
        // add(),addAll(),clear()，contains(),remove(),equlas(),size(),isEmpty(),toArray(),iterator()

        //1.1List
        listdemo();

        //1.2Set  没有get()方法,没有重复元素
        setdemo();

        //1.3 queue
        PriorityQueue<String> pq = new PriorityQueue<>();
        pq.add("pq1");
        pq.add("pq2");
        pq.add("pq3");
        while(!pq.isEmpty()){
            System.out.println(pq.poll());
            pq.peek();
        }


        //BlockingQueue<String> bq = new BlockingQueue<>();



        //1.4 Map: put()、get()、entrySet()、containsKey()、keySet()、remove()、clear()
        mapdemo();


    }


}


class Person2 implements Comparable<Person2> {
    private transient String name;
    private int age;
    Person2(String a,int b){
        this.name = a;
        this.age = b;
    }
    @Override
    public String toString(){
        return this.name+this.age;
    }
    @Override
    public int compareTo(Person2 p){
        return this.age - p.age;
    }
}