package com.jinsong.leetcode;


public class LeetCode92 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        int i = 1;
        ListNode listNodeNext = head;

        ListNode part1Tail = null;
        ListNode part2Tail = null;
        ListNode part3Head = null;


        ListNode btnHead = new ListNode();

        while(listNodeNext != null){
            if(i>=1 && i == left-1){
                part1Tail = listNodeNext;
            }else if(i>=left && i<=right){

                ListNode listNodeTmp = new ListNode(listNodeNext.val);
                if(i == left){
                    part2Tail = listNodeTmp;
                }
                if(i == right && listNodeNext.next != null){
                    part3Head = listNodeNext.next;
                }
                listNodeTmp.next = btnHead.next;
                btnHead.next = listNodeTmp;
            }
            i++;
            listNodeNext = listNodeNext.next;

        }
        if(part1Tail != null) {
            part1Tail.next = btnHead.next;
            part2Tail.next = part3Head;
            return head;
        }else{
            //part1Tail.next = btnHead.next;
            part2Tail.next = part3Head;
            return btnHead.next;
        }
    }

    public static void main(String[] args) {
        ListNode  listNode1 = new ListNode(1);
        ListNode  listNode2 = new ListNode(2);
        ListNode  listNode3 = new ListNode(3);
        ListNode  listNode4 = new ListNode(4);
        ListNode  listNode5 = new ListNode(5);
        listNode1.next=listNode2;
        listNode2.next=listNode3;
        listNode3.next=listNode4;
        listNode4.next=listNode5;

        ListNode listNodeR = reverseBetween(listNode1,1,4);

        ListNode listNodeTmp = listNode4;

        //listNode4.next = listNode3;

        while(listNodeR!=null){
            System.out.println(listNodeR.val);
            listNodeR = listNodeR.next;
        }



    }
}
