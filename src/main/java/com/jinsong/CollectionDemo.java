package com.jinsong;

import scala.Int;

import java.util.*;

public class CollectionDemo {


    public static void main(String[] args) {


        //Collection
        // add(),addAll(),clear()，contains(),remove(),equlas(),size(),isEmpty(),toArray(),iterator()

        //List
        //get(index),set(index,value),listIterator()
        ArrayList<String> al = new ArrayList<>();
        List<String> all = new ArrayList<>();
        all.add("a");
        all.add("a");
        all.add("b");
        all.add("c");
        //all.remove("a");
        System.out.println(all);
        all.forEach((str)->{
            System.out.println(str);
        });

        LinkedList<String> ll = new LinkedList<>();

        Vector<String> v = new Vector<>();
        v.add("asd");
        v.add("gfd");
        v.add("oiuyg");

        //ArrayList 和 LinkedList: 1、get方法效率不同 2、arraylist的add方法每次成倍增加长度，浪费空间 3、本质不同

        //ArrayList 和 Vector ：1、Vector默认构造10长度 2、Vector线程同步 ，是线程安全的

        //对自定义对象使用remove方法，需要实现equals()方法来判断是否相同。


        //Set  没有get()方法,没有重复元素

        HashSet<String> hs = new HashSet<>();//无序
        //判断重复元素，用hashCode()先看是否编码相同，如果相同再用equals();

        hs.add("d");
        hs.add("a");
        hs.forEach(System.out::println);


        TreeSet<String> ts = new TreeSet<>();//自动排序使用treeMap实现，使用Comparable排序
        ts.add("d");
        ts.add("a");
        ts.forEach(System.out::println);

        //集合有四种输出方法

        //一、iterator 迭代输出
        System.out.println("iterator输出：");
        Iterator<String> iter = ts.iterator();
        while(iter.hasNext()){
            //iter.remove(); 不会报错
            //ts.remove("a");  会报错，并发更新的异常
            String str = iter.next();
            System.out.println(str);
        }

        //二、ListItrator双向迭代输出，专门为List子接口准备的
        System.out.println("ListItrator双向迭代输出：");
        ListIterator<String> li = all.listIterator();
        while(li.hasNext()){
            System.out.println(li.next());
        }
        while(li.hasPrevious()){
            System.out.println(li.previous());
        }

        //三、Enumeration 只对Vector起作用
        System.out.println("Enumeration输出：");
        Enumeration<String> enu = v.elements();
        while(enu.hasMoreElements()){
            System.out.println(enu.nextElement());
        }

        //四、foreach输出
        System.out.println("forEach输出");
        for(String str : v){
            System.out.println(str);
        }



        LinkedHashSet<String> lhs = new LinkedHashSet<>();

        //queue
        PriorityQueue<String> pq = new PriorityQueue<>();

        //Map
        Hashtable<String, Int> ht = new Hashtable<>();
        LinkedHashMap<String, Int> lhm = new LinkedHashMap<>();
        HashMap<String, Int> hm = new HashMap<>();
        TreeMap<String, Int> tm = new TreeMap<>();


    }


}
enum Weekday {
    SUN, MON, TUE, WED, THU, FRI, SAT;
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