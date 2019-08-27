package com.jinsong.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Test {

    public static void main(String[] args){
        Queue<Character> queue = new LinkedList<>();
        queue.add('4');
        queue.add('3');
        queue.add('2');
        System.out.println(queue.peek());
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }
}
