package com.jinsong.leetcode;


class ListNode21 {
    int val;
    ListNode21 next;
    ListNode21() {}
    ListNode21(int val) { this.val = val; }
    ListNode21(int val, ListNode21 next) {
        this.val = val;
        this.next = next;
    }
}

public class LeetCode21 {
    public static ListNode21 mergeTwoLists(ListNode21 list1, ListNode21 list2) {
        ListNode21 h = null;
        ListNode21 t = new ListNode21();
        int flag = 0;




        while(list1!=null || list2!=null) {

            Integer curAVal = list1!=null?list1.val:null;
            Integer cutBVal = list2!=null?list2.val:null;
            ListNode21 tempNode = null;

            if(curAVal!=null &&  cutBVal!=null) {
                int curMinVal = curAVal < cutBVal ? curAVal : cutBVal;
                tempNode = new ListNode21(curMinVal);
                if(flag == 0){
                    h =  tempNode;
                    t =  tempNode;
                    flag = 1;
                }else{
                    t.next = tempNode;
                    t = tempNode;
                }

                if (curAVal < cutBVal) {
                    list1 = list1==null?null:list1.next;

                } else {
                    list2 = list2==null?null:list2.next;
                }
            }else if(curAVal!=null){
                tempNode = new ListNode21(curAVal);
                if(flag == 0){
                    h =  tempNode;
                    t =  tempNode;
                    flag = 1;
                }else{
                    t.next = tempNode;
                    t = tempNode;
                }
                list1 = list1==null?null:list1.next;
            }else{

                tempNode = new ListNode21(cutBVal);
                if(flag == 0){
                    h =  tempNode;
                    t =  tempNode;
                    flag = 1;
                }else{
                    t.next = tempNode;
                    t = tempNode;
                }
                list2 = list2==null?null:list2.next;
            }



        }


        return h;
    }

    public static void main(String[] args) {

        ListNode21 node1_3 = new ListNode21(4);
        ListNode21 node1_2 = new ListNode21(2,node1_3);
        ListNode21 node1_1 = new ListNode21(1,node1_2);

        ListNode21 node2_3 = new ListNode21(4);
        ListNode21 node2_2 = new ListNode21(3,node2_3);
        ListNode21 node2_1 = new ListNode21(1,node2_2);

        ListNode21 r = LeetCode21.mergeTwoLists(null,null);

        while(r.next!=null){
            System.out.println(r.val);
            r=r.next;
        }

    }
}
