package com.jinsong.leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class LeetCode19_arraylist {

    public ListNode19 removeNthFromEnd(ListNode19 head, int n) {
        List<ListNode19> list = new ArrayList<>();
        int a = 1;
        
        ListNode19 next = head;

        while(next != null){
            list.add(next);
            a++;
            next = next.next;
        }
        a--;
        if(a == 1){
            return null;
        }
        if(a-n!=0){
 
            list.get(a-n-1).next = (a-n+1)==a?null:list.get(a-n+1);
            return head;
        }else{
            return list.get(a-n+1);
        }
    }

    public static void main(String[] args) {
        //1->2->3->4->5
        LeetCode19_arraylist leetCode19_arraylist = new LeetCode19_arraylist();

        ListNode19 node1 = new ListNode19(1);
        ListNode19 node2 = new ListNode19(2);
        ListNode19 node3 = new ListNode19(3);
        ListNode19 node4 = new ListNode19(4);
        ListNode19 node5 = new ListNode19(5);

        node1.next = node2;
    //    node2.next = node3;
    //    node3.next = node4;
    //     node4.next = node5;

        int n = 2;
        node1 = leetCode19_arraylist.removeNthFromEnd(node1,n);

        ListNode19 next = node1;
        while(next != null){
            System.out.println(next.val);
            next = next.next;
        }


    }
}
