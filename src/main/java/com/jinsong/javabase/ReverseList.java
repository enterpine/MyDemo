package com.jinsong.javabase;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class ListNode{
    String value;
    ListNode next;
    ListNode(String v){
        this.value=v;
        next = null;
    }
}

public class ReverseList {


    public static void main(String[] args){
        List<String> l = new LinkedList<String>();
        l.add("a");
        l.add("b");
        l.add("c");
        l.add("d");

        List<String> result = reverseList3(l);

        Iterator it = result.iterator();
        while(it.hasNext()){
            System.out.print(it.next().toString());
        }


    }

    static List<String> reverseList3(List<String> l){

        Iterator<String> iterator = l.iterator();
        ListNode result = new ListNode("head");

        reverseList33(iterator,result);

        LinkedList<String> rtn = new LinkedList<String>();

        while(result.next!=null){
            rtn.add(result.value);
            result = result.next;
        }
        rtn.add(result.value);
        return rtn;
    }

    private static ListNode reverseList33(Iterator<String> iterator,ListNode result){
        if(iterator.hasNext()) {
            String curValue = iterator.next();
            System.out.println(curValue);
            ListNode curNode = new ListNode(curValue);
            ListNode preNode =  reverseList33(iterator,result);
            preNode.next = curNode;
            return curNode;
        }else {
            return result;
        }
    }

    private static LinkedList<String> reverseList2(List<String> l){
        //迭代
        Iterator<String> iterator = l.iterator();
        ListNode headNode = null;

        while(iterator.hasNext()){
            String curValue = iterator.next();
            ListNode curNode = new ListNode(curValue);
            curNode.next = headNode;
            headNode = curNode;
        }

        LinkedList<String> rtn = new LinkedList<String>();
        while(headNode.next!=null){
            rtn.add(headNode.value);
            headNode = headNode.next;
        }
        rtn.add(headNode.value);
        return rtn;
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
