package com.jinsong.leetcode;



public class LeetCode2 {


        public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

            int flag = 0 ;
            int flag2 = 0;
            ListNode result = new ListNode(0);
            ListNode lastnode = new ListNode(0);
            while(l1!=null || l2!=null ||flag==1) {
                int a = 0;
                int b = 0;
                if(l1!=null){
                    a = l1.val;
                }
                if(l2!=null){
                    b = l2.val;
                }
                int t = a + b + flag;
                flag = t/10;
                int v = t%10;
                if(flag2==0){
                    result.val=v;
                    lastnode=result;
                    flag2=1;
                }
                else {
                    ListNode tmp = new ListNode(0);
                    tmp.val = v;
                    tmp.next = null;
                    lastnode.next=tmp;
                    lastnode=tmp;
                    //System.out.println("123");
                }
                if(l1!=null) {
                    l1 = l1.next;
                }
                if(l2!=null) {
                    l2 = l2.next;
                }
            }
            return result;

        }

        public static void main(String[] args){
            ListNode l1 = new ListNode(1);
            ListNode l1a = new ListNode(2);
            ListNode l1b = new ListNode(3);
            ListNode l1c = new ListNode(3);

            l1.next=l1a;
            l1a.next=l1b;
            l1b.next=l1c;

            ListNode l2 = new ListNode(1);
            ListNode l2a = new ListNode(2);
            ListNode l2b = new ListNode(3);
            ListNode l2c = new ListNode(9);

            l2.next=l2a;
            l2a.next=l2b;
            l2b.next=l2c;

            ListNode a  = addTwoNumbers(l1,l2);

//            while(l1!=null){
//                System.out.print(l1.val);
//                l1=l1.next;
//            }
//            System.out.print("  ");
//            while(l2!=null){
//                System.out.print(l2.val);
//                l2=l2.next;
//            }
            while(a!=null){
                System.out.print(a.val);
                a=a.next;
            }

        }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
