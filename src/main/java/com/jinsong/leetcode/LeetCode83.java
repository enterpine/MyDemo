package com.jinsong.leetcode;

import java.util.List;

public class LeetCode83 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) {
            this.val = val; this.next = next;
        }
    }

    public static ListNode deleteDuplicates(ListNode head) {

        if(head == null||head.next == null){
            return head;
        }
        ListNode next_node = head.next;
        ListNode tail_node = head;
        int last_val = head.val;

        while(next_node!= null){
            if(next_node.val != last_val){
                tail_node.next = next_node;
                tail_node = next_node;
                last_val = next_node.val;
            }else{
                tail_node.next=null;
            }
            next_node = next_node.next;
        }

        return  head;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(1);
        ListNode listNode3 = new ListNode(1);
        ListNode listNode4 = new ListNode(1);
        ListNode listNode5 = new ListNode(1);
        listNode1.next=listNode2;
        listNode2.next=listNode3;
        listNode3.next=listNode4;
        listNode4.next=listNode5;
        ListNode head = deleteDuplicates(listNode1);

        while(head != null){
            System.out.print(head.val);
            head = head.next;
        }

    }
}
