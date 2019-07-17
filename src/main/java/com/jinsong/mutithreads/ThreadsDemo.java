package com.jinsong.mutithreads;
import java.lang.Thread;

public class ThreadsDemo extends Thread {

    String title="";

    ThreadsDemo(String title){
        this.title=title;
    }

    @Override
    public  void run(){
        for(int i=0;i<10;i++) {
            System.out.print(this.title);
        }
    }

    public static void main(String[] args){
        ThreadsDemo threadA = new ThreadsDemo("a");
        ThreadsDemo threadB = new ThreadsDemo("b");
        ThreadsDemo threadC = new ThreadsDemo("c");
        threadA.start();
        threadB.start();
        threadC.start();
    }
}
