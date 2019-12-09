package com.jinsong.javabase;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;



public class ReverseList {
    public static void main(String[] args){
        List<String> l = new LinkedList<String>();
        l.add("a");
        l.add("b");
        l.add("c");
        l.add("d");
        List<String> result = reverseList(l);
        Iterator it = result.iterator();
        while(it.hasNext()){
            System.out.print(it.next().toString());
        }
    }
    private static List<String> reverseList(List<String> l){
        class Node{
            Node(String a){
                this.v=a;
            }
            String v;
            Node next = null;

        }
        List<String> rtn = new LinkedList<String>();

        Iterator it = l.iterator();

        Node tmp =new Node("");
        int i=0;
        while(it.hasNext()){
            String a = it.next().toString();

            System.out.println(a);
            Node n = new Node(a);
            if(i==0) {
                tmp = n;
                i=1;
            }
            else{
                n.next=tmp;
                tmp = n;
            }
        }

        while(tmp.next != null){
            rtn.add(tmp.v);
            tmp=tmp.next;
        }
        rtn.add(tmp.v);

        return rtn;
    }

}
