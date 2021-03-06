package com.jinsong.leetcode;

import java.util.HashMap;

class ListNode19 {
    int val;
    ListNode19 next;
    ListNode19() {}
    ListNode19(int val) { this.val = val; }
    ListNode19(int val, ListNode19 next) { this.val = val; this.next = next; }
}
public class LeetCode19 {

    public ListNode19 removeNthFromEnd(ListNode19 head, int n) {
        HashMap<Integer,ListNode19> map = new HashMap<>();
        int a = 1;
        
        ListNode19 next = head;

        while(next != null){
            map.put(a, next);
            a++;
            next = next.next;
        }
        a--;
        if(a == 1){
            return null;
        }
        if(a-n!=0){
            map.get(a-n).next = map.get(a-n+2);
            return head;
        }else{
            return map.get(a-n+2);
        }
    }

    public static void main(String[] args) {
        //1->2->3->4->5
        LeetCode19 leetCode19 = new LeetCode19();

        ListNode19 node1 = new ListNode19(1);
        ListNode19 node2 = new ListNode19(2);
        ListNode19 node3 = new ListNode19(3);
        ListNode19 node4 = new ListNode19(4);
        ListNode19 node5 = new ListNode19(5);

        //node1.next = node2;
       // node2.next = node3;
       // node3.next = node4;
        // node4.next = node5;

        int n = 1;
        node1 = leetCode19.removeNthFromEnd(node1,n);

        ListNode19 next = node1;
        while(next != null){
            System.out.println(next.val);
            next = next.next;
        }


    }
}
